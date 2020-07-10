package com.xyz.common.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationTransactionInfoSearchParam extends BaseQueryParam {

  private static final long serialVersionUID = -8566091939789969085L;

  @ApiModelProperty(value = "Application ID.")
  private String applicationId;

  // ORDER BY
  @ApiModelProperty(
      value = "Order by `lastUpdateDatetime` ASC or DESC",
      allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderLastUpdateDatetime;

  @ApiModelProperty(value = "Order by `lastUpdateBy` ASC or DESC", allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderLastUpdateBy;
}
