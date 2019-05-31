package com.quicktutorial.learnmicroservices.accountMicroservices.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class RestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello everybody";
    }
}
