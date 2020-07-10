package com.xyz.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseQueryParam implements Serializable {

  private static final long serialVersionUID = 1287597524568162341L;

  @JsonProperty("paginator")
  private BaseQueryPaginator baseQueryPaginator;
}
