package vip.mate.core.log.aspect;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vip.mate.core.common.response.Result;
import vip.mate.core.common.utils.IpUtils;
import vip.mate.core.common.utils.ServletUtils;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.log.enums.BusinessStatus;

import java.lang.reflect.Method;

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

            String ip = IpUtils.getIpAddr(ServletUtils.getHttpServletRequest());

            Result result = JSONUtil.toBean(JSONUtil.parseObj(jsonResult), Result.class);

            int status = result.getCode() == 0 ? BusinessStatus.SUCCESS.ordinal() : BusinessStatus.FAIL.ordinal();

            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            System.out.println(className + "." + methodName + "()");
            String requestURI = ServletUtils.getHttpServletRequest().getRequestURI();
            String method = ServletUtils.getHttpServletRequest().getMethod();
            System.out.println("requestURI=" + requestURI + ",method=" + method);
            // 设置请求方式
//            operLog.setRequestMethod(ServletUtils.getHttpServletRequest().getMethod());
            // 处理设置注解上的参数





        } catch (Exception exp) {
            log.error("日志记录异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
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
