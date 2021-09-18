package com.ek.honeypoint.mypage.member.model.service;

import java.util.ArrayList;

import com.ek.honeypoint.member.model.vo.Member;
import com.ek.honeypoint.mypage.common.PageInfo;
import com.ek.honeypoint.mypage.member.model.dao.MemberDao;
import com.ek.honeypoint.mypage.member.model.vo.PaidPoint;
import com.ek.honeypoint.mypage.member.model.vo.Post;
import com.ek.honeypoint.mypage.member.model.vo.ReservePay;
import com.ek.honeypoint.mypage.member.model.vo.UsedPoint;
import com.ek.honeypoint.mypage.member.model.vo.gnrlMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("mmpService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	@Autowired
	private MemberDao mDao;

	// 회원선택
	@Override
	public Member selectMemberInfo(String mId) {
		return mDao.selectMemberInfo(mId);
	}
	
	
	/* 일반회원 정보수정 */
	@Override
	public int updateMember(Member m) {
		return mDao.updateMember(m);
	}
	
	@Override
	public int updategnrlMember(Member m2) {
		return mDao.updategnrlMember(m2);
	}
	
	
	@Override
	public int updatemPassWord(Member m3) {
		String encPwd = bcryptPasswordEncoder.encode(m3.getmPwd());
		m3.setmPwd(encPwd);
		return mDao.updatemPassWord(m3);
	}
	

	/* 일반회원 탈퇴 */
	@Override
	public int deleteMember(String mId) {
		return mDao.deleteMember(mId);
	}

	
	/* 예약, 결제내역 */
	@Override
	public int selectReservePayListCount() {
		return mDao.selectReservePayListCount();
	}

	@Override
	public ArrayList<ReservePay> selectReservePayList(PageInfo pi) {
		return mDao.selectReservePayList(pi);
	}



	


	// 지급된 포인트내역
	@Override
	public int selectPaidPointListCount() {
		return mDao.selectPaidPointListCount();
	}
	
	@Override	
	public ArrayList<PaidPoint> selectPaidPointList(PageInfo pi) {
		return mDao.selectPaidPointList(pi);
	}
	
	
	// 사용한 포인트내역
	@Override
	public int selectUsedPointListCount() {
		return mDao.selectUsedPointListCount();
	}
	
	@Override
	public ArrayList<UsedPoint> selectUsedPointList(PageInfo pi) {
		return mDao.selectUsedPointList(pi);
	}

	
	
	
	// 게시글 조회
	@Override
	public int selectPostListCount() {
		return mDao.selectPostListCount();
	}

	@Override
	public ArrayList<Post> selectPostList(PageInfo pi) {
		return mDao.selectPostList(pi);
	}

	


	
}
