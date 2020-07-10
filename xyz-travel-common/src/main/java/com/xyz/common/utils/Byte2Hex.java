package com.xyz.common.utils;

import java.util.Formatter;

public class Byte2Hex {
  /**
   * Convert byte to hex 16 String
   *
   * @param b byte
   * @return
   */
  public static String byte2Hex(byte b) {
    String hex = Integer.toHexString(b);
    if (hex.length() > 2) {
      hex = hex.substring(hex.length() - 2);
    }
    StringBuilder bld = new StringBuilder();
    while (hex.length() < 2) {
      bld.append("0").append(hex);
      hex = bld.toString();
    }

    return hex;
  }

  /**
   * Convert byte array to hex 16 String
   *
   * @param bytes Byte array
   * @return
   */
  public static String byte2Hex(byte[] bytes) {
    Formatter formatter = new Formatter();
    for (byte b : bytes) {
      formatter.format("%02x", b);
    }
    String hash = formatter.toString();
    formatter.close();
    return hash;
  }

  private Byte2Hex() {
    throw new IllegalStateException("Utility class");
  }
}
