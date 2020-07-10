package com.xyz.common.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.xyz.common.constants.ResponseCode;
import com.xyz.common.vo.*;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class ResultVoUtil {
  private ResultVoUtil() {}

  public static CommonPaginator generatePaginator(PageInfo data) {
    if (Objects.isNull(data)) {
      data = new PageInfo();
    }

    return new CommonPaginator(
        data.getPageNum(), data.getPageSize(), data.getPages(), data.getTotal());
  }

  /** Return pagination data with paginator */
  public static BasePageResult generatePageResult(Page page, String details) {
    if (Objects.nonNull(page)) {
      return generatePageResult(page.toPageInfo(), details);
    } else {
      return null;
    }
  }

  /** Return pagination data with paginator */
  public static BasePageResult generatePageResult(PageInfo pageInfo, String details) {
    if (Objects.isNull(pageInfo)) {
      pageInfo = new PageInfo();
    }
    CommonPaginator commonPaginator = generatePaginator(pageInfo);

    BasePageResult basePageResult = new BasePageResult();
    basePageResult.setCommonPaginator(commonPaginator);
    CommonStatus commonStatus = new CommonStatus();
    commonStatus.setCode(ResponseCode.SUCCESS.getCode());
    commonStatus.setMessage(ResponseCode.SUCCESS.getMsg());
    commonStatus.setDetails(details);
    basePageResult.setStatus(commonStatus);
    basePageResult.setData(pageInfo.getList());

    return basePageResult;
  }

  public static BaseErrorResult failed(String code, String message) {
    CommonStatus commonStatus = new CommonStatus();
    commonStatus.setCode(code);
    commonStatus.setMessage(message);
    return new BaseErrorResult(commonStatus);
  }

  public static BaseErrorResult failed(String code, String message, String details) {
    CommonStatus commonStatus = new CommonStatus();
    commonStatus.setCode(code);
    commonStatus.setMessage(message);
    commonStatus.setDetails(details);
    return new BaseErrorResult(commonStatus);
  }

  public static BaseErrorResult failed(RestException exceptiont) {
    CommonStatus commonStatus = new CommonStatus();

    if (StringUtils.isEmpty(exceptiont.getCode())) {
      commonStatus.setCode(ResponseCode.FAILED.getCode());
    } else {
      commonStatus.setCode(exceptiont.getCode());
    }
    if (StringUtils.isEmpty(exceptiont.getMessage())) {
      commonStatus.setMessage(ResponseCode.FAILED.getMsg());
    } else {
      commonStatus.setMessage(exceptiont.getMessage());
    }
    if (exceptiont.getArgs() != null) {
      Object[] objects = exceptiont.getArgs();
      String[] detailsList = Arrays.stream(objects).map(Object::toString).toArray(String[]::new);
      commonStatus.setDetails(StringUtils.join(detailsList, ", "));
    } else {
      commonStatus.setDetails("");
    }

    return new BaseErrorResult(commonStatus);
  }
}
