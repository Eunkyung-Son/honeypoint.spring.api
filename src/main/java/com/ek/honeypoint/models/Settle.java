package com.ek.honeypoint.models;

import lombok.Data;

@Data
public class Settle {
	private int setleNo;	// 결제번호
	private String setler;	// 결제자
	private String setleDe;	// 결제일
	private String stprc;	// 결제가격
	private String refndAt; // 환불여부
	private int resveNo;	// 예약번호
	private int mNo;
}
