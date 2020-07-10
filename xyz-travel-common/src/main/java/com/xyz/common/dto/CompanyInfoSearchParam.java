package com.xyz.common.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyInfoSearchParam extends BaseQueryParam {

  @ApiModelProperty(value = "UEN No.")
  private String uen;

  @ApiModelProperty(value = "Company name")
  private String companyName;

  // ORDER BY
  @ApiModelProperty(value = "Order by `uen` ASC or DESC", allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderUEN;

  @ApiModelProperty(value = "Order by `companyName` ASC or DESC", allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderCompanyName;
}
