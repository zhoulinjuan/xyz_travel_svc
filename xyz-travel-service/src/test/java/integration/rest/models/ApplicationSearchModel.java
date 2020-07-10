package integration.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xyz.common.domain.ApplicationInfo;
import com.xyz.common.vo.CommonPaginator;
import com.xyz.common.vo.CommonStatus;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class ApplicationSearchModel {

  @Getter
  @Setter
  public static class Request {
    private String applicationId;
    private String uen;
    private String employeeName;
    private String employeeId;
    private String employeePassport;
    private String orderEmployeeName;
    private String orderUEN;
  }

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {
    @JsonProperty("data")
    private List<ApplicationInfo> applicationInfoList;

    @JsonProperty("status")
    private CommonStatus status;

    @JsonProperty("paginator")
    private CommonPaginator paginator;
  }
}
