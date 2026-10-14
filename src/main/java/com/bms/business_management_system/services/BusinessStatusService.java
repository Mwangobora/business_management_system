package com.bms.business_management_system.services;

import org.springframework.stereotype.Service;

@Service 
public class BusinessStatusService {

    public String getStatus()
    {
      return "Business management system is running";
    }
    
}
