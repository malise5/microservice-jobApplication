package com.malise.jobApp.job;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.malise.jobApp.job.impl.JobImp;


@RestController
@RequestMapping("/api")
public class JobController {

    private final JobImp jObServiceImp;

    public JobController(JobImp jObServiceImp) {
        this.jObServiceImp = jObServiceImp;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAllJobs() {

        List<Job> jobs = jObServiceImp.findaAll();
 
        return ResponseEntity.status(HttpStatus.OK).body(jobs);

    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jObServiceImp.findJobById(id);

        if (job != null) {
            // return ResponseEntity.status(HttpStatus.OK).body(job);
            return new ResponseEntity<Job>(job, HttpStatus.OK);
        } else {
            return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/job")
    public ResponseEntity<String> postJob(@RequestBody Job job) {
        jObServiceImp.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job successfully created");
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Boolean deleted = jObServiceImp.deleteJobById(id);

        if (deleted) {

        return ResponseEntity.status(HttpStatus.OK).body("Job Delete Successfully");
            
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job Not Deleted Successfully");

   
    }

        @PutMapping("/job/{id}")
        public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Job item) {

            Boolean updated = jObServiceImp.updateJob(id, item);

            if (updated) {
                return ResponseEntity.status(HttpStatus.OK).body("Job updated Successfully");
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            
        }
    

    
    


}
