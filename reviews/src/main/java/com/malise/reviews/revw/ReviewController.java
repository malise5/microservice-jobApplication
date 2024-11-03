package com.malise.reviews.revw;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.malise.reviews.revw.impl.ReviewImp;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewImp reviewImp;
    // private final CompImp companyImpl;

    public ReviewController(ReviewImp reviewImp) {
        this.reviewImp = reviewImp;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getById(@RequestParam Long companyId) {
        List<Review> existingReviw = reviewImp.getAllReviewsByCompanyId(companyId);

        if (existingReviw != null) {
            return ResponseEntity.status(HttpStatus.OK).body(existingReviw);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review existingReviw = reviewImp.getReviewById(reviewId);

        return new ResponseEntity<>(existingReviw, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReviewSaved = reviewImp.createReview(companyId, review);

        if (isReviewSaved) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Review failed to be added", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        Boolean existingReview = reviewImp.updateReview(reviewId, review);
        if (existingReview) {
            return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewImp.deleteReview(reviewId);

        if (isReviewDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
    }

}
