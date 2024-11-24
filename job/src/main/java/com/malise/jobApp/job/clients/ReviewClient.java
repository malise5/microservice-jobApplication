package com.malise.jobApp.job.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.malise.jobApp.job.external.Review;

@FeignClient(name="REVIEWS")
public interface ReviewClient {
    // Add methods to call Review API
    @GetMapping("/reviews")
    List<Review> getReviewsByCompanyId(@RequestParam("companyId") Long companyId);

}
