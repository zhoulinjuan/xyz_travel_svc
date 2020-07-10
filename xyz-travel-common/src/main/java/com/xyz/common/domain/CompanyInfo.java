package com.xyz.common.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CompanyInfo implements Serializable {
  private static final long serialVersionUID = 4399199924122174211L;

  private String uen;
  private String companyName;
  private String companyAddress;
}
