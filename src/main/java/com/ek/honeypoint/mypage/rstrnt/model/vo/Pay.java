package com.ek.honeypoint.mypage.rstrnt.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pay {
	
	private Date setleDe;	// 결제날짜
	private String rsvCtm;	// 예약자명
	private int visitorCo;	// 방문인원
	private int stpRc;		// 결제금액
	
	private int mNo;
	private String mId;
}
