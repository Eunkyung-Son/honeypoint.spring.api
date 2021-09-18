package com.ek.honeypoint.mypage.rstrnt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class rstrntMyPageController {
	
	// 맛집 마이페이지
	@RequestMapping("rstrntmp.do")
	public String rstrntMyPage() {
		return "mypage/rstrntMyPage";
	}
	
	// 문의글 등록하기
	@RequestMapping("rstrntInqry.do")
	public String rstrntInqry() {
		return "mypage/rstrntInqry";
	}
	
	// 결제내역 조회
	@RequestMapping("rstrntPay.do")
	public String rstrntPay() {
		return "mypage/rstrntPay";
	}
	
	
}
