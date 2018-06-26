package cn.imust.ys.springbootshiro.handler;

import cn.imust.ys.springbootshiro.utils.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * 异常处理类
 *
 * @author YDeity
 * @version 1.0
 */
@RestControllerAdvice
public class ErrorExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);

    /**
     * 统一异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({ RuntimeException.class })
    @ResponseStatus(HttpStatus.OK)
    public Map processException(RuntimeException exception) {
        logger.info("自定义异常处理-RuntimeException");
        return ControllerUtils.getCustomException(exception.getMessage());
    }

    /**
     * 统一异常处理
     *
     * @param exception
     *            exception
     * @return
     */
    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.OK)
    public Map processException(Exception exception) {
        logger.info("自定义异常处理-Exception");
        return ControllerUtils.getCustomException(exception.getMessage());
    }

}
