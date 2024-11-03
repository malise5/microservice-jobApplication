package com.malise.jobApp.job;

import java.util.List;

public interface JobService {

    List<Job> findaAll();

    void createJob(Job job);

    Job findJobById(Long id);

    Boolean deleteJobById(Long id);

    Boolean updateJob(Long id, Job item);

    
} 