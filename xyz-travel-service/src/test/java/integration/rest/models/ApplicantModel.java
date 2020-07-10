package integration.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xyz.common.domain.ApplicantInfo;
import com.xyz.common.vo.CommonPaginator;
import com.xyz.common.vo.CommonStatus;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class ApplicantModel {

  @Getter
  @Setter
  public static class Request {
    @JsonProperty("uen")
    private String uen;

    @JsonProperty("applicantName")
    private String applicantName;

    @JsonProperty("applicantContact")
    private String applicantContact;

    @JsonProperty("applicantEmail")
    private String applicantEmail;
  }

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {
    @JsonProperty("data")
    private List<ApplicantInfo> applicantInfoList;

    @JsonProperty("status")
    private CommonStatus status;

    @JsonProperty("paginator")
    private CommonPaginator paginator;
  }
}
