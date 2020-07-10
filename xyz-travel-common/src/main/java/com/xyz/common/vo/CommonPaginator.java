package com.xyz.common.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonPaginator implements Serializable {
  private static final long serialVersionUID = -4835175730572523240L;

  private long currentPage = 1;
  private long pageSize;
  private long pageCount = 1;
  private long recordCount;
}
