package com.xyz.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xyz.common.domain.ApplicantInfo;
import com.xyz.common.dto.ApplicantInfoSearchParam;
import com.xyz.common.utils.PaginatorUtil;
import com.xyz.common.vo.RestException;
import com.xyz.service.dao.ApplicantMapper;
import com.xyz.service.service.IApplicant;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicantImpl implements IApplicant {

  @Autowired ApplicantMapper applicantMapper;

  @Override
  public Page<ApplicantInfo> search(ApplicantInfoSearchParam applicantInfoSearchParam) {
    if (Objects.isNull(applicantInfoSearchParam)) {
      throw new RestException("ApplicantInfoSearchParam object passed can not be null");
    }
    PageHelper.startPage(
        PaginatorUtil.getPaginator(applicantInfoSearchParam.getBaseQueryPaginator()));
    return applicantMapper.searchApplicant(applicantInfoSearchParam);
  }
}
