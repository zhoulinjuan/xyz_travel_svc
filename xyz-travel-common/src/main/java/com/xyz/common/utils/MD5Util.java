package com.xyz.common.utils;

import com.xyz.common.constants.XyzConstant;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MD5Util {
  public static String toMD5(String orgText) {
    StringBuilder rlt = new StringBuilder();
    try {
      rlt.append(md5String(orgText.getBytes(XyzConstant.CHARSET_UTF8)));
    } catch (UnsupportedEncodingException e) {
      log.error(" CipherHelper toMD5 exception. {}", e);
    }
    return rlt.toString();
  }

  public static byte[] md5Byte(byte[] data) {
    byte[] md5buf = null;
    try {
      MessageDigest md5 = MessageDigest.getInstance(XyzConstant.KEY_MD5);
      md5buf = md5.digest(data);
    } catch (Exception e) {
      md5buf = null;
      log.error("md5Byte error. {} ", e);
    }
    return md5buf;
  }

  public static String md5String(byte[] data) {
    StringBuilder bld = new StringBuilder();
    try {
      MessageDigest md5 = MessageDigest.getInstance(XyzConstant.KEY_MD5);
      byte[] buf = md5.digest(data);
      for (int i = 0; i < buf.length; i++) {
        bld.append(Byte2Hex.byte2Hex(buf[i]));
      }
    } catch (Exception e) {
      bld = new StringBuilder();
      log.error("md5String error. {}", e);
    }
    return bld.toString();
  }

  private MD5Util() {
    throw new IllegalStateException("Utility class");
  }
}
