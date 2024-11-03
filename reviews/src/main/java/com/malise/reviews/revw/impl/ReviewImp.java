package com.malise.reviews.revw.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.malise.reviews.revw.Review;
import com.malise.reviews.revw.ReviewRepository;
import com.malise.reviews.revw.ReviewService;

@Service
public class ReviewImp implements ReviewService {

    private final ReviewRepository reviewRepository;

    // private final CompanyRepository companyRepository;

    public ReviewImp(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return  reviewRepository.findById(reviewId).orElse(null);

    }

    @Override
    public Boolean createReview(Long companyId, Review review) {

        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
            // company.get().getReviews().add(review);
            // companyRepository.save(company.get());
        }

        return false;
    }

    @Override
    public Boolean updateReview(Long reviewId , Review review) {
        Optional<Review> review1 = reviewRepository.findById(reviewId);
        if (review1.isPresent()) {
            Review existingReview = review1.get();
            existingReview.setTitle(review.getTitle());
            existingReview.setDescription(review.getDescription());
            existingReview.setRating(review.getRating());

            reviewRepository.save(existingReview);

            return true;
        }
        return false;

    }

    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {
        List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        return reviewList;

    }

    @Override
    public boolean deleteReview(Long reviewId) {

        Optional<Review> review = reviewRepository.findById(reviewId);

        if (review.isPresent()) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

}
