package com.xyz.service.service;

import com.github.pagehelper.Page;
import com.xyz.common.domain.ApplicationInfo;
import com.xyz.common.domain.ApplicationProcessInfo;
import com.xyz.common.dto.ApplicationInfoSearchParam;
import com.xyz.common.vo.ApplicationDetails;

public interface IApplication {
  Page<ApplicationInfo> search(ApplicationInfoSearchParam applicationInfoSearchParam);

  ApplicationDetails getApplication(String applicationId);

  void create(ApplicationInfo applicationInfo);

  void update(String applicationId, ApplicationProcessInfo applicationProcessInfo);
}
