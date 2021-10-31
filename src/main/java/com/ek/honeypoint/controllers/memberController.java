package com.ek.honeypoint.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ek.honeypoint.exceptions.memberException;
import com.ek.honeypoint.services.BoardService;
import com.ek.honeypoint.services.FavorService;
import com.ek.honeypoint.services.RestaurantService;
import com.ek.honeypoint.services.ReviewService;
import com.ek.honeypoint.services.memberService;
import com.ek.honeypoint.models.InsertResImg;
import com.ek.honeypoint.models.Member;
import com.ek.honeypoint.models.Menu;
import com.ek.honeypoint.models.RestaurantMember;
import com.ek.honeypoint.models.Review;
import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.Comment;
import com.ek.honeypoint.models.HPResponse;
import com.ek.honeypoint.models.PasswordRequest;
import com.ek.honeypoint.models.Restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SessionAttributes({ "loginUser", "msg" })
@RestController
public class memberController {

	@Autowired
	private memberService mService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private Logger logger = LoggerFactory.getLogger(memberController.class);

	/**
	 * 로그인
	 * @param member 로그인 요청 member
	 * @return
	 */
	@RequestMapping(value = "api/login", method = RequestMethod.POST)
	@ResponseBody
	public HPResponse memberLogin(
		@RequestBody Member member
	) {
		HPResponse response = new HPResponse();
		Member loginUser = mService.loginMember(member);
		if (loginUser != null) {
			if (loginUser.getmSortNo() == 1) {
				member = mService.selectMember(loginUser.getmNo());
				response.put("member", member);
			} else {
				response.put("member", loginUser);
			}
		} else {
			response.put("error", true);
			response.put("msg", "로그인에 실패하였습니다.");			
		}
		return response;
	}

	/**
	 * 아이디 찾기
	 * @param email
	 * @return 아이디
	 * @throws Exception
	 */
	@RequestMapping(value = "api/findId", method = RequestMethod.POST)
	@ResponseBody
	public String findId(
		@RequestParam("email") String email 
	) throws Exception {
		
		String id = mService.findIdByEmail(email);
		if (id != null) {
			return id;
		} else {
			return "";
		}
	}
	
	// FIXME: 비밀번호 찾기
	@RequestMapping(value = "api/findPwd", method = RequestMethod.POST)
	@ResponseBody
	public void find_pwd(
		@RequestParam("id") String id,
		@RequestParam("email") String email
	) throws Exception {
		mService.findPwd(id, email);
		// mService.findIdByEmail(email); TODO: 의미가 뭐지?
	}
	
	// 비밀번호 변경
	@RequestMapping(value = "api/resetPwd", method = RequestMethod.POST)
	@ResponseBody
	@Transactional("transactionManager")
	public HPResponse resetPwd (
		@RequestBody PasswordRequest pwdRequest
	) {
		HPResponse response = new HPResponse();
		Member member = new Member();
		member.setmId(pwdRequest.getMId());
		member.setmPwd(pwdRequest.getOldPassword());
		Member loginUser = mService.loginMember(member);
		if(loginUser == null || !bCryptPasswordEncoder.matches(pwdRequest.getOldPassword(), loginUser.getmPwd())) {
			response.put("error", true);
			response.put("msg", "비밀번호가 일치하지 않습니다");
			return response;
		}
		loginUser.setmPwd(pwdRequest.getNewPassword());
		mService.updatemPassword(loginUser);
		return response;
	}

	// 일반회원 회원 정보 수정
	@RequestMapping(value = "api/member/general/update/{mNo}", method = RequestMethod.POST)
	@ResponseBody
	public HPResponse updateMember (
		@RequestBody Member member,
		@PathVariable(value = "mNo") int mNo
	) {
		member.setmNo(mNo);
		System.out.println(member+ "memberInfo!!");
		HPResponse response = new HPResponse();
		int updateMemberResult = mService.updateMember(member);
		int updateGeneralMemeberResult = mService.updateGeneralMember(member);
		if (updateMemberResult > 0 && updateGeneralMemeberResult > 0) {
			response.put("msg", "회원 정보 변경이 완료되었습니다.");
			member = mService.selectMember(member.getmNo());
			response.put("member", member);
		} else {
			response.put("error", true);
			response.put("msg", "회원 정보 변경에 실패하였습니다.");
		}
		return response;
	}

	// 일반회원 탈퇴
	@RequestMapping(value = "api/member/delete/{memberId}", method = RequestMethod.POST)
	@ResponseBody
	public HPResponse deleteMember (
		@PathVariable(value = "memberId") String memberId
	) {
		HPResponse response = new HPResponse();
		int deleteResult = mService.deleteMember(memberId);
		if (deleteResult > 0) {
			response.put("msg", "탈퇴가 완료되었습니다.");
		} else {
			response.put("error", true);
			response.put("msg", "회원 탈퇴에 실패하였습니다.");
		}
		return response;
	}

	/**
	 * (일반 유저) 회원 가입
	 * @param member
	 * @return 회원 가입한 멤버
	 */
	@RequestMapping(value = "memberInsert", method = RequestMethod.POST)
	@ResponseBody
	@Transactional("transactionManager1")
	public Member memberInsert(
		@RequestBody Member member
	) {
		// TB_MEMBER
		int basicResult = mService.insertBasicMember(member);
		// TB_GNRL_MEMBER
		int gnrlResult = mService.insertGnrlMember(member);
		// TB_M_PASSWORD
		int pwdResult = mService.insertPwdMember(member);

		if (basicResult > 0 && gnrlResult > 0 && pwdResult > 0) {
			return member;
		} else {
			throw new memberException("회원가입에 실패하였습니다.");
		}
	}

	/**
	 * (맛집회원) 회원가입
	 * @param restaurant
	 * @return 회원가입한 맛집회원
	 */
	@RequestMapping(value = "restaurantInsert", method = RequestMethod.POST)
	@ResponseBody
	@Transactional("transactionManager")
	public RestaurantMember resInsert(
		@RequestBody RestaurantMember restaurant
	) {
		int basicResult = mService.insertBasicRestaurant(restaurant);
		int rstrntResult = mService.insertRestaurantInfo(restaurant);
		int pwdResult = mService.insertPwdRestaurant(restaurant);

		if (basicResult > 0 && rstrntResult > 0 && pwdResult > 0) {
			return restaurant;
		} else {
			throw new memberException("회원가입에 실패하였습니다.");
		}
	}

	/**
	 * 아이디 중복확인
	 * @param id 아이디
	 * @return 사용가능한지에 대한 true / false
	 */
	@RequestMapping(value = "idCheck", method = RequestMethod.GET)
	@ResponseBody
	public Boolean idDuplicateCheck(
		@RequestParam("id") String id
	) {
		boolean isUsable = mService.checkIdDup(id) == 0 ? true : false;
		return isUsable;
	}

	/**
	 * 이메일 중복확인
	 * @param email 이메일
	 * @return 사용가능한지에 대한 true / false
	 */
	@RequestMapping(value = "emailCheck", method = RequestMethod.GET)
	@ResponseBody
	public Boolean emailDuplicateCheck(
		@RequestParam("email") String email
	) {
		boolean isUsable = mService.checkEmailDup(email) == 0 ? true : false;
		return isUsable;
	}

	// 일반회원 마이페이지 활동 내역
	@GetMapping(value = "api/member/activities/{mNo}")
	@ResponseBody
	public HPResponse selectGeneralMemberActivities (
		@PathVariable(value = "mNo") int mNo,
		@RequestParam(value = "fetchMyComment", defaultValue = "false") Boolean fetchMyComment,
		@RequestParam(value = "fetchMyReview", defaultValue = "false") Boolean fetchMyReview,
		@RequestParam(value = "fetchMyBoard", defaultValue = "false") Boolean fetchMyBoard
	) {
		ArrayList<Review> reviewList = null;
		ArrayList<Board> boardList = null;
		ArrayList<Comment> commentList = null;

		int reviewCount = 0;
		int boardCount = 0;
		int commentCount = 0;
		HPResponse response = new HPResponse();

		if (fetchMyBoard == true) {
			boardList = boardService.selectMyBoard(mNo);
			response.put("boardList", boardList);
			boardCount = boardList.size();
			response.put("boardCount", boardCount);
		}

		if (fetchMyComment == true) {
			commentList = boardService.selectMyComment(mNo);
			response.put("commentList", commentList);
			commentCount = commentList.size();
			response.put("commentCount", commentCount);
		}

		if (fetchMyReview == true) {
			reviewList = reviewService.getReviewsByMember(mNo);
			response.put("reviewList", reviewList);
			reviewCount = reviewList.size();
			response.put("reviewCount", reviewCount);
		}
		return response;
	}

	// 레스토랑 회원 메뉴 추가
	@PostMapping(value = "api/menu/insertMenu")
	@Transactional("transactionManager")
	@ResponseBody
	public HPResponse insertMenu (
    @RequestBody ArrayList<Menu> menu
	) {
		System.out.println("#####menu####" + menu);
		HPResponse response = new HPResponse();
		int insertResult = mService.insertMenu(menu);
		if (insertResult > 0) {
			response.put("menu", menu);
			response.put("msg", "메뉴 등록에 성공하였습니다.");
		} else {
			response.put("error", true);
			response.put("msg", "메뉴 등록에 실패하였습니다.");
		}
		return response;
	}

	// 레스토랑 회원 메뉴 수정
	@PostMapping(value = "api/menu/updateMenu/{rNo}")
	@ResponseBody
	public HPResponse updateMenu (
		@RequestBody ArrayList<Menu> menus,
		@PathVariable(value = "rNo") int rNo
	) {
		HPResponse response = new HPResponse();
		/**
		 * 입력된 menus 에서 menuId가 없으면 새로 생성
		 * 있으면 수정
		 */
		ArrayList<Menu> prevMenus = restaurantService.selectMenuList(rNo);
		ArrayList<Menu> deleteMenus = new ArrayList<Menu>();
		ArrayList<Menu> addMenus = new ArrayList<Menu>();
		ArrayList<Menu> updateMenus = new ArrayList<Menu>();
		if (prevMenus.size() > 0) {
			for (Menu prevMenu: prevMenus) {
				Boolean isExist = false;
				for (Menu menu: menus) {
					Boolean equal = menu.getMenuNo() == prevMenu.getMenuNo();
					if (equal) {
						isExist = true;
					}
				}
				if (isExist == false) {
					deleteMenus.add(prevMenu);
				}
			}
		}
		if (menus.size() > 0) {
			for (Menu menu: menus) {
				if (menu.getMenuNo() != 0) {
					updateMenus.add(menu);
				} else {
					addMenus.add(menu);
				}
			}
		}
		int deleteResult = 1;
		for (Menu deleteMenu: deleteMenus) {
			int menuDeleteResult = mService.deleteMenu(deleteMenu.getMenuNo());
			if (menuDeleteResult != 1) {
				deleteResult = menuDeleteResult;
			}
		}
		int insertResult = mService.insertMenu(addMenus);
		int updateResult = mService.updateMenu(updateMenus);
		if (deleteResult > 0 & updateResult > 0 & insertResult > 0) {
			menus = restaurantService.selectMenuList(rNo);
			response.put("menus", menus);
		} else {
			response.put("error", true);
			response.put("msg", "메뉴 수정에 실패하였습니다.");
		}
		return response;
	}

	// 레스토랑 회원 메뉴 삭제
	@DeleteMapping(value = "api/menu/{menuNo}")
	@ResponseBody
	public HPResponse deleteMenu(
		@PathVariable(value = "menuNo") int menuNo
	) {
		HPResponse response = new HPResponse();
		int deleteResult = mService.deleteMenu(menuNo);
		if (deleteResult > 0) {
			response.put("error", false);
			response.put("msg", "정상적으로 삭제되었습니다.");
		} else {
			response.put("error", true);
			response.put("msg", "메뉴 삭제에 실패하였습니다");
		}
		return response;
	}

	// TODO: 파일 저장 때문에 보류
	@RequestMapping(value = "insertMenu.do", method = RequestMethod.POST)
	public String menuInsert(Menu menu, HttpServletRequest request, MultipartHttpServletRequest multi) {
		int rNo = mService.selectRno();
		
		
		String[] menuName = request.getParameterValues("menuName"); 
		String[] menuPrice = request.getParameterValues("menuPrice"); 
		int result = 0;
		int result2 = 0;
		for (int i = 0; i < menuName.length; i++) { 
			System.out.println(menuName[i]); 
			result = mService.insertMenu(rNo,menuName[i],Integer.parseInt(menuPrice[i]));
			}
		

		if (result > 0) { // 메뉴 가격등등 추가 완료됬을때
			if (multi.getFileNames().hasNext()) {// 맛집 이미지파일이 존재할때
				
				System.out.println("첫번째");
				
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "//img//detailview";
				String fileName = "";
				ArrayList<String> originFileList = new ArrayList<String>();
				ArrayList<String> renameFileList = new ArrayList<String>();

				File folder = new File(savePath);

				if (folder.exists()) {
					folder.mkdirs();
				}

				Iterator<String> files = multi.getFileNames();
				
				System.out.println("두번째");
				
				while (files.hasNext()) {
					String uploadFile = files.next();

					MultipartFile mFile = multi.getFile(uploadFile);
					System.out.println("원본 파일 이름 : " + mFile.getOriginalFilename());
					if (mFile.getOriginalFilename().equals("")) {
						continue;
					}

					// 파일 이름짓기
					int ranNum = (int) (Math.random() * 100000);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String originFileName = mFile.getOriginalFilename();
					fileName = sdf.format(new Date()) + "_" + ranNum
							+ originFileName.substring(originFileName.lastIndexOf("."));

					try {
						System.out.println(folder + "//" + fileName);
						mFile.transferTo(new File(folder + "//" + fileName));
						originFileList.add(originFileName);
						renameFileList.add(fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				InsertResImg value = new InsertResImg();

				value.setrNo(menu.getrNo());
				value.setOriginFileList(originFileList);
				value.setRenameFileList(renameFileList);

				System.out.println("---------파일밸류값 확인 : "+ value);
				for (int i = 0; i < originFileList.size(); i++) { 
					System.out.println(originFileList.get(i)); 
					result2 = mService.insertResImg(rNo,originFileList.get(i),renameFileList.get(i));
					}
				
				if (result2 > 0) {
					System.out.println("맛집 이미지 디비 등록완료");
					
				} else {
					System.out.println("맛집 이미지 디비 등록실패");
				}
			}else {
				System.out.println("맛집 이미지 추가해야함");
			}
		} else {
			throw new memberException("맛집 등록에 실패하였습니다.");
		}
		return "member/rFinish";
	}
	
	
}