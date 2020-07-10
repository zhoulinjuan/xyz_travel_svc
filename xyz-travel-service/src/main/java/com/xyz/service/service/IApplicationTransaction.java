package com.xyz.service.service;

import com.github.pagehelper.Page;
import com.xyz.common.domain.ApplicationTransactionInfo;
import com.xyz.common.dto.ApplicationTransactionInfoSearchParam;

public interface IApplicationTransaction {
  Page<ApplicationTransactionInfo> search(
      ApplicationTransactionInfoSearchParam applicationTransactionInfoSearchParam);
}
