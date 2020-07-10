package integration.rest;

import integration.rest.models.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface TravelApi {

  @HTTP(method = "POST", path = "/api/travel/applicant:search", hasBody = true)
  Call<ApplicantModel.Response> searchApplicant(
      @Body ApplicantModel.Request request, @Header("user_token") String userToken);

  @HTTP(method = "POST", path = "/api/travel/application:search", hasBody = true)
  Call<ApplicationSearchModel.Response> searchApplication(
      @Body ApplicationSearchModel.Request request, @Header("user_token") String userToken);

  @HTTP(method = "GET", path = "/api/travel/application/{applicationId}", hasBody = false)
  Call<ApplicationDetailsModel.Response> getApplicationDetails(
      @Path("applicationId") String applicationId, @Header("user_token") String userToken);

  @HTTP(method = "POST", path = "/api/travel/application", hasBody = true)
  Call<ApplicationCreateModel.Response> createApplicationDetails(
      @Body ApplicationCreateModel.Request request, @Header("user_token") String userToken);

  @HTTP(method = "PUT", path = "/api/travel/application/{applicationId}", hasBody = true)
  Call<ApplicationUpdateModel.Response> updateApplicationDetails(
      @Path("applicationId") String applicationId,
      @Body ApplicationUpdateModel.Request request,
      @Header("user_token") String userToken);

  @HTTP(method = "POST", path = "/api/travel/application/transaction:search", hasBody = true)
  Call<ApplicationTransactionSearchModel.Response> searchApplicationTxn(
      @Body ApplicationTransactionSearchModel.Request request,
      @Header("user_token") String userToken);

  @HTTP(method = "POST", path = "/api/travel/company:search", hasBody = true)
  Call<CompanySearchModel.Response> searchCpmpany(
      @Body CompanySearchModel.Request request, @Header("user_token") String userToken);

  @HTTP(method = "DELETE", path = "/api/travel/company:search", hasBody = true)
  Call<CompanySearchModel.Response> exceptionMethodNotSupportCpmpany(
      @Body CompanySearchModel.Request request, @Header("user_token") String userToken);

  @HTTP(method = "POST", path = "/api/travel/company:search", hasBody = true)
  Call<CompanySearchModel.Response> exceptionContentTypeNotSupportCpmpany(
      @Body CompanySearchModel.Request request,
      @Header("user_token") String userToken,
      @Header("Content-Type") String content_type);
}
