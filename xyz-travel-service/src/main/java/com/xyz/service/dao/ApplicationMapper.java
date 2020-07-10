package com.xyz.service.dao;

import com.github.pagehelper.Page;
import com.xyz.common.domain.ApplicationInfo;
import com.xyz.common.domain.ApplicationProcessInfo;
import com.xyz.common.dto.ApplicationInfoSearchParam;
import java.util.Date;
import org.apache.ibatis.annotations.Param;

public interface ApplicationMapper {

  Page<ApplicationInfo> searchApplication(ApplicationInfoSearchParam applicationInfoSearchParam);

  int insertSelective(ApplicationInfo record);

  int updateBySelective(
      @Param("record") ApplicationProcessInfo record,
      @Param("applicationId") String applicationId,
      @Param("lastUpdateBy") String lastUpdateBy,
      @Param("lastUpdateDatetime") Date lastUpdateDatetime);
}
