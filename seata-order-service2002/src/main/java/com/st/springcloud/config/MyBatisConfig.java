package com.st.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.st.springcloud.dao"})
public class MyBatisConfig {
}
