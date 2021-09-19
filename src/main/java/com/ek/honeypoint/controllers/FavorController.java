package com.ek.honeypoint.controllers;

import java.util.ArrayList;

import com.ek.honeypoint.exceptions.FavorException;
import com.ek.honeypoint.models.Favor;
import com.ek.honeypoint.models.HPResponse;
import com.ek.honeypoint.models.Restaurant;
import com.ek.honeypoint.services.FavorService;
import com.ek.honeypoint.services.RestaurantService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FavorController {
  @Autowired
  private FavorService favorService;
  @Autowired
  private RestaurantService restaurantService;

  private Logger logger = LoggerFactory.getLogger(ReviewController.class);

  /**
   * 찜하기 favor / 찜하기 unFavor
   * @return
   */
  @PostMapping(value="/api/favor")
  @ResponseBody
  public HPResponse createFavor (
    @Autowired
    @RequestParam(value = "restaurantId") int restaurantNo,
    @RequestParam(value = "memberId") int memberNo
  ) throws FavorException {
    HPResponse response = new HPResponse();
    Favor favor = new Favor(restaurantNo, memberNo);
    int insertResult = favorService.favor(favor);
    if (insertResult > 0) {
      response.put("favor", favor);
    } else {
      throw new FavorException("찜에 실패했습니다.");
    }
    return response;
  }

  @PostMapping(value="/api/unFavor")
  @ResponseBody
  public HPResponse deleteFavor (
    @RequestParam(value = "restaurantId") int restaurantNo,
    @RequestParam(value = "memberId") int memberNo
  ) throws FavorException {
    HPResponse response = new HPResponse();
    Favor favor = new Favor(restaurantNo, memberNo);
    int deleteResult = favorService.unFavor(favor);
    if (deleteResult > 0) {
      response.put("favor", favor);
    } else {
      throw new FavorException("찜 삭제에 실패했습니다.");
    }
    return response;
  }

  // member의 찜 리스트 조회
  @GetMapping(value="/api/favors/{memberId}")
  @ResponseBody
  public HPResponse getMemberFavorList (
    @PathVariable(value = "memberId") int memberNo,
    @RequestParam(value = "fetchRestaurants", defaultValue = "false") Boolean fetchRestaurants
  ) throws FavorException {
    HPResponse response = new HPResponse();
    ArrayList<Favor> favors = favorService.getMemberFavorList(memberNo);
    response.put("favors", favors);
    if (favors.size() > 0) {
      ArrayList<Integer> restaurantIds = new ArrayList<Integer>();
      favors.forEach((favor) -> {
        restaurantIds.add(favor.getRNo());
      });
      if (fetchRestaurants) {
        ArrayList<Restaurant> restaurants = restaurantService.selectRestaurants(restaurantIds);
        response.put("restaurants", restaurants);
        if (restaurants.size() > 0) {
          response.put("total", restaurants.size());
        } else {
          response.put("total", 0);
        }
      }
    }
    return response;
  }

  @GetMapping(value="/api/favor/check")
  @ResponseBody
  public HPResponse checkFavor(
    @RequestParam(value = "memberId") int memberNo,
    @RequestParam(value = "restaurantId") int restaurantNo
  ) {
    HPResponse response = new HPResponse();
    Favor inputFavor = new Favor(restaurantNo, memberNo);
    Boolean isFavor = favorService.checkFavor(inputFavor);
    response.put("isFavor", isFavor);
    return response;
  }
  
}
