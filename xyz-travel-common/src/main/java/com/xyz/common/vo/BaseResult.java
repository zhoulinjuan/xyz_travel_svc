package com.xyz.common.vo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResult implements Serializable {
  private static final long serialVersionUID = -3247284534259235685L;

  @ApiModelProperty("Common response data")
  private CommonStatus status;
}
