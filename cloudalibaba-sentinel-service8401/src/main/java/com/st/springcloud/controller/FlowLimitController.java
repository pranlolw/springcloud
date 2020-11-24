package com.st.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.st.springcloud.entities.CommonResult;
import com.st.springcloud.entities.Payment;
import com.st.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @SentinelResource(value = "testB",blockHandler = "testB_handleException")
    @GetMapping("/testB")
    public String testB() {

        StringBuilder sb=new StringBuilder().append("ja").append("va");
        System.out.println(sb.toString()==sb.toString().intern());

        return "------testB";
    }
    public String testB_handleException() {
        return "------testB_handleException";
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客戶自定义",new Payment(2020L,"serial003"));
    }

}
