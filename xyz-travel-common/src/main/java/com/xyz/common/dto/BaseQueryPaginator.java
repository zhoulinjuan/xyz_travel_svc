package com.xyz.common.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class BaseQueryPaginator implements Serializable {

  private static final long serialVersionUID = -2928704014550812170L;

  @NotNull
  @Min(1)
  @ApiModelProperty(value = "Current Page Number", example = "1")
  public int pageNum = 1;

  @NotNull
  @Range(min = 1, max = 30000, message = "search.validate.pagesize.range.failed")
  @ApiModelProperty(value = "Number of records per page", example = "10")
  public int pageSize = 10;
}
