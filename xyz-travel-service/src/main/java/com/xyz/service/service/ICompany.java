package com.xyz.service.service;

import com.github.pagehelper.Page;
import com.xyz.common.domain.CompanyInfo;
import com.xyz.common.dto.CompanyInfoSearchParam;

public interface ICompany {
  Page<CompanyInfo> search(CompanyInfoSearchParam companyInfoSearchParam);
}
