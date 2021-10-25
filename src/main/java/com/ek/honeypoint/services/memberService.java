package com.ek.honeypoint.services;

import java.io.IOException;


import com.ek.honeypoint.models.Member;
import com.ek.honeypoint.models.RestaurantMember;

public interface memberService {

	// 1. 회원 로그인 서비스를 위한 메소드
	public Member loginMember(Member member);

	// 2. 일반 회원 가입 서비스를 위한 메소드
	public int insertBasicMember(Member member);
	public int insertGnrlMember(Member member);
	public int insertPwdMember(Member member);
	
	// 3. 맛집 회원 가입 서비스를 위한 메소드
	public int insertBasicRestaurant(RestaurantMember r1);
	public int insertRestaurantInfo(RestaurantMember r2);
	public int insertPwdRestaurant(RestaurantMember r3);
	
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

  public int checkEmailDup(String email);

  public int deleteMember(String memberId);

  public int updateMember(Member member);

  public int updateGeneralMember(Member member);

}
