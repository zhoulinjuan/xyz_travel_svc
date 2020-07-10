package integration.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xyz.common.domain.CompanyInfo;
import com.xyz.common.dto.BaseQueryPaginator;
import com.xyz.common.vo.CommonPaginator;
import com.xyz.common.vo.CommonStatus;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class CompanySearchModel {

  @Getter
  @Setter
  public static class Request {
    private String uen;
    private String companyName;
    private String orderUEN;
    private String orderCompanyName;

    @JsonProperty("paginator")
    private BaseQueryPaginator baseQueryPaginator;
  }

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {
    @JsonProperty("data")
    private List<CompanyInfo> companyInfoList;

    @JsonProperty("status")
    private CommonStatus status;

    @JsonProperty("paginator")
    private CommonPaginator paginator;
  }
}
