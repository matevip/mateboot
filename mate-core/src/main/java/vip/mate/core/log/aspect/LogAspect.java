package vip.mate.core.log.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vip.mate.core.log.annotation.Log;

/**
 * 日志切面
 *
 * @author matevip
 * @date 2023/9/22
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
     * 日志切入点
     */
    @Pointcut("@annotation(vip.mate.core.log.annotation.Log)")
    public void logPointCut() {}

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleCommonLogic(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleCommonLogic(joinPoint, e, null);
    }

    protected void handleCommonLogic(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            Log controllerLog = getAnnotationLog(joinPoint);
            if(controllerLog == null){
                return;
            }



        } catch (Exception exp) {
            log.error("日志记录异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Log log = joinPoint.getTarget().getClass().getAnnotation(Log.class);
        if (log != null) {
            return log;
        }
        return null;
    }

    /**
     * 获取请求的参数，放到log中
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                params += o.toString() + " ";
            }
        }
        return params.trim();
    }

    /**
     * 是否需要过滤的对象
     */
    public boolean isFilterObject(Object obj) {
        return obj instanceof MultipartFile || obj instanceof HttpServletRequest || obj instanceof HttpServletResponse;
    }
}
