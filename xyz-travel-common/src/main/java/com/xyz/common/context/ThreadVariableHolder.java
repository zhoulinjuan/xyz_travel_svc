package com.xyz.common.context;

import com.xyz.common.constants.XyzConstant;
import java.util.HashMap;
import java.util.Map;

/** Static method to keep tenant info */
public class ThreadVariableHolder {

  private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

  /**
   * Get attributes from thread
   *
   * @param attribute name
   * @return attribute value
   */
  public static Object get(String attribute) {
    Map map = (Map) threadLocal.get();
    if (map == null) {
      return null;
    }
    return map.get(attribute);
  }

  /** Current User Token */
  public static String getUserToken() {
    Map map = (Map) threadLocal.get();
    if (map == null) {
      return null;
    }
    Object obj = map.get(XyzConstant.XYZ_USER_TOKEN);

    if (obj != null) {
      return (String) obj;
    }
    return null;
  }

  public static void setUserToken(String currentUserToken) {
    Map map = (Map) threadLocal.get();

    if (map == null) {
      map = new HashMap(10);
      threadLocal.set(map);
    }
    map.put(XyzConstant.XYZ_USER_TOKEN, currentUserToken);
  }

  /** Clear thread data */
  public static void clearThreadVariable() {
    threadLocal.remove();
  }

  private ThreadVariableHolder() {
    throw new IllegalStateException("Utility class");
  }
}
