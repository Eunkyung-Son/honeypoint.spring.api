package com.ek.honeypoint.member.model.dao;

import com.ek.honeypoint.member.model.vo.Member;
import com.ek.honeypoint.member.model.vo.Menu;
import com.ek.honeypoint.member.model.vo.Photofile;
import com.ek.honeypoint.member.model.vo.Restaurant;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("memDao")	 
public class memberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public Member selectMember(Member member) {
		System.out.println(member.toString());
		return sqlSession.selectOne("memberMapper.memSort",member);
	}
	
	
	public int insertBasicMember(Member member) {
		return sqlSession.insert("memberMapper.insertBasicMember", member);
	}
	public int insertGnrlMember(Member member) {
		return sqlSession.insert("memberMapper.insertGnrlMember", member);
	}
	public int insertPwdMember(Member member) {
		return sqlSession.insert("memberMapper.insertPwdMember", member);
	}
	
	
	public int insertBasicRestaurant(Restaurant restaurant) {
		return sqlSession.insert("memberMapper.insertBasicRestaurant", restaurant);
	}
	public int insertRestaurantInfo(Restaurant restaurant) {
		return sqlSession.insert("memberMapper.insertRestaurantInfo", restaurant);
	}
	public int insertPwdRestaurant(Restaurant restaurant) {
		return sqlSession.insert("memberMapper.insertPwdRestaurant", restaurant);
	}

	
	public int checkIdDup(String id) {
		return sqlSession.selectOne("memberMapper.idCheck", id);
	}

	public int updatemPassword(Member member) {
		return sqlSession.update("memberMapper.updatemPassword", member);
	}

	public int insertMenu(int rNo, String menuName, int menuPrice) {
		Menu menu = new Menu();
		menu.setrNo(rNo);
		menu.setMenuName(menuName);
		menu.setMenuPrice(menuPrice);
		return sqlSession.insert("memberMapper.insertMenu", menu);
	}


	public int insertResImg(int rNo, String originFileList, String renameFileList) {
		Photofile value = new Photofile();
		value.setrNo(rNo);
		value.setOriginFileName(originFileList);
		value.setStreFileName(renameFileList);
		return sqlSession.insert("memberMapper.insertResImg", value);
	}


	public int selectRno() {
		return sqlSession.selectOne("memberMapper.selectRno");
	}


	public int insertHistory(Member loginUser) {
		
		return sqlSession.insert("memberMapper.insertHistory", loginUser);
	}


	public String findIdByEmail(String email) {
		return sqlSession.selectOne("memberMapper.findIdByEmail", email);
	}
	
	public int getCountByEmail(String email) {
		return sqlSession.selectOne("memberMapper.countMemberByEmail", email);
	}
	
	public Member getMemberByEmail(String email) {
		return sqlSession.selectOne("memberMapper.getMemberByEmail", email);
	}

	public int updatePwd(Member m){
		return sqlSession.update("memberMapper.updatePwd", m);
	}

}
