package com.xyz.common.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationProcessInfo implements Serializable {
  private static final long serialVersionUID = -4155612656888236957L;

  private Double travelCost;
  private String status;
  private String remarks;
}
