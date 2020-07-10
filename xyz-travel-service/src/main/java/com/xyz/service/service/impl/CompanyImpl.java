package com.xyz.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xyz.common.domain.CompanyInfo;
import com.xyz.common.dto.CompanyInfoSearchParam;
import com.xyz.common.utils.PaginatorUtil;
import com.xyz.common.vo.RestException;
import com.xyz.service.dao.CompanyMapper;
import com.xyz.service.service.ICompany;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CompanyImpl implements ICompany {

  @Autowired CompanyMapper companyMapper;

  @Override
  public Page<CompanyInfo> search(CompanyInfoSearchParam companyInfoSearchParam) {
    if (Objects.isNull(companyInfoSearchParam)) {
      throw new RestException("CompanyInfoSearchParam object passed can not be null");
    }
    PageHelper.startPage(
        PaginatorUtil.getPaginator(companyInfoSearchParam.getBaseQueryPaginator()));
    return companyMapper.searchCompany(companyInfoSearchParam);
  }
}
