package com.ek.honeypoint.member.model.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.ek.honeypoint.member.model.vo.InsertResImg;
import com.ek.honeypoint.member.model.vo.Member;
import com.ek.honeypoint.member.model.vo.Menu;
import com.ek.honeypoint.member.model.vo.Restaurant;

public interface memberService {

	// 1. 회원 로그인 서비스를 위한 메소드
	public Member loginMember(Member member);

	// 2. 일반 회원 가입 서비스를 위한 메소드
	public int insertBasicMember(Member member);
	public int insertGnrlMember(Member member);
	public int insertPwdMember(Member member);
	
	// 3. 맛집 회원 가입 서비스를 위한 메소드
	public int insertBasicRestaurant(Restaurant r1);
	public int insertRestaurantInfo(Restaurant r2);
	public int insertPwdRestaurant(Restaurant r3);
	
	// 4. 아이디 중복 검사를 위한 메소드
	public int checkIdDup(String id);

	public int updatemPassword(Member member);

	// 메뉴 리스트 추가
	public int insertMenu(int rNo, String menuName, int menuPrice);

	// 레스토랑 이미지 추가
	public int insertResImg(int rNo, String originFileList, String renameFileList);

	public int selectRno();

	public int loginHistory(Member loginUser);

	public String findIdByEmail(String email) throws IOException, Exception;

	public void findPwd(String id, String email) throws IOException;

	void sendEmail(Member member, String div);

	public int getCountByEmail(String email);

	Member getMemberByEmail(String email);

  public Member selectMember(int getmNo);

}
