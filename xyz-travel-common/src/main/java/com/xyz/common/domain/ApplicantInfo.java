package com.xyz.common.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicantInfo implements Serializable {

  private static final long serialVersionUID = -2207453222848469986L;

  private String uen;
  private String applicantName;
  private String applicantContact;
  private String applicantEmail;
  private String role;
}
