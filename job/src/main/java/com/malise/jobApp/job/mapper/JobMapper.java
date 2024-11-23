package com.malise.jobApp.job.mapper;

import java.util.List;

import com.malise.jobApp.job.Job;
import com.malise.jobApp.job.dto.JobDTO;
import com.malise.jobApp.job.external.Company;
import com.malise.jobApp.job.external.Review;

public class JobMapper {
    // Mapping between Job and JobWithCompanyDTO
    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews) {
        
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setCompany(company);
        // Add reviews to JobWithCompanyDTO if available
        jobWithCompanyDTO.setReview(reviews);
        
        return jobWithCompanyDTO;
        
    }
    
}
