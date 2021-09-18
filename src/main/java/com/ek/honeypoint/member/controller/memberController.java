package com.ek.honeypoint.member.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.ek.honeypoint.member.model.exception.memberException;
import com.ek.honeypoint.member.model.service.memberService;
import com.ek.honeypoint.member.model.vo.InsertResImg;
import com.ek.honeypoint.member.model.vo.Member;
import com.ek.honeypoint.member.model.vo.Menu;
import com.ek.honeypoint.member.model.vo.Restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@SessionAttributes({ "loginUser", "msg" })
@RestController
public class memberController {

	@Autowired
	private memberService mService;

	private Logger logger = LoggerFactory.getLogger(memberController.class);

	/**
	 * 로그인
	 * @param member 로그인 요청 member
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Member memberLogin(
		@RequestBody Member member
	) {
		Member loginUser = mService.loginMember(member);
		// int result = mService.loginHistory(loginUser);
		if (loginUser != null) {
			return loginUser;
		} else {
			throw new memberException("로그인에 실패하였습니다");
		}
	}

	/**
	 * 아이디 찾기
	 * @param email
	 * @return 아이디
	 * @throws Exception
	 */
	@RequestMapping(value = "findId", method = RequestMethod.POST)
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
	@RequestMapping(value = "findPwd", method = RequestMethod.POST)
	@ResponseBody
	public void find_pwd(
		@RequestParam("id") String id,
		@RequestParam("email") String email
	) throws Exception{
		mService.findPwd(id, email);
		// mService.findIdByEmail(email); TODO: 의미가 뭐지?
	}
	
	/**
	 * (일반 유저) 회원 가입
	 * @param member
	 * @return 회원 가입한 멤버
	 */
	@RequestMapping(value = "memberInsert", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
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
	public Restaurant resInsert(
		@RequestBody Restaurant restaurant
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