package com.ek.honeypoint.daos;

import java.util.ArrayList;
import java.util.List;

import com.ek.honeypoint.models.Member;
import com.ek.honeypoint.models.Menu;
import com.ek.honeypoint.models.Photofile;
import com.ek.honeypoint.models.RestaurantMember;

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
	
	public int insertBasicRestaurant(RestaurantMember restaurant) {
		return sqlSession.insert("memberMapper.insertBasicRestaurant", restaurant);
	}
	public int insertRestaurantInfo(RestaurantMember restaurant) {
		return sqlSession.insert("memberMapper.insertRestaurantInfo", restaurant);
	}
	public int insertPwdRestaurant(RestaurantMember restaurant) {
		return sqlSession.insert("memberMapper.insertPwdRestaurant", restaurant);
	}

	public int checkIdDup(String id) {
		return sqlSession.selectOne("memberMapper.idCheck", id);
	}

  public int checkEmailDup(String email) {
    return sqlSession.selectOne("memberMapper.emailCheck", email);
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
		value.setRNo(rNo);
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

  public Member selectMemeber(int mNo) {
    return sqlSession.selectOne("memberMapper.selectMember", mNo);
  }

	public int deleteMember(String memberId) {
		return sqlSession.update("memberMapper.deleteMember", memberId);
	}

  public int updateMember(Member member) {
    return sqlSession.update("memberMapper.updateMember", member);
  }

	public int updateGeneralMember(Member member) {
		return sqlSession.update("memberMapper.updateGeneralMember", member);
	}

  public int insertMenu(ArrayList<Menu> menu) {
    return sqlSession.insert("memberMapper.insertMenu", menu);
  }

  public int deleteMenu(int menuNo) {
    return sqlSession.delete("memberMapper.deleteMenu", menuNo);
  }

  public int updateMenu(ArrayList<Menu> menus) {
		int result = 1;
		for (Menu menu: menus) {
			int updateResult = sqlSession.update("memberMapper.updateMenu", menu);
			if (updateResult == 0) {
				result = 0;
			} 
		}
    return result;
  }

}
