package integration.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xyz.common.vo.ApplicationDetails;
import com.xyz.common.vo.CommonPaginator;
import com.xyz.common.vo.CommonStatus;
import lombok.Getter;

public class ApplicationDetailsModel {

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {
    @JsonProperty("data")
    private ApplicationDetails applicationDetails;

    @JsonProperty("status")
    private CommonStatus status;

    @JsonProperty("paginator")
    private CommonPaginator paginator;
  }
}
