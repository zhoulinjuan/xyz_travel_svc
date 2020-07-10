package com.xyz.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xyz.common.domain.ApplicationTransactionInfo;
import com.xyz.common.dto.ApplicationTransactionInfoSearchParam;
import com.xyz.common.utils.PaginatorUtil;
import com.xyz.common.vo.RestException;
import com.xyz.service.dao.ApplicationTransactionMapper;
import com.xyz.service.service.IApplicationTransaction;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationTransactionImpl implements IApplicationTransaction {

  @Autowired ApplicationTransactionMapper applicationTransactionMapper;

  @Override
  public Page<ApplicationTransactionInfo> search(
      ApplicationTransactionInfoSearchParam applicationTransactionInfoSearchParam) {
    if (Objects.isNull(applicationTransactionInfoSearchParam)) {
      throw new RestException(
          "ApplicationTransactionInfoSearchParam object passed can not be null");
    }
    PageHelper.startPage(
        PaginatorUtil.getPaginator(applicationTransactionInfoSearchParam.getBaseQueryPaginator()));
    return applicationTransactionMapper.searchApplicationTransaction(
        applicationTransactionInfoSearchParam);
  }
}
