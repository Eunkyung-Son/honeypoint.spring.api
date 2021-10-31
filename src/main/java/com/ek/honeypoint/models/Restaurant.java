package com.ek.honeypoint.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@JsonProperty("rNo")
	private int rNo; // 업체회원번호
	@JsonProperty("mNo")
	private int mNo; // 회원번호
	@JsonProperty("rTel")
	private String rTel; // 업체전화번호
	@JsonProperty("rType")
	private String rType; // 업종 카테고리
	@JsonProperty("rName")
	private String rName; // 업체명
	@JsonProperty("rAddress")
	private String rAddress; // 업체 주소
	@JsonProperty("rOAddress")
	private String rOAddress; // 업체지번주소
	@JsonProperty("rTag")
	private String rTag; // 검색태그 등록
	@JsonProperty("rPrice")
	private String rPrice; // 업체별 가격대
	@JsonProperty("rParking")
	private char rParking; // 주차장 유무
	@JsonProperty("rStartTime")
	private String rStartTime; //
	@JsonProperty("rEndTime")
	private String rEndTime; //
	@JsonProperty("rRating")
	private double rRating; //
	@JsonProperty("rIntro")
	private String rIntro; // 소개글
	@JsonProperty("resveYn")
	private char resveYn; // 예약가능여부
	@JsonProperty("rRestDay")
	private String rRestDay; //정기 휴무일
	@JsonProperty("rStatus")
	private char rStatus; // 1.미승인 2.승인 3.탈퇴
	@JsonProperty("rCount")
	private int rCount; // 조회수
	@JsonProperty("fileIds")
	private ArrayList<String> fileIds;
}
