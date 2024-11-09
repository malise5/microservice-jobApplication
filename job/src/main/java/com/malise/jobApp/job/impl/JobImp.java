package com.malise.jobApp.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.malise.jobApp.job.Job;
import com.malise.jobApp.job.JobRepository;
import com.malise.jobApp.job.JobService;
import com.malise.jobApp.job.dto.JobWithCompanyDTO;
import com.malise.jobApp.job.external.Company;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobImp implements JobService{

    @Autowired
    RestTemplate restTemplate;

    private final JobRepository jobRepository;


    public JobImp(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobsWithCompanyDTO = new ArrayList<>();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());

    }

    private JobWithCompanyDTO convertToDto(Job job){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);

        // RestTemplate restTemplate = new RestTemplate();

        // Fetch company details
        try {
            String companyApiUrl = "http://COMPANY:8081/api/companies/" + job.getCompanyId();
            Company company = restTemplate.getForObject(companyApiUrl, Company.class);
            jobWithCompanyDTO.setCompany(company);
        } catch (RestClientException e) {
            log.error("Failed to fetch company details for Job ID {}: {}", job.getId(), e.getMessage());
            jobWithCompanyDTO.setCompany(null); // Set to null or handle as needed
        }

        return jobWithCompanyDTO;
    }




    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }

        
    }

    @Override
    public Boolean updateJob(Long id, Job item) {
        
        Optional<Job> job = jobRepository.findById(id);

        if (job.isPresent()) {
            Job updatedJob = job.get();
            updatedJob.setDescription(item.getDescription());
            updatedJob.setTitle(item.getTitle());
            updatedJob.setMaxSalary(item.getMaxSalary());
            updatedJob.setMinSalary(item.getMinSalary());
            updatedJob.setLocation(item.getLocation());
            jobRepository.save(updatedJob);
            return true;
        }

        return false;
    }

}
