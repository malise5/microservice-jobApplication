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
import com.malise.jobApp.job.mapper.JobMapper;

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
      
            String companyApiUrl = "http://COMPANY:8081/api/companies/" + job.getCompanyId();
            Company company = restTemplate.getForObject(companyApiUrl, Company.class);

            JobWithCompanyDTO jobWithCompanyDTO = JobMapper.mapToJobWithCompanyDTO(job, company);

            jobWithCompanyDTO.setCompany(company);

            return jobWithCompanyDTO;

    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    // @Override
    // public Job findJobById(Long id) {
    //     return jobRepository.findById(id).orElse(null);
    // }

    @Override
    public JobWithCompanyDTO findJobById(Long id) {
        Job job= jobRepository.findById(id).orElse(null);
        return convertToDto(job);

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
