package com.xyz.common.vo;

import com.xyz.common.constants.ResponseCode;
import lombok.Getter;

@Getter
public class RestException extends RuntimeException {
  private static final long serialVersionUID = 9013897362878036567L;

  private final String code;
  private final String message;
  private final transient Object[] args;

  public RestException(String message) {
    super(message);
    this.code = null;
    this.message = message;
    this.args = null;
  }

  public RestException(ResponseCode responseCode, String details) {
    super(responseCode.getMsg());
    this.code = responseCode.getCode();
    this.message = responseCode.getMsg();
    this.args = new Object[] {details};
  }
}
