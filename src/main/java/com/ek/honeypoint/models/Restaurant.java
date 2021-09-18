package com.ek.honeypoint.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	private int rNo; // 업체회원번호
	private int mNo; // 회원번호
	private String rTel; // 업체전화번호
	private String rType; // 업종 카테고리
	private String rName; // 업체명
	private String rAddress; // 업체 주소
	private String rOAddress; // 업체지번주소
	private String rTag; // 검색태그 등록
	private String rPrice; // 업체별 가격대
	private char rParking; // 주차장 유무
	private String rStartTime; //
	private String rEndTime; //
	private double rRating; //
	private String rIntro; // 소개글
	private char resveYn; // 예약가능여부
	private String rRestDay; //정기 휴무일
	private char rStatus; // 1.미승인 2.승인 3.탈퇴
	private int rCount; // 조회수

}
