package com.malise.jobApp.job;

import java.util.List;

import com.malise.jobApp.job.dto.JobDTO;

public interface JobService {

    List<JobDTO> findAll();

    void createJob(Job job);

    JobDTO findJobById(Long id);

    Boolean deleteJobById(Long id);

    Boolean updateJob(Long id, Job item);

    
} 