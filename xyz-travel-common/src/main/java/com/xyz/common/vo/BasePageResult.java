package com.xyz.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePageResult<T> extends BaseObjectResult<T> {

  private static final long serialVersionUID = -3915629832495329644L;

  @ApiModelProperty("Common paginator")
  @JsonProperty("paginator")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private CommonPaginator commonPaginator;
}
