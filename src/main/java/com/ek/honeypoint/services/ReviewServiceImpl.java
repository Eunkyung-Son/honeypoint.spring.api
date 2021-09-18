package com.ek.honeypoint.services;

import java.util.ArrayList;

import com.ek.honeypoint.daos.ReviewDao;
import com.ek.honeypoint.models.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewDao reviewDao;

  @Override
	public int insertReview(Review rev) {
		return reviewDao.insertReview(rev);
	}

  @Override
  public int updateReview(Review review) {
    return reviewDao.updateReview(review);
  }

  @Override
  public int deleteReview(int revNo) {
    return reviewDao.deleteReview(revNo);
  }

  @Override
  public Review selectReview(int revNo) {
    return reviewDao.selectReview(revNo);
  }

  @Override
  public ArrayList<Review> getReviewsByRestaurant(int restaurantId) {
    return reviewDao.getReviewsByRestaurant(restaurantId);
  }

  @Override
  public ArrayList<Review> getReviewsByMember(int memberId) {
    return reviewDao.getReviewsByMember(memberId);
  }

  @Override
  public int selectReviewCount(int restaurantId) {
    return reviewDao.selectReviewCount(restaurantId);
  }
  
}
