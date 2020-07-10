package com.xyz.common.constants;

import lombok.Getter;

@Getter
public class ResponseCode {

  private String code;
  private String msg;

  // 0000 success
  public static final ResponseCode SUCCESS = new ResponseCode("00-0000", "successful.operation");

  // 0400 failed
  public static final ResponseCode FAILED = new ResponseCode("00-0400", "failed.operation");

  // 0400 validate failed
  public static final ResponseCode VALIDATE_FAILED = new ResponseCode("00-0400", "validate.failed");

  // 0401 validate unauthorized
  public static final ResponseCode UNAUTHORIZED =
      new ResponseCode("00-0401", "validate.unauthorized");

  // 0404 not found
  public static final ResponseCode NOT_FOUND = new ResponseCode("00-0404", "not.found");

  // 0405 method not allowed
  public static final ResponseCode METHOD_NOT_ALLOWED =
      new ResponseCode("00-0405", "method.not.allowed");

  // 0415 method not allowed
  public static final ResponseCode UNSUPPORTED_MEDIA_TYPE_FAILED =
      new ResponseCode("00-0415", "unsupported.media.type.failed");

  public ResponseCode(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
