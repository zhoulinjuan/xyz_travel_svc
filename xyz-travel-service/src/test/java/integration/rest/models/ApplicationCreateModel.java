package integration.rest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xyz.common.vo.CommonPaginator;
import com.xyz.common.vo.CommonStatus;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class ApplicationCreateModel {

  @Getter
  @Setter
  public static class Request {
    private String applicationId;
    private String uen;
    private String employeeName;
    private String employeeId;
    private String employeePassport;
    private String employeeOriginCountry;
    private String employeeDestinationCountry;
    private String employeeTravelFromDate;
    private String employeeTravelToDate;
    private Date submitDatetime;
    private String submitBy;
    private Date lastUpdateDatetime;
    private String lastUpdateBy;
    private Double travelCost;
    private String status;
    private String remarks;
    private Object travelPath;
    private String companyName;
  }

  @Getter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {
    //        @JsonProperty("data")
    //        private List<ApplicationInfo> applicationInfoList;

    @JsonProperty("status")
    private CommonStatus status;

    @JsonProperty("paginator")
    private CommonPaginator paginator;
  }
}
