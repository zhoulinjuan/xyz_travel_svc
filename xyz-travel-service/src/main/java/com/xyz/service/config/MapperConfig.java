package com.xyz.service.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xyz.service.dao")
public class MapperConfig {}
