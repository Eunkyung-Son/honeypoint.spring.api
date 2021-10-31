package com.ek.honeypoint.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ek.honeypoint.models.InsertReviewImg;
import com.ek.honeypoint.models.Menu;
import com.ek.honeypoint.models.Photofile;
import com.ek.honeypoint.models.Reservation;
import com.ek.honeypoint.models.Restaurant;
import com.ek.honeypoint.models.Review;
import com.ek.honeypoint.models.ReviewCount;
import com.ek.honeypoint.models.ReviewImg;
import com.ek.honeypoint.models.RstrntMenu;
import com.ek.honeypoint.models.UpdateReviewImg;

import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {

	Restaurant selectRestaurant(int rNo);

	ArrayList<Restaurant> selectRestaurants();
	ArrayList<Restaurant> selectRestaurants(String restaurantType);
	ArrayList<Restaurant> selectRestaurants(ArrayList<Integer> restaurantIds);

	int insertRestaurantImg(ArrayList<Photofile> photofiles);

	int deleteRestaurantImg(ArrayList<Photofile> photofiles);

	int selectImgListCount(int rNo);

	ArrayList<Photofile> selectImgList(int rNo);

	ArrayList<Menu> selectMenuList(int rNo);

	ArrayList<Review> selectReviewList(HashMap<String, Integer> value);

	ReviewCount selectReviewCount(int rNo);

	ArrayList<ReviewImg> selectReviewImgList(HashMap<String, Integer> value);

	ArrayList<Review> selectReviewFilterList(HashMap<String, Integer> value);

	int insertReviewImg(InsertReviewImg value);

	String deleteImgFile(ReviewImg revImg);

	int deleteReviewImg(Review rev, String[] names);

	int updateReviewImg(UpdateReviewImg value);

	int deleteReviewImage(String string);

	ArrayList<String> getRevImgNames(int revNo);

	int insertResve(Reservation resve);

	int insertPoint(Reservation resve);

	ArrayList<Reservation> selectReservationList(int rNo);

  Restaurant selectRestaurantInfoByMember(int memberNo);

  ArrayList<Restaurant> searchRestaurants(String keyword);

  int updateRestaurant(Restaurant restaurant);

}