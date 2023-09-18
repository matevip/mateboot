package vip.mate.bootstrap.handler;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.SaTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.mate.core.common.enums.ErrorCode;
import vip.mate.core.common.exception.ServerException;
import vip.mate.core.common.response.Result;

import java.nio.file.AccessDeniedException;


/**
 * 异常处理器
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Slf4j
@RestControllerAdvice
public class GlobalServerExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServerException.class)
    public Result<String> handleException(ServerException ex) {

        return Result.error(ex.getCode(), ex.getMsg());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return Result.error(fieldError.getDefaultMessage());
    }

    /**
     * 没有权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result<String> handleAccessDeniedException(NotPermissionException ex) {
        return Result.error(ErrorCode.FORBIDDEN.getCode(), ex.getMessage());
    }

    @ExceptionHandler(SaTokenException.class)
    public Result<String> SaTokenException(SaTokenException ex) {
        return Result.error(ErrorCode.UNAUTHORIZED.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}