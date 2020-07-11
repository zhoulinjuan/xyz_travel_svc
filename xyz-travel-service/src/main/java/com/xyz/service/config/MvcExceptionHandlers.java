package com.xyz.service.config;

import com.xyz.common.constants.ResponseCode;
import com.xyz.common.utils.ResultVoUtil;
import com.xyz.common.vo.BaseErrorResult;
import com.xyz.common.vo.RestException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class MvcExceptionHandlers {

  @ExceptionHandler(BindException.class)
  public ResponseEntity<BaseErrorResult> handleBindException(BindException ex) {
    log.debug("BindException thrown?", ex);
    return getBaseErrorResultResponseEntity(ex.getBindingResult());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BaseErrorResult> handle400FailedValidation(
      MethodArgumentNotValidException ex) {
    log.info("Mvc request parameters failed validation", ex);

    return getBaseErrorResultResponseEntity(ex.getBindingResult());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<BaseErrorResult> handle400UndeserializableRequests(
      HttpMessageNotReadableException ex) {
    log.info("Unable to deserialize mvc request parameters: {}", ex.getMessage());
    log.debug("Details:", ex);

    BaseErrorResult result =
        ResultVoUtil.failed(ResponseCode.VALIDATE_FAILED.getCode(), ex.getMessage());
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<BaseErrorResult> handle404Requests(NoHandlerFoundException ex) {
    log.info("404 received: {}", ex.getRequestURL());

    BaseErrorResult result =
        ResultVoUtil.failed(ResponseCode.NOT_FOUND.getCode(), ex.getRequestURL());
    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<BaseErrorResult> handle405Requests(
      HttpRequestMethodNotSupportedException ex) {
    log.info("405 received: {} {}", ex.getMethod(), ex.getMessage());

    BaseErrorResult result =
        ResultVoUtil.failed(
            ResponseCode.METHOD_NOT_ALLOWED.getCode(),
            ResponseCode.METHOD_NOT_ALLOWED.getMsg(),
            ex.getMessage());
    return new ResponseEntity<>(result, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<BaseErrorResult> handle415Requests(HttpMediaTypeNotSupportedException ex) {
    log.info("415 received: {} {}", ex.getContentType(), ex.getMessage());

    BaseErrorResult result =
        ResultVoUtil.failed(
            ResponseCode.UNSUPPORTED_MEDIA_TYPE_FAILED.getCode(),
            ResponseCode.UNSUPPORTED_MEDIA_TYPE_FAILED.getMsg());
    return new ResponseEntity<>(result, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ExceptionHandler(RestException.class)
  public ResponseEntity<BaseErrorResult> restException(RestException ex) {
    log.debug("Rest exception thrown????", ex);
    return new ResponseEntity<>(ResultVoUtil.failed(ex), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<BaseErrorResult> unhandledException(Throwable ex) {
    log.error("Unhandled exception in mvc", ex);

    BaseErrorResult result =
        ResultVoUtil.failed(ResponseCode.FAILED.getCode(), "Something bad happen", ex.getMessage());
    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<BaseErrorResult> getBaseErrorResultResponseEntity(BindingResult result) {
    List<String> allErrorMessages = new ArrayList<>();
    List<ObjectError> allErrors = result.getAllErrors();

    for (ObjectError error : allErrors) {
      allErrorMessages.add(error.getDefaultMessage());
    }

    return new ResponseEntity<>(
        ResultVoUtil.failed(ResponseCode.VALIDATE_FAILED.getCode(), allErrorMessages.toString()),
        HttpStatus.BAD_REQUEST);
  }
}
