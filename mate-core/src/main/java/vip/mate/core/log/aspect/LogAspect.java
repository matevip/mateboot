package vip.mate.core.log.aspect;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import vip.mate.core.common.context.UserInfoContextHolder;
import vip.mate.core.common.req.UserVO;
import vip.mate.core.common.response.Result;
import vip.mate.core.common.utils.IpUtils;
import vip.mate.core.common.utils.ServletUtils;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.log.enums.BusinessStatus;
import vip.mate.core.log.handle.LogHandle;
import vip.mate.core.log.req.SysOperateLogReq;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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
@RequiredArgsConstructor
public class LogAspect {

    private final LogHandle logHandle;

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<StopWatch> TIME_THREADLOCAL = new TransmittableThreadLocal<>();

    /**
     * 处理请求前执行
     */
    @Before(value = "logPointCut()")
    public void boBefore(JoinPoint joinPoint) {
        StopWatch stopWatch = new StopWatch();
        TIME_THREADLOCAL.set(stopWatch);
        stopWatch.start();
    }

    /**
     * 日志切入点
     */
    @Pointcut("@annotation(vip.mate.core.log.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        saveLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        saveLog(joinPoint, e, null);
    }

    protected void saveLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {

        SysOperateLogReq handleLog = new SysOperateLogReq();
        try {
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            // 获取IP地址
            String ip = IpUtils.getIpAddr(ServletUtils.getHttpServletRequest());
            handleLog.setIp(ip);
            handleLog.setAddress(IpUtils.getCityInfo(ip));
            // 获取当前用户
            UserVO user = UserInfoContextHolder.get(StpUtil.getLoginIdAsLong());
            handleLog.setUserId(user.getId());
            handleLog.setRealName(user.getRealName());

            // 设置部分请求和响应数据
            handleLog.setReqUri(ServletUtils.getHttpServletRequest().getRequestURI());
            handleLog.setReqMethod(ServletUtils.getHttpServletRequest().getMethod());
            Result result = JSONUtil.toBean(JSONUtil.parseObj(jsonResult), Result.class);
            handleLog.setJsonResult(JSONUtil.toJsonStr(result));
            handleLog.setUserAgent(ServletUtils.getHttpServletRequest().getHeader("User-Agent"));
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, handleLog);

            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            handleLog.setMethod(className + "." + methodName + "()");
            // 根据包名截取模块名
            String[] parts = className.split("\\.");
            String extractedString = parts[2];
            handleLog.setModule(extractedString);

            // 设置操作状态
            handleLog.setStatus(result.getCode() == 0 ? BusinessStatus.SUCCESS.ordinal() : BusinessStatus.FAIL.ordinal());

            // 设置消耗时间
            StopWatch stopWatch = TIME_THREADLOCAL.get();
            stopWatch.stop();
            handleLog.setDuration((int) stopWatch.getTime());

            // 处理异常信息
            if (e != null) {
                handleLog.setStatus(BusinessStatus.FAIL.ordinal());
                log.error("Log Exception:{}", e.getMessage());
                handleLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }

            // 保存日志
            logHandle.saveLog(handleLog);
        } catch (Exception exp) {
            log.error("日志记录异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     *
     * @param joinPoint 切点
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
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log       日志
     * @param handleLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperateLogReq handleLog) throws Exception {
        // 设置action动作
        handleLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        handleLog.setName(log.title());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, handleLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param paramsArray 参数数组
     */
    private String argsArrayToString(Object[] paramsArray) {
        return Arrays.stream(paramsArray)
                .filter(o -> !isFilterObject(o))
                .map(JSONUtil::toJsonStr)
                .collect(Collectors.joining(" "));
    }

    /**
     * 是否需要过滤的对象
     *
     * @param obj 对象
     */
    public boolean isFilterObject(Object obj) {
        return obj instanceof MultipartFile || obj instanceof HttpServletRequest || obj instanceof HttpServletResponse;
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param joinPoint 切点
     * @param handleLog 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperateLogReq handleLog) {
        String requestMethod = handleLog.getReqMethod();

        if (HttpMethod.PUT.name().equals(requestMethod)
                || HttpMethod.POST.name().equals(requestMethod)
                || HttpMethod.DELETE.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            handleLog.setReqParams(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap =
                    (Map<?, ?>) ServletUtils.getHttpServletRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            handleLog.setReqParams(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }
}
