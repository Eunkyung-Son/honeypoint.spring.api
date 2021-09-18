package com.ek.honeypoint.mypage.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservePay {
	
	
	
	private String rName;
	private int visitrCo;
	private int rsvAmount;
	private Date rsvDe;
	
	private int mNo;
	private String mId;
}
