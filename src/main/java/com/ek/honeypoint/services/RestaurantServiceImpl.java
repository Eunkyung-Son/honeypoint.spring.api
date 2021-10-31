package com.ek.honeypoint.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ek.honeypoint.daos.RestaurantDao;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("rService")
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantDao rDao;
	
	@Override
	public Restaurant selectRestaurant(int rNo) {
		return rDao.selectRestaurant(rNo);
	}

	@Override
	public ArrayList<Restaurant> selectRestaurants() {
		return rDao.selectRestaurants();
	}

	@Override
	public ArrayList<Restaurant> selectRestaurants(String restaurantType) {
		return rDao.selectRestaurants(restaurantType);
	}
	
	@Override
	public ArrayList<Restaurant> selectRestaurants(ArrayList<Integer> restaurantIds) {
		return rDao.selectRestaurants(restaurantIds);
	}

	@Override
	public int insertRestaurantImg(ArrayList<Photofile> photofiles) {
		return rDao.insertRestaurantImg(photofiles);
	}

	@Override
	public int deleteRestaurantImg(ArrayList<Photofile> photofiles) {
		return rDao.deleteRestaurantImg(photofiles);
	}

	@Override
	public int selectImgListCount(int rNo) {
		
		return rDao.selectImgListCount(rNo);
	}

	@Override
	public ArrayList<Photofile> selectImgList(int rNo) {
		return rDao.selectImgList(rNo);
	}

	@Override
	public ArrayList<Menu> selectMenuList(int rNo) {
		return rDao.selectMenuList(rNo);
	}

	@Override
	public ArrayList<Review> selectReviewList(HashMap<String, Integer> value) {
		return rDao.selectReviewList(value);
	}

	@Override
	public ReviewCount selectReviewCount(int rNo) {
		return rDao.selectReviewCount(rNo);
	}

	@Override
	public ArrayList<ReviewImg> selectReviewImgList(HashMap<String, Integer> value) {
		return rDao.selectReviewImgList(value);
	}

	@Override
	public ArrayList<Review> selectReviewFilterList(HashMap<String, Integer> value) {
		return rDao.selectReviewFilterList(value);
	}

	@Override
	public int insertReviewImg(InsertReviewImg value) {
		return rDao.insertReviewImg(value);
	}

	@Override
	public String deleteImgFile(ReviewImg revImg) {
		return rDao.deleteImgFile(revImg);
	}

	@Override
	public int deleteReviewImg(Review rev, String[] names) {
		return rDao.deleteReviewImg(rev, names);
	}

	@Override
	public int updateReviewImg(UpdateReviewImg value) {
		return rDao.updateReviewImg(value);
	}

	@Override
	public int deleteReviewImage(String name) {
		return rDao.deleteReviewImage(name);
	}

	@Override
	public ArrayList<String> getRevImgNames(int revNo) {
		return rDao.getRevImgNames(revNo);
	}

	@Override
	public int insertResve(Reservation resve) {
		return rDao.insertResve(resve);
	}

	@Override
	public int insertPoint(Reservation resve) {
		return rDao.insertPoint(resve);
	}

	@Override
	public ArrayList<Reservation> selectReservationList(int rNo) {
		return rDao.selectResveList(rNo);
	}

	@Override
	public Restaurant selectRestaurantInfoByMember(int memberNo) {
		return rDao.selectRestaurantInfoByMember(memberNo);
	}

	@Override
	public ArrayList<Restaurant> searchRestaurants(String keyword) {
		return rDao.searchRestaurants(keyword);
	}

	@Override
	public int updateRestaurant(Restaurant restaurant) {
		return rDao.updateRestaurant(restaurant);
	}
}