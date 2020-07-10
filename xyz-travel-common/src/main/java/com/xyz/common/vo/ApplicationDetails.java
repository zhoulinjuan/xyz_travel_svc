package com.xyz.common.vo;

import com.xyz.common.domain.ApplicantInfo;
import com.xyz.common.domain.ApplicationInfo;
import com.xyz.common.domain.ApplicationTransactionInfo;
import com.xyz.common.domain.CompanyInfo;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApplicationDetails implements Serializable {
  private static final long serialVersionUID = -3829862620698882173L;

  private ApplicantInfo applicantInfo;
  private CompanyInfo companyInfo;
  private ApplicationInfo applicationInfo;
  private List<ApplicationTransactionInfo> applicationTransactionInfoList;
}
