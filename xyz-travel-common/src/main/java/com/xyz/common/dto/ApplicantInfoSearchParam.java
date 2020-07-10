package com.xyz.common.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantInfoSearchParam extends BaseQueryParam {

  @ApiModelProperty(value = "Applicant Name")
  private String applicantName;

  @ApiModelProperty(value = "Applicant Contact Number")
  private String applicantContact;

  @ApiModelProperty(value = "Applicant Contact Email")
  private String applicantEmail;

  @ApiModelProperty(value = "UEN Number")
  private String uen;

  // ORDER BY
  @ApiModelProperty(value = "Order by `applicantName` ASC or DESC", allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderApplicantName;

  @ApiModelProperty(value = "Order by `applicantEmail` ASC or DESC", allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderApplicantEmail;

  @ApiModelProperty(value = "Order by `uen` ASC or DESC", allowableValues = "ASC,DESC")
  @Pattern(
      regexp = "^(?i)(ASC|DESC)$",
      message = "Please provide \"ASC\" for Ascending or \"DESC\" for Descending")
  private String orderUEN;
}
