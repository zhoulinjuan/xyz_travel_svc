package com.xyz.common.utils;

import com.xyz.common.constants.ResponseCode;
import com.xyz.common.domain.ApplicationInfo;
import com.xyz.common.vo.RestException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class XyzUUIDGenUtil {
  private static final Random RANDOM = new SecureRandom();

  public static String genApplicationId(ApplicationInfo applicationInfo) {

    if (applicationInfo != null && !StringUtils.isEmpty(applicationInfo.getUen())) {
      LocalDate currentDate = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
      Instant instant = Instant.now();
      StringBuilder stringBuilder =
          new StringBuilder()
              .append(currentDate.format(formatter))
              .append("-")
              .append(applicationInfo.getUen())
              .append("-")
              .append(MD5Util.toMD5(instant.getNano() + "" + RANDOM.nextInt()));

      return stringBuilder.toString();
    } else {
      throw new RestException(ResponseCode.FAILED, "Uen is empty or EmployeeId is empty");
    }
  }
}
