package com.xyz.service.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xyz.common.constants.ResponseCode;
import com.xyz.common.constants.XyzConstant;
import com.xyz.common.context.ThreadVariableHolder;
import com.xyz.common.domain.*;
import com.xyz.common.dto.ApplicantInfoSearchParam;
import com.xyz.common.dto.ApplicationInfoSearchParam;
import com.xyz.common.dto.ApplicationTransactionInfoSearchParam;
import com.xyz.common.dto.CompanyInfoSearchParam;
import com.xyz.common.utils.PaginatorUtil;
import com.xyz.common.utils.XyzUUIDGenUtil;
import com.xyz.common.vo.ApplicationDetails;
import com.xyz.common.vo.RestException;
import com.xyz.service.dao.ApplicantMapper;
import com.xyz.service.dao.ApplicationMapper;
import com.xyz.service.dao.ApplicationTransactionMapper;
import com.xyz.service.dao.CompanyMapper;
import com.xyz.service.service.IApplication;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationImpl implements IApplication {

  @Autowired ApplicationMapper applicationMapper;

  @Autowired ApplicantMapper applicantMapper;

  @Autowired CompanyMapper companyMapper;

  @Autowired ApplicationTransactionMapper applicationTransactionMapper;

  @Override
  public Page<ApplicationInfo> search(ApplicationInfoSearchParam applicationInfoSearchParam) {
    if (ObjectUtils.isEmpty(applicationInfoSearchParam)) {
      throw new RestException("ApplicationInfoSearchParam object passed can not be null");
    }
    return getApplicationInfosPage(applicationInfoSearchParam);
  }

  private Page<ApplicationInfo> getApplicationInfosPage(
      ApplicationInfoSearchParam applicationInfoSearchParam) {

    PageHelper.startPage(
        PaginatorUtil.getPaginator(applicationInfoSearchParam.getBaseQueryPaginator()));
    return applicationMapper.searchApplication(applicationInfoSearchParam);
  }

  @Override
  public ApplicationDetails getApplication(String applicationId) {
    if (StringUtils.isEmpty(applicationId)) {
      throw new RestException("ApplicationId is empty");
    }

    ApplicationDetails applicationDetails = new ApplicationDetails();

    // Get Application Info
    ApplicationInfoSearchParam applicationInfoSearchParam = new ApplicationInfoSearchParam();
    applicationInfoSearchParam.setApplicationId(applicationId);
    Page<ApplicationInfo> applicationInfoPage =
        applicationMapper.searchApplication(applicationInfoSearchParam);
    log.info("Applicant Info Total -> {}", applicationInfoPage.getTotal());
    if (ObjectUtils.isEmpty(applicationInfoPage)) {
      throw new RestException("Application is empty or more then one for the id " + applicationId);
    }
    ApplicationInfo applicationInfo = applicationInfoPage.getResult().get(0);

    // Get Applicant Info
    ApplicantInfo applicantInfo = getApplicantInfoByUserId(applicationInfo.getSubmitBy());

    // Get Company Info
    CompanyInfo companyInfo = getCompanyInfoByUen(applicationInfo.getUen());

    // Get Application Transaction Info
    ApplicationTransactionInfoSearchParam applicationTransactionInfoSearchParam =
        new ApplicationTransactionInfoSearchParam();
    applicationTransactionInfoSearchParam.setApplicationId(applicationId);
    Page<ApplicationTransactionInfo> applicationTransactionInfoPage =
        applicationTransactionMapper.searchApplicationTransaction(
            applicationTransactionInfoSearchParam);
    List<ApplicationTransactionInfo> applicationTransactionInfoList =
        applicationTransactionInfoPage.getResult();

    applicationDetails.setApplicationInfo(applicationInfo);
    applicationDetails.setApplicantInfo(applicantInfo);
    applicationDetails.setCompanyInfo(companyInfo);
    applicationDetails.setApplicationTransactionInfoList(applicationTransactionInfoList);
    return applicationDetails;
  }

  @Override
  public void create(ApplicationInfo applicationInfo) {
    if (ObjectUtils.isEmpty(applicationInfo)) {
      throw new RestException("ApplicationInfo object passed can not be null");
    }
    if (StringUtils.isNotEmpty(applicationInfo.getApplicationId())) {
      throw new RestException("ApplicationId isNotEmpty");
    }

    ApplicantInfo applicantInfo = getApplicantInfoByUserId(ThreadVariableHolder.getUserToken());
    CompanyInfo companyInfo = getCompanyInfoByUen(applicantInfo.getUen());

    applicationInfo.setUen(companyInfo.getUen());
    applicationInfo.setCompanyName(companyInfo.getCompanyName());
    applicationInfo.setApplicationId(XyzUUIDGenUtil.genApplicationId(applicationInfo));

    applicationInfo.setLastUpdateDatetime(new Date());
    applicationInfo.setLastUpdateBy(ThreadVariableHolder.getUserToken());
    applicationInfo.setSubmitDatetime(new Date());
    applicationInfo.setSubmitBy(ThreadVariableHolder.getUserToken());
    applicationInfo.setStatus(XyzConstant.APPLICATION_INIT_STATUS);
    int count = applicationMapper.insertSelective(applicationInfo);
    log.info("Insert Count {}", count);
  }

  @Override
  public void update(String applicationId, ApplicationProcessInfo applicationProcessInfo) {
    if (ObjectUtils.isEmpty(applicationProcessInfo)) {
      throw new RestException("ApplicationProcessInfo object passed can not be null");
    }
    if (StringUtils.isEmpty(applicationId)) {
      throw new RestException("ApplicationId isEmpty");
    }
    if (applicationProcessInfo.getTravelCost() == null
        || StringUtils.isEmpty(applicationProcessInfo.getStatus())) {
      throw new RestException("GetTravelCost or GetStatus can not be null");
    }

    ApplicationInfoSearchParam applicationInfoSearchParam = new ApplicationInfoSearchParam();
    applicationInfoSearchParam.setApplicationId(applicationId);
    Page<ApplicationInfo> applicationInfoPage =
        applicationMapper.searchApplication(applicationInfoSearchParam);
    if (ObjectUtils.isEmpty(applicationInfoPage)) {
      throw new RestException("ApplicationInfo not found for applicationId -> " + applicationId);
    }

    Date lastUpdateDatetime = new Date();
    int count =
        applicationMapper.updateBySelective(
            applicationProcessInfo,
            applicationId,
            ThreadVariableHolder.getUserToken(),
            lastUpdateDatetime);
    log.info("Update Count {}", count);

    // Insert into application transaction for successful updated records
    if (count > 0) {
      ApplicationTransactionInfo applicationTransactionInfo = new ApplicationTransactionInfo();
      BeanUtils.copyProperties(applicationProcessInfo, applicationTransactionInfo);
      applicationTransactionInfo.setApplicationId(applicationId);
      applicationTransactionInfo.setLastUpdateBy(ThreadVariableHolder.getUserToken());
      applicationTransactionInfo.setLastUpdateDatetime(lastUpdateDatetime);
      log.info("applicationTransactionInfo -> {}", applicationTransactionInfo);
      int countUpdateTxn = applicationTransactionMapper.insertSelective(applicationTransactionInfo);
      log.info("Update countUpdateTxn {}", countUpdateTxn);
    }
  }

  private ApplicantInfo getApplicantInfoByUserId(String applicantEmailId) {
    ApplicantInfoSearchParam applicantInfoSearchParam = new ApplicantInfoSearchParam();
    applicantInfoSearchParam.setApplicantEmail(applicantEmailId);
    Page<ApplicantInfo> applicantInfoPage =
        applicantMapper.searchApplicant(applicantInfoSearchParam);
    if (ObjectUtils.isEmpty(applicantInfoPage)) {
      throw new RestException(
          ResponseCode.VALIDATE_FAILED,
          "Applicant is empty or more then one for the email " + applicantEmailId);
    }
    ApplicantInfo applicantInfo = applicantInfoPage.getResult().get(0);
    return applicantInfo;
  }

  private CompanyInfo getCompanyInfoByUen(String uen) {
    CompanyInfoSearchParam companyInfoSearchParam = new CompanyInfoSearchParam();
    companyInfoSearchParam.setUen(uen);
    Page<CompanyInfo> companyInfoPage = companyMapper.searchCompany(companyInfoSearchParam);
    if (ObjectUtils.isEmpty(companyInfoPage)) {
      throw new RestException(
          ResponseCode.VALIDATE_FAILED, "Company is empty or more then one for the UEN " + uen);
    }
    return companyInfoPage.getResult().get(0);
  }
}
