package com.ek.honeypoint.mypage.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ek.honeypoint.member.model.exception.memberException;
import com.ek.honeypoint.member.model.service.memberService;
import com.ek.honeypoint.member.model.vo.Member;
import com.ek.honeypoint.mypage.common.PageInfo;
import com.ek.honeypoint.mypage.common.Pagination;
import com.ek.honeypoint.mypage.member.model.exception.MemberException;
import com.ek.honeypoint.mypage.member.model.service.MemberService;
import com.ek.honeypoint.mypage.member.model.vo.PaidPoint;
import com.ek.honeypoint.mypage.member.model.vo.PasswordRequest;
import com.ek.honeypoint.mypage.member.model.vo.Post;
import com.ek.honeypoint.mypage.member.model.vo.ReservePay;
import com.ek.honeypoint.mypage.member.model.vo.UsedPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({ "loginUser", "msg" })
@RestController
public class memberMyPageController {

	@Autowired
	private MemberService mService;

	@Autowired
	private memberService mService2;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private Logger logger = LoggerFactory.getLogger(memberMyPageController.class);

	/* 일반회원 마이페이지 */
	@RequestMapping("getMemberInfo")
	@ResponseBody
	public Member getMemberInfo(
		HttpServletRequest request,
		@RequestParam("mId") String mId
	) {
		Member member = mService.selectMemberInfo(mId);
		if (member != null) {
			return member;
		} else {
			throw new memberException("회원정보를 읽어오는데 실패했습니다.");
		}
	}

	// /******* 사이드 메뉴 ********/
	// /* 일반회원 예약 및 결제내역 */
	// @RequestMapping("memberreservepaylist.do")
	// public ModelAndView ReservePayList(ModelAndView mv,			
	// 								   @RequestParam(value = "currentPage", 
	// 								   required = false, 
	// 								   defaultValue = "1") Integer page,
	// 								   HttpSession session) {

	// 	Member loginUser = (Member)session.getAttribute("loginUser");
	// 	System.out.println(loginUser.getmNo());
	// 	int mNo = loginUser.getmNo();
	// 	Member m = mService.selectMemberInfo(mNo);
		
	// 	int currentPage = page != null ? page : 1;

	// 	int listCount = mService.selectReservePayListCount();

	// 	PageInfo pi = Pagination.getPageInfo(currentPage, listCount);

	// 	ArrayList<ReservePay> list = mService.selectReservePayList(pi);

	// 	if (list != null) {
	// 		mv.addObject("list", list);
	// 		mv.addObject("pi", pi);
	// 		mv.setViewName("/mypage/member/memberReservePayList");
	// 	} else {
	// 		throw new MemberException("예약 결제내역이 존재하지 않습니다.");
	// 	}

	// 	return mv;
	// }
	
	/* 포인트 지급내역 */
	// @RequestMapping("memberpaidpoint.do")
	// public ModelAndView MemberPaidPointList(ModelAndView mv, 
	// 								  @RequestParam(value="currentPage",
	// 								  required=false,
	// 								  defaultValue="1") Integer page) {
		
	// 	int currentPage = page != null ? page : 1;
		
	// 	int listCount = mService.selectPaidPointListCount();
		
	// 	PageInfo pi = Pagination.getPageInfo(currentPage, listCount);		
		
	// 	ArrayList<PaidPoint> list = mService.selectPaidPointList(pi); 
		
	// 	if (list != null) {
	// 		mv.addObject("list", list);
	// 		mv.addObject("pi", pi);
	// 		mv.setViewName("/mypage/member/memberPaidPointList");
	// 	} else {
	// 		throw new MemberException("포인트 지급내역이 존재하지 않습니다.");
	// 	}
		
	// 	return mv;
	// }
	
	// /* 포인트 사용내역 */
	// @RequestMapping("memberusedpoint.do")
	// public ModelAndView MemberUsedPointList(ModelAndView mv,
	// 								  @RequestParam(value="currentPage",
	// 								  required=false,
	// 								  defaultValue="1") Integer page) {
		
	// 	int currentPage = page != null ? page : 1;
		
	// 	int listCount = mService.selectUsedPointListCount();
		
	// 	PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
	// 	ArrayList<UsedPoint> list = mService.selectUsedPointList(pi);
		
	// 	if (list != null) {
	// 		mv.addObject("list", list);
	// 		mv.addObject("pi", pi);
	// 		mv.setViewName("/mypage/member/memberUsedPointList");
	// 	} else {
	// 		throw new MemberException("포인트 사용내역이 존재하지 않습니다.");
	// 	}
				
		
	// 	return mv;
	// }
	

	/* 작성 게시글목록 조회 */
	@RequestMapping("memberpost.do")
	public int MemberPostList(
		@RequestParam(value="currentPage", required=false, defaultValue="1") Integer page
	) {

		int currentPage = page != null ? page : 1;
		
		int listCount = mService.selectPostListCount();
		// PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		// ArrayList<Post> list = mService.selectPostList(pi);
		
		// model.addAttribute("currentPage", currentPage);
		// model.addAttribute("listCount", listCount);
		// model.addAttribute("list", list);
		
		return listCount;
		
	}


	//TODO: 일반회원 정보변경 이름, 닉네임, 생년월일, 이메일, 전화번호, 주소 변경
	@PutMapping("updateMember")
	public Member updateMember(
		@RequestBody Member newMemberInfo
	) {
		String mId = newMemberInfo.getmId();
		// service update Member
		mService.updateMember(newMemberInfo);
		Member updatedMemberInfo = mService.selectMemberInfo(mId);
		return updatedMemberInfo;
	}

	@PutMapping("updatePwd")
	public String updateMemberPwd(
		@RequestBody PasswordRequest pwdRequest
	) {
		Member member = new Member();
		member.setmId(pwdRequest.getMId());
		Member loginUser = mService2.loginMember(member);
		if(!bCryptPasswordEncoder.matches(pwdRequest.getOldPassword(), loginUser.getmPwd())) {
			return "비밀번호가 일치하지 않습니다";
		}
		loginUser.setmPwd(pwdRequest.getNewPassword());
		mService.updatemPassWord(loginUser);
		return "비밀번호가 변경 되었습니다";
	}

	// 일반회원 탈퇴
	@DeleteMapping("signOut")
	public String MemberDeletePage(
		String mId
	) {	
		int result = mService.deleteMember(mId);
		if (result > 0) {
			return mId;
		} else {
			throw new MemberException("회원 탈퇴에 실패 하였습니다.");
		}
	}
}