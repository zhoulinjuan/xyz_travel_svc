package com.xyz.common.utils;

import com.xyz.common.constants.XyzConstant;
import com.xyz.common.dto.BaseQueryPaginator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

@Slf4j
public class PaginatorUtil {
  public static int getPageSize(BaseQueryPaginator queryPaginator) {
    int pageSize;
    pageSize =
        (queryPaginator.getPageSize() <= 0)
            ? XyzConstant.PAGE_DEFAULT_PAGE_SIZE
            : queryPaginator.getPageSize();
    pageSize =
        (queryPaginator.getPageSize() > XyzConstant.PAGE_MAX_PAGE_SIZE)
            ? XyzConstant.PAGE_MAX_PAGE_SIZE
            : queryPaginator.getPageSize();
    return pageSize;
  }

  public static int getPageNumner(BaseQueryPaginator queryPaginator) {
    int pageNumner;
    pageNumner =
        (queryPaginator.getPageNum() <= 0)
            ? XyzConstant.PAGE_DEFAULT_PAGE_NUMBER
            : queryPaginator.getPageNum();
    return pageNumner;
  }

  public static BaseQueryPaginator getPaginator(BaseQueryPaginator queryPaginator) {
    BaseQueryPaginator formattedPaginator = new BaseQueryPaginator();
    int pageNumner = XyzConstant.PAGE_DEFAULT_PAGE_NUMBER;
    int pageSize = XyzConstant.PAGE_DEFAULT_PAGE_SIZE;
    if (ObjectUtils.isEmpty(queryPaginator)) {
      log.info("No paginator been setted");
    } else {
      pageNumner = getPageNumner(queryPaginator);
      pageSize = getPageSize(queryPaginator);
    }
    formattedPaginator.setPageSize(pageSize);
    formattedPaginator.setPageNum(pageNumner);
    return formattedPaginator;
  }
}
