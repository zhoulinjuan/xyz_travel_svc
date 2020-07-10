package integration.rest.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xyz.common.vo.CommonStatus;
import lombok.Getter;

@Getter
public class StatusResponseModelBase {
  @JsonProperty("status")
  private CommonStatus status;
}
