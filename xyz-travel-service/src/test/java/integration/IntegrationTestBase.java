package integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.xyz.common.utils.JsonUtil;
import com.xyz.common.vo.CommonStatus;
import com.xyz.service.TravelServiceApplication;
import integration.rest.TravelApi;
import integration.rest.base.StatusResponseModelBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Slf4j
@SpringBootTest(
    classes = TravelServiceApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public abstract class IntegrationTestBase {
  private static final PostgreSQLContainer postgres =
      new PostgreSQLContainer<>("postgres:11.1")
          .withDatabaseName("postgres")
          .withUsername("postgres")
          .withPassword("abcd1234")
          .withClasspathResourceMapping("/sql", "/docker-entrypoint-initdb.d", BindMode.READ_ONLY);

  @DynamicPropertySource
  private static void postgresProperties(DynamicPropertyRegistry registry) {
    postgres.start();

    registry.add("spring.datasource.url", () -> postgres.getJdbcUrl() + "&stringtype=unspecified");
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  protected TravelApi travelApiClient;

  @LocalServerPort public int localServicePort;

  @BeforeEach
  public void retrofitSetup() {
    travelApiClient =
        new Retrofit.Builder()
            .baseUrl("http://localhost:" + localServicePort)
            .addConverterFactory((JacksonConverterFactory.create()))
            .build()
            .create(TravelApi.class);
  }

  protected static void assertThatStatusExist(String responseBody) {
    log.info("assertThatStatusExist for -> {} ", responseBody);
    if (JsonUtil.isJSONValid(responseBody)) {
      StatusResponseModelBase response =
          JsonUtil.jsonToPojo(responseBody, StatusResponseModelBase.class);

      CommonStatus status = response.getStatus();
      assertThat(status).isNotNull();
      assertThat(status.getCode()).isNotEmpty();
      assertThat(status.getMessage()).isNotEmpty();
      //    assertThat(status.getDetails()).isNotEmpty();
    }
  }
}
