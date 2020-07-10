package com.xyz.service.dao;

import com.github.pagehelper.Page;
import com.xyz.common.domain.ApplicantInfo;
import com.xyz.common.dto.ApplicantInfoSearchParam;

public interface ApplicantMapper {

  Page<ApplicantInfo> searchApplicant(ApplicantInfoSearchParam applicantInfoSearchParam);
}
