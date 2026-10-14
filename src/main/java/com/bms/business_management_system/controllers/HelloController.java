package com.bms.business_management_system.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.business_management_system.services.BusinessStatusService;

@RestController 
public class HelloController {

    private  BusinessStatusService businessStatusService;

    public HelloController(BusinessStatusService businessStatusService)
    {
        this.businessStatusService = businessStatusService;
    }

    
    @GetMapping("/hello")
    public String hello(){
        return "Welcome to business management system";
    }

    @GetMapping("/status")
    public String getStatus(){
        return businessStatusService.getStatus();
    }
    
}
