package integration.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.xyz.common.dto.BaseQueryPaginator;
import com.xyz.common.utils.JsonUtil;
import integration.IntegrationTestBase;
import integration.rest.models.*;
import java.util.Random;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

@Slf4j
public class TravelApiTest extends IntegrationTestBase {

  private static final Random r = new Random();

  @Nested
  public class SearchApplicant {
    private ApplicantModel.Request applicantSearchRequest;

    @BeforeEach
    public void setup() {
      applicantSearchRequest = new ApplicantModel.Request();
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSet() {

      Response<ApplicantModel.Response> response =
          travelApiClient.searchApplicant(applicantSearchRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }
  }

  @Nested
  public class SearchApplication {
    private ApplicationSearchModel.Request applicantSearchRequest;

    @BeforeEach
    public void setup() {
      applicantSearchRequest = new ApplicationSearchModel.Request();
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSet() {

      Response<ApplicationSearchModel.Response> response =
          travelApiClient.searchApplication(applicantSearchRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenSetUEN() {

      applicantSearchRequest.setUen("E11223344E");
      Response<ApplicationSearchModel.Response> response =
          travelApiClient.searchApplication(applicantSearchRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }
  }

  @Nested
  public class GetApplicationDetails {
    //        private ApplicationSearchModel.Request applicantSearchRequest;

    @BeforeEach
    public void setup() {
      //            applicantSearchRequest = new ApplicationSearchModel.Request();
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSet() {

      Response<ApplicationDetailsModel.Response> response =
          travelApiClient.getApplicationDetails("2020_T00SS0080D_0705001", "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }

    @Test
    @SneakyThrows
    public void shouldReturn400WhenWrongApplicationId() {

      Response<ApplicationDetailsModel.Response> response =
          travelApiClient.getApplicationDetails("!@#$%^%$&%^*((WRONG ID", "").execute();

      assertThat(response.code()).isEqualTo(400);
      assertThatStatusExist(response.errorBody().string());
    }

    @Test
    @SneakyThrows
    public void shouldReturn400WhenNullApplicationId() {

      Response<ApplicationDetailsModel.Response> response =
          travelApiClient.getApplicationDetails("null", "").execute();

      assertThat(response.code()).isEqualTo(400);
      assertThatStatusExist(response.errorBody().string());
    }
  }

  @Nested
  public class CreateApplicationDetails {
    private ApplicationCreateModel.Request applicationCreateModelRequest;

    @BeforeEach
    public void setup() {
      applicationCreateModelRequest = new ApplicationCreateModel.Request();
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSet() {

      applicationCreateModelRequest.setEmployeeId("empid");

      Response<ApplicationCreateModel.Response> response =
          travelApiClient
              .createApplicationDetails(applicationCreateModelRequest, "mike@xyz.com")
              .execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }

    @Test
    @SneakyThrows
    public void shouldReturn400WhenWrongApplicationId() {

      applicationCreateModelRequest.setApplicationId("SHOULD NOT HAVE");

      Response<ApplicationCreateModel.Response> response =
          travelApiClient.createApplicationDetails(applicationCreateModelRequest, "").execute();

      assertThat(response.code()).isEqualTo(400);
      assertThatStatusExist(response.errorBody().string());
    }
  }

  @Nested
  public class UpdateApplicationDetails {
    private ApplicationUpdateModel.Request applicationUpdateModelRequest;

    @BeforeEach
    public void setup() {
      applicationUpdateModelRequest = new ApplicationUpdateModel.Request();
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSet() {

      applicationUpdateModelRequest.setRemarks("remarks");
      applicationUpdateModelRequest.setStatus("STATUS");
      applicationUpdateModelRequest.setTravelCost(0.1);

      Response<ApplicationUpdateModel.Response> response =
          travelApiClient
              .updateApplicationDetails(
                  "2020_T00SS0080D_0705001", applicationUpdateModelRequest, "isen@agent.com")
              .execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }

    @Test
    @SneakyThrows
    public void shouldReturn400WhenWrongApplicationId() {

      applicationUpdateModelRequest.setRemarks("remarks");
      applicationUpdateModelRequest.setStatus("STATUS");
      applicationUpdateModelRequest.setTravelCost(0.1);

      Response<ApplicationUpdateModel.Response> response =
          travelApiClient
              .updateApplicationDetails(
                  "WRONG APPLICATION ID", applicationUpdateModelRequest, "isen@agent.com")
              .execute();

      assertThat(response.code()).isEqualTo(400);
      assertThatStatusExist(response.errorBody().string());
    }

    @Test
    @SneakyThrows
    public void shouldReturn400WhenNullApplicationId() {

      Response<ApplicationUpdateModel.Response> response =
          travelApiClient
              .updateApplicationDetails(
                  "2020_T00SS0080D_0705001", applicationUpdateModelRequest, "isen@agent.com")
              .execute();

      assertThat(response.code()).isEqualTo(400);
      assertThatStatusExist(response.errorBody().string());
    }
  }

  @Nested
  public class SearchApplicationTransaction {
    private ApplicationTransactionSearchModel.Request applicationTxnSearchModelRequest;

    @BeforeEach
    public void setup() {
      applicationTxnSearchModelRequest = new ApplicationTransactionSearchModel.Request();
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSet() {

      Response<ApplicationTransactionSearchModel.Response> response =
          travelApiClient.searchApplicationTxn(applicationTxnSearchModelRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSetButPage() {
      BaseQueryPaginator baseQueryPaginator = new BaseQueryPaginator();
      baseQueryPaginator.setPageNum(1);
      baseQueryPaginator.setPageSize(1);
      applicationTxnSearchModelRequest.setBaseQueryPaginator(baseQueryPaginator);
      Response<ApplicationTransactionSearchModel.Response> response =
          travelApiClient.searchApplicationTxn(applicationTxnSearchModelRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }

    @Test
    @SneakyThrows
    public void shouldReturn400WhenNullApplicationId() {

      applicationTxnSearchModelRequest.setApplicationId("WRONG ID");
      Response<ApplicationTransactionSearchModel.Response> response =
          travelApiClient.searchApplicationTxn(applicationTxnSearchModelRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
      assertThat(response.body().getPaginator().getRecordCount()).isEqualTo(0);
    }
  }

  @Nested
  public class SearchCompany {
    private CompanySearchModel.Request companySearchModelRequest;

    @BeforeEach
    public void setup() {
      companySearchModelRequest = new CompanySearchModel.Request();
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSet() {

      Response<CompanySearchModel.Response> response =
          travelApiClient.searchCpmpany(companySearchModelRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }

    @Test
    @SneakyThrows
    public void shouldReturn200WhenallOptionalFieldsAreNotSetButPage() {
      BaseQueryPaginator baseQueryPaginator = new BaseQueryPaginator();
      baseQueryPaginator.setPageNum(1);
      baseQueryPaginator.setPageSize(1);
      companySearchModelRequest.setUen("T00SS0080D");
      companySearchModelRequest.setBaseQueryPaginator(baseQueryPaginator);
      Response<CompanySearchModel.Response> response =
          travelApiClient.searchCpmpany(companySearchModelRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
    }

    @Test
    @SneakyThrows
    public void shouldReturn400WhenWrongUEN() {

      companySearchModelRequest.setUen("WRONG ID");
      Response<CompanySearchModel.Response> response =
          travelApiClient.searchCpmpany(companySearchModelRequest, "").execute();

      log.info("responseBody: {}", JsonUtil.objectToJson(response.body()));
      assertThat(response.code()).isEqualTo(200);
      assertThat(response.body().getStatus().getCode()).isEqualTo("00-0000");
      assertThat(response.body().getPaginator().getRecordCount()).isEqualTo(0);
    }

    @Test
    @SneakyThrows
    public void shouldReturn400WhenWrongUENOrder() {

      BaseQueryPaginator baseQueryPaginator = new BaseQueryPaginator();
      baseQueryPaginator.setPageNum(1);
      baseQueryPaginator.setPageSize(1);
      companySearchModelRequest.setUen("T00SS0080D");
      companySearchModelRequest.setBaseQueryPaginator(baseQueryPaginator);
      companySearchModelRequest.setOrderUEN("WRONG ORDER");
      Response<CompanySearchModel.Response> response =
          travelApiClient.searchCpmpany(companySearchModelRequest, "").execute();
      assertThat(response.code()).isEqualTo(400);
      assertThatStatusExist(response.errorBody().string());
    }

    @Test
    @SneakyThrows
    public void shouldReturn405WhenMethodNotSupportException() {

      BaseQueryPaginator baseQueryPaginator = new BaseQueryPaginator();
      baseQueryPaginator.setPageNum(1);
      baseQueryPaginator.setPageSize(1);
      companySearchModelRequest.setUen("T00SS0080D");
      companySearchModelRequest.setBaseQueryPaginator(baseQueryPaginator);
      Response<CompanySearchModel.Response> response =
          travelApiClient.exceptionMethodNotSupportCpmpany(companySearchModelRequest, "").execute();
      assertThat(response.code()).isEqualTo(405);
      assertThatStatusExist(response.errorBody().string());
    }

    @Test
    @SneakyThrows
    public void shouldReturn415WhenContentTypeNotSupportException() {

      BaseQueryPaginator baseQueryPaginator = new BaseQueryPaginator();
      baseQueryPaginator.setPageNum(1);
      baseQueryPaginator.setPageSize(1);
      companySearchModelRequest.setUen("T00SS0080D");
      companySearchModelRequest.setBaseQueryPaginator(baseQueryPaginator);
      Response<CompanySearchModel.Response> response =
          travelApiClient
              .exceptionContentTypeNotSupportCpmpany(companySearchModelRequest, "", "image/bmp")
              .execute();
      assertThat(response.code()).isEqualTo(415);
      assertThatStatusExist(response.errorBody().string());
    }
  }
}
