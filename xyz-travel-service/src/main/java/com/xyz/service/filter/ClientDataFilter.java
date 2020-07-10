package com.xyz.service.filter;

import com.xyz.common.constants.XyzConstant;
import com.xyz.common.context.ThreadVariableHolder;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ClientDataFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURL = httpRequest.getRequestURL().toString();

    if (httpRequest.getHeader(XyzConstant.XYZ_USER_TOKEN) != null) {

      String userTokenStr = httpRequest.getHeader(XyzConstant.XYZ_USER_TOKEN);
      if (StringUtils.isNotEmpty(userTokenStr)) {
        ThreadVariableHolder.setUserToken(userTokenStr);

      } else {
        log.info("XYZ_USER_TOKEN Header Details is null for request: {}", requestURL);
      }
    } else {
      log.info("XYZ_USER_TOKEN Header is null for request: {}", requestURL);
    }
    log.info(
        "ThreadVariableHolder current XYZ_USER_TOKEN -> {}",
        ThreadVariableHolder.get(XyzConstant.XYZ_USER_TOKEN));
    chain.doFilter(request, response);
  }
}
