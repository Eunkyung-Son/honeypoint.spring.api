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
public class PaidPoint {
	private Date setleDe;
	private String rstrntInfo;
	private int stPrc;
	private int mPoint;
}
