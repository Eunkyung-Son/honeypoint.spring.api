package com.ek.honeypoint.daos;

import java.util.ArrayList;

import com.ek.honeypoint.models.Review;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reviewDao")
public class ReviewDao {

  @Autowired
  private SqlSessionTemplate sqlSession;

  public int insertReview(Review review) {
		return sqlSession.insert("reviewMapper.insertReview", review);
	}

  public int updateReview(Review review) {
		return sqlSession.update("reviewMapper.updateReview", review);
	}

  public int deleteReview(int revNo) {
		return sqlSession.delete("reviewMapper.deleteReview", revNo);
	}

  public Review selectReview(int revNo) {
    return sqlSession.selectOne("reviewMapper.selectReview", revNo);
  }

  public ArrayList<Review> getReviewsByRestaurant(int restaurantId) {
    return (ArrayList)sqlSession.selectList("reviewMapper.getReviewsByRestaurant", restaurantId);
  }

  public ArrayList<Review> getReviewsByMember(int memberId) {
    return (ArrayList)sqlSession.selectList("reviewMapper.getReviewsByMember", memberId);
  }

  public int selectReviewCount(int restaurantId) {
    return sqlSession.selectOne("reviewMapper.selectReviewCount", restaurantId);
  }
}
