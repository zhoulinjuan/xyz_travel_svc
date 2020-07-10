package com.xyz.service.service;

import com.github.pagehelper.Page;
import com.xyz.common.domain.ApplicantInfo;
import com.xyz.common.dto.ApplicantInfoSearchParam;

public interface IApplicant {
  Page<ApplicantInfo> search(ApplicantInfoSearchParam applicantInfoSearchParam);
}
