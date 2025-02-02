package com.st.springcloud.controller;

import com.st.springcloud.entities.CommonResult;
import com.st.springcloud.entities.Payment;
import com.st.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("PaymentController=>create，serverPort："+serverPort+"，插入结果，："+result);
        if(result>0){
            return new CommonResult(200,"插入数据成功，serverPort："+serverPort,result);
        }
        return new CommonResult(444,"插入数据失败，serverPort："+serverPort,result);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment=paymentService.getPaymentById(id);
        log.info("PaymentController=>getPaymentById，serverPort："+serverPort+"，查询结果："+payment);
        if(null!=payment){
            return new CommonResult(200,"查询数据成功，serverPort："+serverPort,payment);
        }
        return new CommonResult(444,"查询数据失败，serverPort："+serverPort,null);
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();}
        return serverPort;
    }
}
