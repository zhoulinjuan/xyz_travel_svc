package com.xyz.service.dao;

import com.github.pagehelper.Page;
import com.xyz.common.domain.CompanyInfo;
import com.xyz.common.dto.CompanyInfoSearchParam;

public interface CompanyMapper {

  Page<CompanyInfo> searchCompany(CompanyInfoSearchParam companyInfoSearchParam);
}
