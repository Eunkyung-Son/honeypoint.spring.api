package com.ek.honeypoint.mypage.member.model.dao;

import java.util.ArrayList;

import com.ek.honeypoint.member.model.vo.Member;
import com.ek.honeypoint.mypage.common.PageInfo;
import com.ek.honeypoint.mypage.member.model.vo.PaidPoint;
import com.ek.honeypoint.mypage.member.model.vo.Post;
import com.ek.honeypoint.mypage.member.model.vo.ReservePay;
import com.ek.honeypoint.mypage.member.model.vo.UsedPoint;
import com.ek.honeypoint.mypage.member.model.vo.gnrlMember;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mmpDao")
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;	
	
	
	// 일반회원 정보수정
	public int updateMember(Member m) {
		return sqlSession.update("membermpMapper.updateMember", m);				
	}
	
	public int updategnrlMember(Member m2) {
		return sqlSession.update("membermpMapper.updategnrlMember", m2);
	}

	public int updatemPassWord(Member m3) {
		return sqlSession.update("membermpMapper.updatemPassWord",m3);
	}

	// 일반회원 탈퇴
	public int deleteMember(String mId) {
		System.out.println(mId);
		int result = sqlSession.delete("membermpMapper.deletempMember", mId);
		if(result != 0) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
		return result;
	}

	
	// 예약 결제내역
	public int selectReservePayListCount() {
		return sqlSession.selectOne("membermpMapper.selectReservePayListCount");
	}

	public ArrayList<ReservePay> selectReservePayList(PageInfo pi) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		ArrayList<ReservePay> list = (ArrayList)sqlSession.selectList("membermpMapper.selectReservePayList", null, rowBounds);
		return list;
	}

	// 회원구분
	public Member selectMember(Member m) {
		return sqlSession.selectOne("memberMapper.memSort",m);		
	}

	// 포인트 지급내역
	public int selectPaidPointListCount() {		
		return sqlSession.selectOne("membermpMapper.selectPaidPointListCount");
	}

	public ArrayList<PaidPoint> selectPaidPointList(PageInfo pi) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());		
		
		ArrayList<PaidPoint> list = (ArrayList)sqlSession.selectList("membermpMapper.selectPaidPointList", null, rowBounds);
		System.out.println("list" + list);

		return (ArrayList)sqlSession.selectList("membermpMapper.selectPaidPointList", null, rowBounds);
	}
	
	// 포인트 사용내역
	public int selectUsedPointListCount() {
		return sqlSession.selectOne("membermpMapper.selectUsedPointListCount");
	}

	public ArrayList<UsedPoint> selectUsedPointList(PageInfo pi) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return (ArrayList)sqlSession.selectList("membermpMapper.selectUsedPointList", null, rowBounds);
	}

	// 게시글조 조회내역
	public int selectPostListCount() {
		return sqlSession.selectOne("membermpMapper.selectPostListCount");
	}

	public ArrayList<Post> selectPostList(PageInfo pi) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
				
		return (ArrayList)sqlSession.selectList("membermpMapper.selectPostList", null, rowBounds);
	}

	public Member selectMemberInfo(String mId) {
		return sqlSession.selectOne("membermpMapper.selectMemberInfo", mId);
	}


}
