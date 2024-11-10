package com.malise.jobApp.job.mapper;

import com.malise.jobApp.job.Job;
import com.malise.jobApp.job.dto.JobWithCompanyDTO;
import com.malise.jobApp.job.external.Company;

public class JobMapper {
    // Mapping between Job and JobWithCompanyDTO
    public static JobWithCompanyDTO mapToJobWithCompanyDTO(Job job, Company company){
        
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setCompany(company);
        
        return jobWithCompanyDTO;
        
    }
    
}
