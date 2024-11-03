package com.malise.reviews.revw;

import java.util.List;


public interface ReviewService {

    List<Review> getAllReviews();

    List<Review> getAllReviewsByCompanyId(Long companyId);

    // Review getReviewByTitle(String title);

    // Review getReviewByRating(Double rating);

    Review getReviewById(Long reviewId);

    Boolean createReview(Long companyId, Review review);

    Boolean updateReview(Long reviewId, Review review);

    boolean deleteReview(Long reviewId);
    
}
