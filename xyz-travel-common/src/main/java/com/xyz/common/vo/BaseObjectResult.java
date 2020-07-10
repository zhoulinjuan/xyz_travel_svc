package com.xyz.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseObjectResult<T> extends BaseResult {
  private static final long serialVersionUID = 6612651436478590991L;

  @ApiModelProperty("Common response data")
  @JsonProperty("data")
  private transient T data;
}
