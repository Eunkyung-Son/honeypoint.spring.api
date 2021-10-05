package com.ek.honeypoint.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ek.honeypoint.exceptions.RestaurantException;
import com.ek.honeypoint.models.Favor;
import com.ek.honeypoint.models.HPResponse;
import com.ek.honeypoint.models.InsertReviewImg;
import com.ek.honeypoint.models.Photofile;
import com.ek.honeypoint.models.Reservation;
import com.ek.honeypoint.models.Restaurant;
import com.ek.honeypoint.models.Review;
// import com.ek.honeypoint.models.ReviewCount;
import com.ek.honeypoint.models.ReviewImg;
import com.ek.honeypoint.models.RstrntMenu;
import com.ek.honeypoint.models.UpdateReviewImg;
import com.ek.honeypoint.services.FavorService;
import com.ek.honeypoint.services.RestaurantService;
import com.ek.honeypoint.services.ReviewService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantController {
	@Autowired
	private RestaurantService rService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FavorService favorService;
	
	private Logger logger = LoggerFactory.getLogger(RestaurantController.class);
	
	@GetMapping(value="/api/restaurant/{restaurantId}")
	@ResponseBody
	public HPResponse detail (
		@PathVariable(value = "restaurantId") int restaurantId,
		@RequestParam(value = "fetchMenuList", defaultValue = "false") Boolean fetchMenuList,
		@RequestParam(value = "fetchFavorCount", defaultValue = "false") Boolean fetchFavorCount,
		@RequestParam(value = "fetchReservation", defaultValue = "false") Boolean fetchReservation,
		@RequestParam(value = "fetchReviewList", defaultValue = "false") Boolean fetchReviewList,
		@RequestParam(value = "fetchReviewCount", defaultValue = "false") Boolean fetchReviewCount
	) throws RestaurantException {
		/**
		 * 필수항목
		 */
		Restaurant restaurant = null;
		ArrayList<Photofile> imgList = null;
		ArrayList<RstrntMenu> menuList = null;
		ArrayList<Review> reviewList = null;
		ArrayList<Reservation> reservationList = null;
		int favorCount = 0;
		int reviewCount = 0;
		HPResponse response = new HPResponse();
		
		restaurant = rService.selectRestaurant(restaurantId);
		if(restaurant != null) {
			response.put("restaurant", restaurant);
			// response.setImages(imgList);
			response.put("total", 1);
		} else {
			throw new RestaurantException("맛집 상세조회에 실패하였습니다.");
		}
		// TODO: 이미지는 다시해야함
		// imgList =  rService.selectImgList(rNo);
		// 선택항목
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
	public HPResponse selectRestaurantInfoByMember (
		@PathVariable(value = "memberNo") int memberNo
	) throws RestaurantException {
		HPResponse response = new HPResponse();
		Restaurant restaurant = null;

		restaurant = rService.selectRestaurantInfoByMember(memberNo);

		if (restaurant != null) {
			response.put("restaurant", restaurant);
		} else {
			throw new RestaurantException("레스토랑 정보 불러오기에 실패하였습니다.");
		}

		return response;
	}
	
	@GetMapping(value = "/api/restaurants")
	@ResponseBody
	public HPResponse list(
		@RequestParam(value = "restaurantType", defaultValue = "") String restaurantType
	) {
		//FIXME: 추후에 이미지 파일 필요하다면 JOIN 형태로 들어갈지 파일 컨트롤러 따로 가져올지 정의하기
		HPResponse response = new HPResponse();
		ArrayList<Restaurant> restaurants = null;
		if (restaurantType != "") {
			restaurants = rService.selectRestaurants(restaurantType);
		} else {
			// restaurants = rService.selectRestaurants();
		}
		if (restaurants != null) {
			response.put("restaurants", restaurants);
			
			int total = restaurants.size();
			response.put("total", total);
		} else {
			throw new RestaurantException("맛집 리스트 조회에 실패하였습니다.");
		}
		return response;
	}


	// TODO: 아래는 reviewController 등 다른 컨트롤러로 관리해야함. 코드 이관 전 상태


	//FIXME: 리턴타입, url주소 & 이미지랑 함께 리턴하는 방법 생각해보기 ?
	@RequestMapping("moreReview.do")
	public ModelAndView selectReviewList(ModelAndView mv, int rNo, int startNum, int filterCheck, HttpServletResponse response) {
		
		HashMap<String, Integer> value = new HashMap<String, Integer>();
		value.put("rNo", rNo);
		value.put("startNum", startNum);
		//FIXME: 필터 체크의 의미?
		value.put("filterCheck", filterCheck);
		
		ArrayList<Review> reviewList = null;
		
		if(filterCheck == 0) {
			reviewList = rService.selectReviewList(value);
		}else if(filterCheck == 1) {
			reviewList = rService.selectReviewFilterList(value);
		}else if(filterCheck == 2) {
			// FIXME: 필터체크 1,2 그리고 else 차이?
			reviewList = rService.selectReviewFilterList(value);
		}else {
			reviewList = rService.selectReviewFilterList(value);
		}
		

		
		Map<String, ArrayList<Review>> map = new HashMap<String, ArrayList<Review>>();
		map.put("reviewList", reviewList);
		
		
		mv.addAllObjects(map);
		mv.setViewName("jsonView");
		
		response.setContentType("application/json; charset=utf-8");
		
		return mv;
		
	}
	

	// FIXME: 리턴타입, url주소 + 리뷰랑 함께 리턴하는 방법 생각해보기
	@RequestMapping("moreReviewImg.do")
	public ModelAndView selectReviewImgList(ModelAndView mv, int rNo, int revNo, HttpServletResponse response) {
		
		HashMap<String, Integer> value = new HashMap<String, Integer>();
		value.put("rNo", rNo);
		value.put("revNo", revNo);
		
		ArrayList<ReviewImg> reviewImgList = rService.selectReviewImgList(value);
		
		Map<String, ArrayList<ReviewImg>> map = new HashMap<String, ArrayList<ReviewImg>>();
		map.put("reviewImgList", reviewImgList);
		
		
		mv.addAllObjects(map);
		mv.setViewName("jsonView");
		
		response.setContentType("application/json; charset=utf-8");
		
		return mv;
		
	}
	

	// // FIXME: 리턴타입, url주소
	// @RequestMapping(value="insertReview.do", method = RequestMethod.POST)
	// public String boardInsert(Review rev, HttpServletRequest request, HttpServletResponse response,
	// 		MultipartHttpServletRequest multi) {
		
	// 	int result1 = rService.insertReview(rev);

	// 	//FIXME: 파일 로컬에 저장하도록 수정
	// 	if(result1 > 0) {
	// 		if(multi.getFileNames().hasNext()) {
				
	// 			String root = request.getSession().getServletContext().getRealPath("resources");
	// 			String savePath = root + "\\img\\review";
	// 			String fileName = "";
	// 			ArrayList<String> originFileList = new ArrayList<String>();
	// 			ArrayList<String> renameFileList = new ArrayList<String>();
				
	// 			File folder = new File(savePath);

	// 			if(!folder.exists()) {
	// 				folder.mkdirs();
	// 			}
				
	// 			Iterator<String> files = multi.getFileNames();
				
	// 			while(files.hasNext()) {
	// 				String uploadFile = files.next();
					
	// 				MultipartFile mFile = multi.getFile(uploadFile);
	// 				System.out.println("원본 파일 이름 : " +  mFile.getOriginalFilename());
	// 				if(mFile.getOriginalFilename().equals("")) {
	// 					continue;
	// 				}
				
	// 				// FIXME: 파일 명명규칙 수정
	// 				// 파일 이름짓기
	// 				int ranNum = (int)(Math.random() * 100000);
	// 				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	// 				String originFileName = mFile.getOriginalFilename();
	// 				fileName = sdf.format(new Date()) + "_" + ranNum 
	// 						+ originFileName.substring(originFileName.lastIndexOf("."));
					
	// 				try {
	// 					System.out.println(folder + "\\"  + fileName);
	// 					mFile.transferTo(new File(folder + "\\"  + fileName));
						
	// 					originFileList.add(originFileName);
	// 					renameFileList.add(fileName);
	// 				} catch (Exception e) {
	// 					e.printStackTrace();
	// 				}
	// 			}
				
	// 			if(originFileList.isEmpty()) {
	// 				return "redirect:detail.do?rNo=" + rev.getRNo();
	// 			}
				
	// 			InsertReviewImg value = new InsertReviewImg();
				
	// 			value.setRNo(rev.getRNo());
	// 			value.setOriginFileList(originFileList);
	// 			value.setRenameFileList(renameFileList);

	// 			int result2 = rService.insertReviewImg(value);
				
	// 			if(result2 > 0) {
	// 				if(logger.isDebugEnabled()) {
	// 					logger.debug(rev.getRevNo() + "번째 리뷰가 생성되었습니다.");
	// 				}
					
	// 				return "redirect:detail.do?rNo=" + rev.getRNo();
					
	// 			}else {
					
	// 				throw new RestaurantException("리뷰 이미지 등록에 실패하였습니다.");
					
	// 			}
				
	// 		}else {
				
	// 			return "redirect:detail.do?rNo=" + rev.getRNo();
				
	// 		}
	// 	}else {
			
	// 		throw new RestaurantException("리뷰 등록에 실패하였습니다.");
			
	// 	}
		
		
	// }
	

	// // FIXME: 업데이트 하고 나서 완료되면 해당 업데이트 된 페이지 내용을 다시 업데이트한다 
	// @RequestMapping("updateReviewView.do")
	// public ModelAndView updateReviewView(ModelAndView mv, 
	// 		@RequestParam("rNo") int rNo, @RequestParam("revNo") int revNo) {
		
	// 	Review rev = rService.selectReview(rNo, revNo);
		
	// 	HashMap<String, Integer> value = new HashMap<String, Integer>();
	// 	value.put("rNo", rNo);
	// 	value.put("revNo", revNo);
		
	// 	ArrayList<ReviewImg> reviewImgList = rService.selectReviewImgList(value);
		
		
	// 	if(rev != null) {
	// 		mv.addObject("img", reviewImgList);
	// 		mv.addObject("review", rev);
	// 		mv.setViewName("restaurant/updateReviewPage");
	// 	}else {
	// 		throw new RestaurantException("리뷰 수정 페이지를 불러오는데 실패하였습니다.");
	// 	}

	// 	return mv;
	// }
	
	// @RequestMapping(value="updateReview.do", method = RequestMethod.POST)
	// public String updateReview(Review rev, MultipartHttpServletRequest multi, HttpServletRequest request, @RequestParam("lastNumber") int lastNumber) {
	// 	int result = 0;
	// 	System.out.println(rev);
		
	// 	String[] names = request.getParameterValues("deleteNames");
		
	// 	//System.out.println(names.length);
	// 	//System.out.println(names[0]);
	// 	if(names != null) {
	// 		// int result = rService.deleteReviewImg(rev, names);
	// 		for(int i = 0; i < names.length; i++) {
	// 			result = rService.deleteReviewImage(names[i]);
	// 		}
			
			
	// 		if(result == 0) {
	// 			throw new RestaurantException("기존 리뷰 이미지 삭제 실패.");
	// 		}else {
	// 			int result1 = rService.updateReview(rev);
				
	// 			if(result1 == 0) {
	// 				throw new RestaurantException("리뷰 내용 수정 실패.");
	// 			}
	// 		}
	// 	}else {
	// 		int result1 = rService.updateReview(rev);
	// 	}
		
	// 	// 새로운 리뷰 이미지 파일 첨부
		
	// 	if(multi.getFileNames().hasNext()) {
			
	// 		String root = request.getSession().getServletContext().getRealPath("resources");
	// 		String savePath = root + "\\img\\review";
	// 		String fileName = "";
	// 		ArrayList<String> originFileList = new ArrayList<String>();
	// 		ArrayList<String> renameFileList = new ArrayList<String>();
			
	// 		File folder = new File(savePath);

	// 		if(folder.exists()) {
	// 			folder.mkdirs();
	// 		}
			
	// 		Iterator<String> files = multi.getFileNames();
			
	// 		while(files.hasNext()) {
	// 			String uploadFile = files.next();
				
	// 			MultipartFile mFile = multi.getFile(uploadFile);
	// 			System.out.println("원본 파일 이름 : " +  mFile.getOriginalFilename());
	// 			if(mFile.getOriginalFilename().equals("")) {
	// 				continue;
	// 			}
				
	// 			// 파일 이름짓기
	// 			int ranNum = (int)(Math.random() * 100000);
	// 			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	// 			String originFileName = mFile.getOriginalFilename();
	// 			fileName = sdf.format(new Date()) + "_" + ranNum 
	// 					+ originFileName.substring(originFileName.lastIndexOf("."));
				
	// 			try {
	// 				System.out.println(folder + "\\"  + fileName);
	// 				mFile.transferTo(new File(folder + "\\"  + fileName));
	// 				originFileList.add(originFileName);
	// 				renameFileList.add(fileName);
	// 			} catch (Exception e) {
	// 				e.printStackTrace();
	// 			}
	// 		}
			
	// 		if(originFileList.isEmpty()) {
	// 			return "redirect:detail.do?rNo=" + rev.getRNo();
	// 		}
			
	// 		UpdateReviewImg value = new UpdateReviewImg();
			
	// 		value.setRNo(rev.getRNo());
	// 		value.setRevNo(rev.getRevNo());
	// 		value.setOriginFileList(originFileList);
	// 		value.setRenameFileList(renameFileList);
			
	// 		ArrayList<Integer> lastNumberCount = new ArrayList<Integer>();
	// 		System.out.println("리네임파일크기 : " + renameFileList.size());
	// 		if(lastNumber != 0) {
	// 			for(int i = lastNumber + 1; i < lastNumber + renameFileList.size() + 1; i++) {
	// 				lastNumberCount.add(i);
	// 			}
	// 		}else {
	// 			lastNumberCount.add(1);
	// 		}
			
			
	// 		value.setLastNumberCount(lastNumberCount);
			
	// 		int result2 = rService.updateReviewImg(value);
			
	// 		if(result2 > 0) {
	// 			if(logger.isDebugEnabled()) {
	// 				logger.debug(rev.getRevNo() + "번째 리뷰가 생성되었습니다.");
	// 			}
				
	// 			return "redirect:detail.do?rNo=" + rev.getRNo();
				
	// 		}else {
				
	// 			throw new RestaurantException("리뷰 이미지 등록에 실패하였습니다.");
				
	// 		}
			
	// 	}else {
			
	// 		return "redirect:detail.do?rNo=" + rev.getRNo();
			
	// 	}
	
	// }
	
	// @RequestMapping("deleteReview.do")
	// public ModelAndView deleteReview(ModelAndView mv, int rNo, int revNo, HttpServletResponse response, HttpServletRequest request) {
	// 	System.out.println(revNo);
		
	// 	int result = rService.deleteReview(revNo);
		
	// 	if(result != 0) {
			
	// 		ArrayList<String> names = new ArrayList<String>();
			
	// 		names = rService.getRevImgNames(revNo);
	// 		System.out.println("names : " + names);
	// 		if(names != null) {
	// 			// int result = rService.deleteReviewImg(rev, names);
	// 			for(int i = 0; i < names.size(); i++) {
	// 				result = rService.deleteReviewImage(names.get(i));
					

	// 				//FIXME: 서버 열린 로컬에 저장하는 쪽으로 수정
	// 				if(result != 0) {
	// 					String root = request.getSession().getServletContext().getRealPath("resources");
	// 					String savePath = root + "\\img\\review";
						
	// 					File deleteFile = new File(savePath + "\\" + names.get(i));
						
	// 					if(deleteFile.exists()) {
	// 						deleteFile.delete();
	// 						System.out.println( i + "번째 파일 삭제 성공");
	// 					}
	// 				}
					
	// 			}
				
				
	// 			if(result != 0) {
	// 				// 여기다 카운팅 메소드 넣을거임
	// 				ReviewCount reviewCount = rService.selectReviewCount(rNo);
					
	// 				mv.addObject("reviewCount", reviewCount);
	// 				mv.setViewName("jsonView");
					
	// 				response.setContentType("application/json; charset=utf-8");
					
	// 				return mv;
					
	// 			}
	// 		}
			
			
			
			
	// 	}else {
	// 		throw new RestaurantException("리뷰  삭제에 실패하였습니다.");
	// 	}
	// 	ReviewCount reviewCount = rService.selectReviewCount(rNo);
		
	// 	mv.addObject("reviewCount", reviewCount);
		
	// 	mv.setViewName("jsonView");
		
	// 	response.setContentType("application/json; charset=utf-8");
		
	// 	return mv;

	// }
	
	@RequestMapping("resve.do")
	public ModelAndView insertResve(Reservation resve, ModelAndView mv, HttpServletResponse response) {
		//System.out.println(resve);
		
		int result = rService.insertResve(resve);
		
		/*int amount = Integer.parseInt(resve.getResveAmount());
		resve.setResveAmount(Integer.toString(amount / 100 * 5));
		
		int result1 = rService.insertPoint(resve);*/
		
		if(result != 0) {
			
			mv.setViewName("jsonView");
			
			response.setContentType("application/json; charset=utf-8");
			
			return mv;
			
			
		}else {
			throw new RestaurantException("예약 인서트 실패하였습니다.");
		}
	}
	

	// 리뷰지우기 기능 보류
	
	/*@RequestMapping("deleteImgFile.do")
	public ModelAndView deleteImgFile(ModelAndView mv, ReviewImg revImg, HttpServletRequest request, HttpServletResponse response) {
		
		String fileName = rService.deleteImgFile(revImg);
		System.out.println("db에서 삭제한 filename : " + fileName);
		
		if(!fileName.equals("")) {
			String root = request.getSession().getServletContext().getRealPath("resources");
			String savePath = root + "\\img\\review";
			
			File deleteFile = new File(savePath + "\\" + fileName);
			
			if(deleteFile.exists()) {
				deleteFile.delete();
				System.out.println("삭제 성공");
			}
			
		}
		mv.setViewName("jsonView");
		
		response.setContentType("application/json; charset=utf-8");
		
		return mv;
	}*/

}
