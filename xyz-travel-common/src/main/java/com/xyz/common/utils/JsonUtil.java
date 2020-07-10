package com.xyz.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JsonUtil {

  /** Define jackson Object */
  private static final ObjectMapper MAPPER = new ObjectMapper();

  /** Convert object to json string */
  public static String objectToJson(Object data) {
    try {
      return MAPPER.writeValueAsString(data);
    } catch (JsonProcessingException e) {
      log.error("objectToJson JsonProcessingException [{}]", e.getStackTrace());
    }
    return null;
  }

  /**
   * Convert json string to Object
   *
   * @param jsonData json data
   * @param beanType object type
   */
  public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
    try {
      return MAPPER.readValue(jsonData, beanType);
    } catch (Exception e) {
      log.error("jsonToPojo Exception [{}]", e.getStackTrace());
    }
    return null;
  }

  public static boolean isJSONValid(String jsonInString) {
    try {
      if (StringUtils.isEmpty(jsonInString) || jsonInString.trim().length() < 3) {
        return false;
      } else {
        MAPPER.readTree(jsonInString);
        return true;
      }
    } catch (IOException e) {
      return false;
    }
  }

  private JsonUtil() {
    throw new IllegalStateException("Utility class");
  }
}
