package com.ek.honeypoint.controllers;

import java.util.ArrayList;
import java.util.List;

import com.ek.honeypoint.exceptions.RestaurantException;
import com.ek.honeypoint.models.HPResponse;
import com.ek.honeypoint.models.Menu;
import com.ek.honeypoint.models.Photofile;
import com.ek.honeypoint.models.Reservation;
import com.ek.honeypoint.models.Restaurant;
import com.ek.honeypoint.models.Review;
import com.ek.honeypoint.services.FavorService;
import com.ek.honeypoint.services.FileService;
import com.ek.honeypoint.services.RestaurantService;
import com.ek.honeypoint.services.ReviewService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RestaurantController {
	@Autowired
	private RestaurantService rService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FavorService favorService;
	@Autowired
	private FileService fileService;
	@Value("${file.path}")
	private String filePath;

	private Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	@GetMapping(value = "/api/restaurant/{restaurantId}")
	@ResponseBody
	public HPResponse detail(@PathVariable(value = "restaurantId") int restaurantId,
			@RequestParam(value = "fetchMenuList", defaultValue = "false") Boolean fetchMenuList,
			@RequestParam(value = "fetchFavorCount", defaultValue = "false") Boolean fetchFavorCount,
			@RequestParam(value = "fetchReservation", defaultValue = "false") Boolean fetchReservation,
			@RequestParam(value = "fetchReviewList", defaultValue = "false") Boolean fetchReviewList,
			@RequestParam(value = "fetchReviewCount", defaultValue = "false") Boolean fetchReviewCount)
			throws RestaurantException {
		/**
		 * ????????????
		 */
		Restaurant restaurant = null;
		ArrayList<Photofile> imgList = null;
		ArrayList<Menu> menuList = null;
		ArrayList<Review> reviewList = null;
		ArrayList<Reservation> reservationList = null;
		int favorCount = 0;
		int reviewCount = 0;
		HPResponse response = new HPResponse();

		restaurant = rService.selectRestaurant(restaurantId);
		if (restaurant != null) {
			imgList = rService.selectImgList(restaurantId);
			ArrayList<String> fileIds = new ArrayList<String>();
			System.out.println(imgList.size());
			if (imgList.size() > 0) {
				for (Photofile image : imgList) {
					fileIds.add(image.getStreFileName());
				}
				restaurant.setFileIds(fileIds);
			}
			response.put("restaurant", restaurant);
			response.put("total", 1);
		} else {
			throw new RestaurantException("?????? ??????????????? ?????????????????????.");
		}

		// ????????????
		if (fetchFavorCount == true) {
			favorCount = favorService.restaurantFavorCount(restaurantId);
			response.put("favorCount", favorCount);
		}
		if (fetchMenuList == true) {
			menuList = rService.selectMenuList(restaurantId);
			response.put("menus", menuList);
		}
		if (fetchReservation == true) {
			reservationList = rService.selectReservationList(restaurantId);
			response.put("reservations", reservationList);
		}
		if (fetchReviewList == true) {
			reviewList = reviewService.getReviewsByRestaurant(restaurantId);
			response.put("reviews", reviewList);
		}
		if (fetchReviewCount == true) {
			reviewCount = reviewService.selectReviewCount(restaurantId);
			response.put("reviewCount", reviewCount);
		}
		System.out.print(response);
		return response;
	}

	@GetMapping(value = "/api/restaurantByMember/{memberNo}")
	@ResponseBody
	public HPResponse selectRestaurantInfoByMember(@PathVariable(value = "memberNo") int memberNo)
			throws RestaurantException {
		HPResponse response = new HPResponse();
		Restaurant restaurant = null;
		ArrayList<Photofile> imgList = null;

		restaurant = rService.selectRestaurantInfoByMember(memberNo);
		if (restaurant != null) {
			imgList = rService.selectImgList(restaurant.getRNo());
			ArrayList<String> fileIds = new ArrayList<String>();
			System.out.println(imgList.size());
			if (imgList.size() > 0) {
				for (Photofile image : imgList) {
					fileIds.add(image.getStreFileName());
				}
				restaurant.setFileIds(fileIds);
			}
			response.put("restaurant", restaurant);
			response.put("total", 1);
		} else {
			throw new RestaurantException("?????? ??????????????? ?????????????????????.");
		}
		if (restaurant != null) {
			response.put("restaurant", restaurant);
		} else {
			throw new RestaurantException("???????????? ?????? ??????????????? ?????????????????????.");
		}
		return response;
	}

	@PostMapping(value = "/api/restaurant/update")
	@ResponseBody
	public HPResponse updateRestaurantInfo (
		@RequestBody Restaurant restaurant
		) {
		HPResponse response = new HPResponse();
		int updateResult = rService.updateRestaurant(restaurant);
		System.out.println(restaurant + "???????????? ????????????");
		if (updateResult > 0) {
			response.put("msg", "???????????? ?????? ????????? ?????????????????????.");
			Restaurant selectRestaurant = rService.selectRestaurant(restaurant.getRNo());
			response.put("restaurant", selectRestaurant);
		} else {
			response.put("error", true);
			response.put("msg", "???????????? ?????? ????????? ?????????????????????.");
		}
		return response;
	}

	@GetMapping(value= "/api/restaurant/all")
	@ResponseBody
	public HPResponse selectRestaurants() {
		HPResponse response = new HPResponse();
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		restaurants = rService.selectRestaurants();
		if (restaurants != null) {
			int total = restaurants.size();
			response.put("total", total);

			ArrayList<Photofile> imgList = null;
			for (Restaurant restaurant : restaurants) {
				imgList = rService.selectImgList(restaurant.getRNo());
				ArrayList<String> fileIds = new ArrayList<String>();
				for (Photofile image : imgList) {
					fileIds.add(image.getStreFileName());
				}
				restaurant.setFileIds(fileIds);
			}
			response.put("restaurants", restaurants);
		}
		return response;
	}

	@GetMapping(value = "/api/restaurants")
	@ResponseBody
	public HPResponse list(@RequestParam(value = "restaurantType", defaultValue = "") String restaurantType) {
		// FIXME: ????????? ????????? ?????? ??????????????? JOIN ????????? ???????????? ?????? ???????????? ?????? ???????????? ????????????
		HPResponse response = new HPResponse();
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

		if (restaurantType != "") {
			restaurants = rService.selectRestaurants(restaurantType);
		}
		if (restaurants != null) {
			int total = restaurants.size();
			response.put("total", total);

			ArrayList<Photofile> imgList = null;
			for (Restaurant restaurant : restaurants) {
				imgList = rService.selectImgList(restaurant.getRNo());
				ArrayList<String> fileIds = new ArrayList<String>();
				for (Photofile image : imgList) {
					fileIds.add(image.getStreFileName());
				}
				restaurant.setFileIds(fileIds);
			}
			response.put("restaurants", restaurants);
		}
		return response;
	}

	@GetMapping(value = "/api/searchRestaurants")
	@ResponseBody
	public HPResponse searchRestaurants(@RequestParam(value = "keyword", defaultValue = "") String keyword) {
		// FIXME: ????????? ????????? ?????? ??????????????? JOIN ????????? ???????????? ?????? ???????????? ?????? ???????????? ????????????
		HPResponse response = new HPResponse();
		ArrayList<Restaurant> restaurants = null;
		if (keyword != "") {
			restaurants = rService.searchRestaurants(keyword);
		} else {

		}
		if (restaurants != null) {
			response.put("restaurants", restaurants);

			int total = restaurants.size();
			response.put("total", total);

			ArrayList<Photofile> imgList = null;
			for (Restaurant restaurant : restaurants) {
				imgList = rService.selectImgList(restaurant.getRNo());
				ArrayList<String> fileIds = new ArrayList<String>();
				for (Photofile image : imgList) {
					fileIds.add(image.getStreFileName());
				}
				restaurant.setFileIds(fileIds);
			}
		} else {
			throw new RestaurantException("?????? ????????? ?????????????????????.");
		}
		return response;
	}

	// ???????????? ????????? ?????? ??????????????? ?????? ???????????? ?????? ???????????? ?????? ??? ???????????? ?????? ????????? api??? ?????????
	@PostMapping(value = "/api/file/restaurant/{restaurantId}")
	@ResponseBody
	public HPResponse addFileOnRestaurant(@PathVariable(value = "restaurantId") int restaurantId,
			@RequestPart("file") List<MultipartFile> insertFileList) {
		HPResponse response = new HPResponse();
		System.out.println(insertFileList.toString());
		String path = filePath + "/restaurants" + "/" + restaurantId;

		ArrayList<Photofile> deleteList = new ArrayList<Photofile>();
		ArrayList<MultipartFile> addList = new ArrayList<MultipartFile>();
		ArrayList<Photofile> prevImages = rService.selectImgList(restaurantId);
		if (prevImages.size() > 0) {
			for (Photofile prevImage : prevImages) {
				Boolean isExist = false;
				String prevFileName = prevImage.getStreFileName();
				for (MultipartFile insertFile : insertFileList) {
					if (insertFile.getOriginalFilename().equals(prevFileName))
						isExist = true;
				}
				if (isExist == false) {
					deleteList.add(prevImage);
				}
			}
		}
		for (MultipartFile insertFile : insertFileList) {
			Boolean isExist = false;
			String insertFileName = insertFile.getOriginalFilename();
			for (Photofile prevImage : prevImages) {
				if (prevImage.getStreFileName().equals(insertFileName))
					isExist = true;
			}
			if (isExist == false) {
				addList.add(insertFile);
			}
		}
		if (deleteList.size() > 0) {
			Boolean isDeleted = fileService.deleteFilesOnRestaurant(deleteList, path);
			if (isDeleted) {
				int deleteResult = rService.deleteRestaurantImg(deleteList);
				System.out.print(deleteResult);
			}
		}

		ArrayList<Photofile> photofiles = fileService.saveFileOnRestaurant(insertFileList, path, restaurantId);
		int insertResult = rService.insertRestaurantImg(photofiles);
		System.out.println(insertResult);
		return response;
	}

}