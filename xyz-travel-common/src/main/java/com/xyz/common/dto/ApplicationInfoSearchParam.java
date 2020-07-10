package com.xyz.common.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationInfoSearchParam extends BaseQueryParam {

  private static final long serialVersionUID = 1650977036564510704L;

  @ApiModelProperty(value = "Application ID.")
  private String applicationId;

  @ApiModelProperty(value = "UEN No.")
  private String uen;

  @ApiModelProperty(value = "Employee name")
  private String employeeName;

  @ApiModelProperty(value = "Employee ID")
  private String employeeId;

  @ApiModelProperty(value = "Employee Passport Number")
  private String employeePassport;

  // ORDER BY
  @ApiModelProperty(value = "Order by `employeeName` ASC or DESC", allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderEmployeeName;

  @ApiModelProperty(value = "Order by `uen` ASC or DESC", allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderUEN;
}
