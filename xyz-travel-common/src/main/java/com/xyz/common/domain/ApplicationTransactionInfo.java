package com.xyz.common.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationTransactionInfo implements Serializable {

  private static final long serialVersionUID = -6914666213064600093L;

  private String applicationId;
  private Date lastUpdateDatetime;
  private String lastUpdateBy;
  private Double travelCost;
  private String status;
  private String remarks;
}
