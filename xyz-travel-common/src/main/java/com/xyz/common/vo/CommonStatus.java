package com.xyz.common.vo;

import com.xyz.common.constants.ResponseCode;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonStatus implements Serializable {
  private static final long serialVersionUID = 2628686242636591547L;

  private String code;
  private String message;
  private String details = "";

  public CommonStatus(String code, String message, String details) {
    this.code = code;
    this.message = message;
    this.details = details;
  }

  public CommonStatus(ResponseCode responseCode) {
    this.code = responseCode.getCode();
    this.message = responseCode.getMsg();
    this.details = "";
  }

  public CommonStatus(ResponseCode responseCode, String details) {
    this.code = responseCode.getCode();
    this.message = responseCode.getMsg();
    this.details = details;
  }

  public CommonStatus() {}
}
