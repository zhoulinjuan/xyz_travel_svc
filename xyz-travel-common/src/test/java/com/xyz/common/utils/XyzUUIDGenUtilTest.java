package com.xyz.common.utils;

import static org.junit.Assert.*;

import com.xyz.common.domain.ApplicationInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class XyzUUIDGenUtilTest {

  @Test
  public void testGenApplicationId() {
    ApplicationInfo applicationInfo = new ApplicationInfo();
    applicationInfo.setEmployeeId("A");
    applicationInfo.setUen("T00SS0080D");
    String applicationUUID = XyzUUIDGenUtil.genApplicationId(applicationInfo);
    log.info(applicationUUID);
    Assert.assertNotNull(applicationUUID);
  }
}
