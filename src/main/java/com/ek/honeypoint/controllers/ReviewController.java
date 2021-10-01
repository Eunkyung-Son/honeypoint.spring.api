package com.ek.honeypoint.controllers;

import java.util.ArrayList;

import com.ek.honeypoint.exceptions.ReviewException;
import com.ek.honeypoint.models.HPResponse;
import com.ek.honeypoint.models.Review;
import com.ek.honeypoint.services.ReviewService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  private Logger logger = LoggerFactory.getLogger(ReviewController.class);

  // create review on Restaurant
  // FIXME: 이미지파일은 파일컨트롤러 만들어지면 다시
  @PostMapping(value="/api/review/{restaurantId}")
  @ResponseBody
  public HPResponse createReview (
    @RequestBody Review review,
    @PathVariable(value = "restaurantId") int restaurantNo

    // @RequestParam(value = "review", required = true) Review review
    // TODO: Multipart로 받아올지 아니면 다른방식으로 받아올지 정하기
  ) throws ReviewException {
    HPResponse response = new HPResponse();
    int insertResult = reviewService.insertReview(review);
    if (insertResult > 0) {
      response.put("review", review);
    } else {
      throw new ReviewException("리뷰 생성에 실패했습니다");
    }
    return response;
  }

  // 하나의 리뷰 상세
  @GetMapping(value="api/review/{reviewId}")
  @ResponseBody
  public HPResponse detailReview(
    @PathVariable(value = "reviewId") int reviewNo
  ) {
    HPResponse response = new HPResponse();
    Review review = reviewService.selectReview(reviewNo);
    if(review == null) {
      throw new ReviewException("리뷰를 불러오는데 실패 했습니다");
    }
    response.put("review", review);
    return response;
  }

  // TODO: 파일 수정에 대해선 어떻게 할지 고민해볼것 패치 받은 리뷰 이미지 리스트에서 건네준 아이디 배열에서 수정만 할지, 추가할거 있으면 할지 
  // PUT메서드는 tomcat에서 body를 파싱하지 않는다 ..
  @PutMapping(value="api/review/{reviewId}")
  public HPResponse updateReview(
    @PathVariable(value = "reviewId") int reviewNo,
    @RequestBody Review review
  ) {
      HPResponse response = new HPResponse();
      int updateResult = reviewService.updateReview(review);
      if (updateResult > 0) {
        response.put("review", review);
      } else {
        throw new ReviewException("리뷰 업데이트 실패했습니다");
      }
      return response;
  }

  @DeleteMapping(value = "api/review/{reviewId}")
  @ResponseBody
  public HPResponse deleteReview(
    @PathVariable(value = "reviewId") int reviewNo
  ) {
    HPResponse response = new HPResponse();
    int deleteResult = reviewService.deleteReview(reviewNo);
    if (deleteResult > 0) {
      response.put("msg", "정상적으로 삭제되었습니다");
    } else {
      response.put("msg", "문제가 발생했습니다");
    }
    return response;
  }

  @ResponseBody
  @GetMapping(value="api/reviews/{restaurantId}")
  public HPResponse selectReviewFilter(
    @PathVariable(value = "restaurantId") int restaurantId,
    @RequestParam(value = "filterType", defaultValue = "0") int filterType
  ) {
    ArrayList<Review> reviewList = null;
    HPResponse response = new HPResponse();
    if (filterType == 0) {
      reviewList = reviewService.getReviewsByRestaurant(restaurantId);
      response.put("reviews", reviewList);
    } else {
      reviewList = reviewService.selectReviewFilter(restaurantId, filterType);
      response.put("reviews", reviewList);
    }
    if (reviewList.size() > 0) {
      response.put("total", reviewList.size());
    } else {
      response.put("total", 0);
    }
    return response;
  }
}
