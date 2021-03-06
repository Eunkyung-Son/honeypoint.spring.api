package com.ek.honeypoint.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ek.honeypoint.daos.memberDao;
import com.ek.honeypoint.models.Member;
import com.ek.honeypoint.models.Menu;
import com.ek.honeypoint.models.RestaurantMember;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("memService")
public class memberServiceImpl implements memberService {

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private memberDao mDao;
	
	@Override
	public Member loginMember(Member member) {
		
		Member loginUser = mDao.selectMember(member);
		System.out.println(loginUser);
		
		if(!bcryptPasswordEncoder.matches(member.getmPwd(), loginUser.getmPwd())) {
			
		// 	// 첫번째 인자로 로그인시 입력한 pwd 평문
		// 	// 두번째 인자로 회원가입시 생성 된 pwd 다이제스트
		// 	// match되지 않으면 비밀번호 틀린 것이므로 loginUser를 null처리
			loginUser = null;
			
		}
		return loginUser;
	}

	@Override
	public Member selectMember(int mNo) {
		return mDao.selectMemeber(mNo);
	}

	@Override
	public int updateMember(Member member) {
		return mDao.updateMember(member);
	}

	@Override
	public int updateGeneralMember(Member member) {
		return mDao.updateGeneralMember(member);
	}

	@Override
	public int insertBasicMember(Member member) {
		return mDao.insertBasicMember(member);
	}
	@Override
	public int insertGnrlMember(Member member) {
		return mDao.insertGnrlMember(member);
	}
	@Override
	public int insertPwdMember(Member member) {
		String encPwd = bcryptPasswordEncoder.encode(member.getmPwd());
		member.setmPwd(encPwd);
		return mDao.insertPwdMember(member);
	}

	@Override
	public int insertBasicRestaurant(RestaurantMember restaurant) {
		return mDao.insertBasicRestaurant(restaurant);
	}

	@Override
	public int insertRestaurantInfo(RestaurantMember restaurant) {
		return mDao.insertRestaurantInfo(restaurant);
	}

	@Override
	public int insertPwdRestaurant(RestaurantMember restaurant) {
		String encPwd = bcryptPasswordEncoder.encode(restaurant.getMPwd());
		restaurant.setMPwd(encPwd);
		return mDao.insertPwdRestaurant(restaurant);
	}

	@Override
	public int deleteMember(String memberId) {
		return mDao.deleteMember(memberId);
	}
	
	
	@Override
	public int checkIdDup(String id) {
		return mDao.checkIdDup(id);
	}

	@Override
	public int checkEmailDup(String email) {
		return mDao.checkEmailDup(email);
	}

	@Override
	public int updatemPassword(Member member) {
		String encPwd = bcryptPasswordEncoder.encode(member.getmPwd());
		member.setmPwd(encPwd);
		return mDao.updatemPassword(member);
	}

	@Override
	public int insertResImg(int rNo, String originFileList, String renameFileList) {
		return mDao.insertResImg(rNo, originFileList,renameFileList);
	}

	@Override
	public int insertMenu(int rNo, String menuName, int menuPrice) {
		return mDao.insertMenu(rNo, menuName,menuPrice);
	}

	@Override
	public int selectRno() {
		return mDao.selectRno();
	}

	@Override
	public int loginHistory(Member loginUser) {
		return mDao.insertHistory(loginUser);
	}
	
	public String findIdByEmail(String email) throws Exception {
		return mDao.findIdByEmail(email);
	}
	
	@Override
	public int getCountByEmail(String email) {
		return mDao.getCountByEmail(email);
	}
	
	@Override
	public Member getMemberByEmail(String email) {
		return mDao.getMemberByEmail(email);
	}

	@Override
	public int insertMenu(ArrayList<Menu> menu) {
		return mDao.insertMenu(menu);
	}

	@Override
	public int updateMenu(ArrayList<Menu> menu) {
		return mDao.updateMenu(menu);
	}

	@Override
	public int deleteMenu(int menuNo) {
		return mDao.deleteMenu(menuNo);
	}
	
	// TODO: 이메일 설정하기
	@Override
	public void sendEmail(Member member, String div){
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "sek9510@gmail.com";
		String hostSMTPpwd = "vopsfplalkmlidhw";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "sek9510@gmail.com";
		String fromName = "허니포인트";
		String subject = "";
		String msg = "";
		
		
		if(div.equals("find_pw")) {
			subject = "Honey Point 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += member.getmId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += member.getmPwd() + "</p></div>";
		}
		// 받는 사람 E-Mail 주소
		String mail = member.getmEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}

	@Override
	public void findPwd(String id, String email) throws IOException {
		int countById = mDao.checkIdDup(id);
		int countByEmail = mDao.getCountByEmail(email);
		Member mem = mDao.getMemberByEmail(email);
		// 아이디가 없으면
		if(countById == 0) {
			// TODO: 가입한 아이디에 대한 처리
			// out.println("<script>");
			// out.println("alert('가입된 아이디가 없습니다.');");
			// out.println("history.go(-1);");
			// out.println("</script>");
			// out.close();
		}
		// 가입에 사용한 이메일이 아니면
		else if(countByEmail == 0) {
			// TODO: 가입한 이메일에 대한 처리
			// out.println("<script>");
			// out.println("alert('잘못된 이메일 입니다.');");
			// out.println("history.go(-1);");
			// out.println("</script>");
			// out.close();
		}else {
			// TODO: 다 처리 됐을때에 대한 처리
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			//인코딩 되기 전 비밀번호로 이메일 전송
			mem.setmPwd(pw);
			sendEmail(mem,"find_pw");
			// 이메일 전송 후 비밀번호 인코딩 해서 디비에 저장
			String encodedPwd = bcryptPasswordEncoder.encode(pw);
			mem.setmPwd(encodedPwd);
			mDao.updatePwd(mem);
			
			// out.println("<script>");
			// out.println("alert('임시 비밀번호를 이메일로 발송했습니다.');");
			// out.println("history.go(-1);");
			// out.println("</script>");
			// out.close();
			// out.close();
		}
		
	}

}

