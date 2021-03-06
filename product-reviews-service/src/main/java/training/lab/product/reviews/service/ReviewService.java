package training.lab.product.reviews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.lab.product.reviews.exception.CrudException;
import training.lab.product.reviews.model.Review;
import training.lab.product.reviews.exception.ResourceNotFoundException;
import training.lab.product.reviews.repository.ReviewRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public Review getReview(Long reviewId) {
        if (null == reviewId || reviewId <= 0) {
            throw new CrudException("Review Id Is Invalid");
        }
        Review review = null;
        try {
            review = reviewRepository.findById(reviewId).get();
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Review Record Id: " + reviewId + " Not Found In The Database");
        }
        return review;
    }

    @Transactional
    public Review saveReview(Review review) {
        if (null == review) {
            throw new CrudException("Request Parameters Not Found In The Request Body");
        }
        if (!(null == review.getReviewId())) {
            throw new CrudException("Remove the Review Id From The Request Body. It Will Be Auto Generated By The System");
        }
        Review newReview = null;
        try {
            newReview = reviewRepository.save(review);
        } catch(Exception ex) {
            throw new CrudException("Error While Inserting The Record In To The Database");
        }
        return newReview;
    }

    @Transactional
    public Review updateReview(Review review) {
        if (null == review) {
            throw new CrudException("Request Parameters Not Found In The Request Body");
        }
        if (null == review.getReviewId()) {
            throw new CrudException("Review Id Is Missing In The Request Body");
        }
        Review updatedReview = null;
        try {
            updatedReview = reviewRepository.save(review);
        } catch(Exception ex) {
            throw new CrudException("Error While Updating The Record In To The Database");
        }
        return updatedReview;
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        if (null == reviewId || reviewId <= 0) {
            throw new CrudException("Review Id Is Invalid");
        }
        try {
            reviewRepository.deleteById(reviewId);
        } catch(Exception ex) {
            throw new ResourceNotFoundException("Review Record Id: " + reviewId + " Not Found In The Database");
        }
    }
}
