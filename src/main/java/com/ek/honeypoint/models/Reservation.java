package com.ek.honeypoint.models;

import lombok.Data;

@Data
public class Reservation {
	private int resveNo;
	private String rsvctm; // 예약자
	private String rsvde; // 예약일
	private String rsvtm; // 예약시간
	private int visitrCo; // 방문인원
	private String resveReq; // 요청사항
	private String resvePhone; // 전화번호
	private int rNo;
	private int mNo;
	private String resveAmount; // 예약금
}
