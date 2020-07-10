package com.xyz.common.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationInfo implements Serializable {

  private static final long serialVersionUID = 8083159834925716027L;

  private String applicationId;
  private String uen;
  private String employeeName;
  private String employeeId;
  private String employeePassport;
  private String employeeOriginCountry;
  private String employeeDestinationCountry;
  private String employeeTravelFromDate;
  private String employeeTravelToDate;
  private Date submitDatetime;
  private String submitBy;
  private Date lastUpdateDatetime;
  private String lastUpdateBy;
  private Double travelCost;
  private String status;
  private String remarks;
  private Object travelPath;
  private String companyName;
}
