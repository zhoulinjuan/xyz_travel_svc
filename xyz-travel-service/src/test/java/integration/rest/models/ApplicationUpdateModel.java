package integration.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xyz.common.vo.CommonPaginator;
import com.xyz.common.vo.CommonStatus;
import lombok.Getter;
import lombok.Setter;

public class ApplicationUpdateModel {

  @Getter
  @Setter
  public static class Request {
    private Double travelCost;
    private String status;
    private String remarks;
  }

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {
    @JsonProperty("status")
    private CommonStatus status;

    @JsonProperty("paginator")
    private CommonPaginator paginator;
  }
}
