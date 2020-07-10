package com.xyz.service.controller;

import com.xyz.common.constants.ResponseCode;
import com.xyz.common.domain.*;
import com.xyz.common.dto.ApplicantInfoSearchParam;
import com.xyz.common.dto.ApplicationInfoSearchParam;
import com.xyz.common.dto.ApplicationTransactionInfoSearchParam;
import com.xyz.common.dto.CompanyInfoSearchParam;
import com.xyz.common.utils.ResultVoUtil;
import com.xyz.common.vo.*;
import com.xyz.service.service.IApplicant;
import com.xyz.service.service.IApplication;
import com.xyz.service.service.IApplicationTransaction;
import com.xyz.service.service.ICompany;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/travel")
@ApiResponses({
  @ApiResponse(code = 500, message = "Server error", response = BaseErrorResult.class)
})
public class TravelSvcController {

  @Autowired ICompany iCompany;
  @Autowired IApplicant iApplicant;
  @Autowired IApplication iApplication;
  @Autowired IApplicationTransaction iApplicationTransaction;

  private static final String URI_API_GET_APPLICATION_DETAILS = "/application/{applicationId}";
  private static final String URI_API_SEARCH_COMPANY = "/company:search";
  private static final String URI_API_SEARCH_APPLICANT = "/applicant:search";
  private static final String URI_API_SEARCH_APPLICATION = "/application:search";
  private static final String URI_API_CREATE_APPLICATION = "/application";
  private static final String URI_API_UPDATE_APPLICATION = "/application/{applicationId}";
  private static final String URI_API_SEARCH_APPLICATION_TRANSACTION =
      "/application/transaction:search";

  @ApiOperation("Get Application full details")
  @GetMapping(value = URI_API_GET_APPLICATION_DETAILS)
  public BaseResult getApplicationCase(@PathVariable("applicationId") String applicationId) {
    BaseObjectResult<ApplicationDetails> applicationCaseBaseObjectResult = new BaseObjectResult<>();
    CommonStatus commonStatus = new CommonStatus(ResponseCode.SUCCESS);
    applicationCaseBaseObjectResult.setStatus(commonStatus);
    applicationCaseBaseObjectResult.setData(iApplication.getApplication(applicationId));
    log.info("getApplicationCase -> applicationId {}", applicationId);
    return applicationCaseBaseObjectResult;
  }

  @ApiOperation(
      value = "Search company by some parameters",
      response = CompanyInfo.class,
      responseContainer = "List")
  @PostMapping(value = URI_API_SEARCH_COMPANY)
  public BaseResult searchCompany(
      @Validated @RequestBody CompanyInfoSearchParam companyInfoSearchParam) {
    BasePageResult pageResult =
        ResultVoUtil.generatePageResult(iCompany.search(companyInfoSearchParam), "");
    return pageResult;
  }

  @ApiOperation(
      value = "Search applicant by some parameters",
      response = ApplicantInfo.class,
      responseContainer = "List")
  @PostMapping(value = URI_API_SEARCH_APPLICANT)
  public BaseResult searchApplicant(
      @Validated @RequestBody ApplicantInfoSearchParam applicantInfoSearchParam) {
    BasePageResult pageResult =
        ResultVoUtil.generatePageResult(iApplicant.search(applicantInfoSearchParam), "");
    return pageResult;
  }

  @ApiOperation(
      value = "Search application by some parameters",
      response = ApplicationInfo.class,
      responseContainer = "List")
  @PostMapping(value = URI_API_SEARCH_APPLICATION)
  public BaseResult searchApplication(
      @Validated @RequestBody ApplicationInfoSearchParam applicationInfoSearchParam) {
    BasePageResult pageResult =
        ResultVoUtil.generatePageResult(iApplication.search(applicationInfoSearchParam), "");
    return pageResult;
  }

  @ApiOperation(value = "Create application")
  @PostMapping(value = URI_API_CREATE_APPLICATION)
  public ResponseEntity<BaseResult> createApplication(
      @Validated @RequestBody ApplicationInfo applicationInfo) {
    BaseResult baseObjectResult = new BaseResult();
    try {
      iApplication.create(applicationInfo);
      baseObjectResult.setStatus(new CommonStatus(ResponseCode.SUCCESS));
      return ResponseEntity.ok(baseObjectResult);
    } catch (IllegalArgumentException e) {
      CommonStatus status = new CommonStatus(ResponseCode.FAILED);
      status.setDetails(e.getMessage());
      baseObjectResult.setStatus(status);
      return ResponseEntity.ok(baseObjectResult);
    }
  }

  @ApiOperation(value = "Update application")
  @PutMapping(value = URI_API_UPDATE_APPLICATION)
  public ResponseEntity<BaseResult> updateApplication(
      @PathVariable("applicationId") String applicationId,
      @Validated @RequestBody ApplicationProcessInfo applicationProcessInfo) {
    BaseResult baseObjectResult = new BaseResult();
    try {
      iApplication.update(applicationId, applicationProcessInfo);
      baseObjectResult.setStatus(new CommonStatus(ResponseCode.SUCCESS));
      return ResponseEntity.ok(baseObjectResult);
    } catch (IllegalArgumentException e) {
      CommonStatus status = new CommonStatus(ResponseCode.FAILED);
      status.setDetails(e.getMessage());
      baseObjectResult.setStatus(status);
      return ResponseEntity.ok(baseObjectResult);
    }
  }

  @ApiOperation(
      value = "Search application transaction by some parameters",
      response = ApplicationInfo.class,
      responseContainer = "List")
  @PostMapping(value = URI_API_SEARCH_APPLICATION_TRANSACTION)
  public BaseResult searchApplicationTransaction(
      @Validated @RequestBody
          ApplicationTransactionInfoSearchParam applicationTransactionInfoSearchParam) {
    BasePageResult pageResult =
        ResultVoUtil.generatePageResult(
            iApplicationTransaction.search(applicationTransactionInfoSearchParam), "");
    return pageResult;
  }
}
