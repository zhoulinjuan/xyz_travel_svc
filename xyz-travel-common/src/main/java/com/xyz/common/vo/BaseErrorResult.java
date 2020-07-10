package com.xyz.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseErrorResult extends BaseResult {
  private static final long serialVersionUID = -4861532833369684633L;

  public BaseErrorResult(CommonStatus commonStatus) {
    this.setStatus(commonStatus);
  }
}
