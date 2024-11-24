package com.malise.jobApp.job.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.malise.jobApp.job.external.Company;

@FeignClient(name="COMPANY")
public interface CompanyClient {
    // Add methods to call Company API
    @GetMapping("/company/{id}")
    Company getCompanyById(@PathVariable("id") Long id);
 

}
