package org.epsda.base.advice;

import lombok.extern.slf4j.Slf4j;
import org.epsda.base.enums.ResponseStatusCode;
import org.epsda.base.exception.ServiceException;
import org.epsda.base.utils.ResultWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description: WebMVC异常拦截器
 * Author: EPSDA
 * Date: 2026/08/03
 * Time: 13:35
 * Package Name: org.epsda.base.advice
 * Project Name: online-oj
 */
@Slf4j
@RestControllerAdvice // @ControllerAdvice 和 @ResponseBody 结合体
public class ExceptionAdvice {

    private static final String SERVICE_UNAVAILABLE_MESSAGE = "服务器内部错误";
    private static final String RESOURCE_NOT_FOUND_MESSAGE = "资源不存在";
    private static final String METHOD_ARGUMENT_ERROR_MESSAGE = "参数校验异常";

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultWrapper allExceptionHandler(Exception e) {
        // 详细日志只在后端记录
        log.error("系统异常: ", e);

        // 其他异常返回通用提示，不暴露技术细节
        return ResultWrapper.fail(ResponseStatusCode.SYSTEM_INTERNAL_ERROR.getCode(), SERVICE_UNAVAILABLE_MESSAGE);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultWrapper allExceptionHandler(MethodArgumentNotValidException e) {
        String errMsg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        log.info("出现参数校验异常：{}", errMsg);
        return ResultWrapper.fail(ResponseStatusCode.SYSTEM_INTERNAL_ERROR.getCode(), METHOD_ARGUMENT_ERROR_MESSAGE);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResultWrapper noResourceFoundException(NoResourceFoundException e) {
        log.warn("资源不存在: {}", e.getResourcePath());
        return ResultWrapper.fail(ResponseStatusCode.RESOURCES_NOT_FOUND.getCode(), RESOURCE_NOT_FOUND_MESSAGE);
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(IllegalStateException.class)
    public ResultWrapper illegalStateExceptionHandler(IllegalStateException e) {
        log.warn("状态异常: {}", e.getMessage());
        // 状态异常通常是业务逻辑问题，返回具体提示
        return ResultWrapper.fail(ResponseStatusCode.SYSTEM_INTERNAL_ERROR.getCode(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(RuntimeException.class)
    public ResultWrapper runtimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常: ", e);
        return ResultWrapper.fail(ResponseStatusCode.SYSTEM_INTERNAL_ERROR.getCode(), SERVICE_UNAVAILABLE_MESSAGE);
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServiceException.class)
    public ResultWrapper serviceExceptionHandler(ServiceException e) {
        log.error("运行时异常: ", e);
        return ResultWrapper.fail(e.getCode(), e.getMessage());
    }
}
