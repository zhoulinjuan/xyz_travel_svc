package com.xyz.service.dao;

import com.github.pagehelper.Page;
import com.xyz.common.domain.ApplicationTransactionInfo;
import com.xyz.common.dto.ApplicationTransactionInfoSearchParam;

public interface ApplicationTransactionMapper {

  Page<ApplicationTransactionInfo> searchApplicationTransaction(
      ApplicationTransactionInfoSearchParam applicationTransactionInfoSearchParam);

  int insertSelective(ApplicationTransactionInfo record);
}
