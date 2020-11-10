package com.st.springcloud;

import com.st.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@RibbonClient(name="CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
@EnableEurekaClient
@SpringBootApplication
public class Order80Main {
    public static void main(String[] args) {
        SpringApplication.run(Order80Main.class);
    }
}
