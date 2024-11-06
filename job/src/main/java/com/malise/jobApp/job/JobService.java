package com.malise.jobApp.job;

import java.util.List;

import com.malise.jobApp.job.dto.JobWithCompanyDTO;

public interface JobService {

    List<JobWithCompanyDTO> findAll();

    void createJob(Job job);

    Job findJobById(Long id);

    Boolean deleteJobById(Long id);

    Boolean updateJob(Long id, Job item);

    
} 