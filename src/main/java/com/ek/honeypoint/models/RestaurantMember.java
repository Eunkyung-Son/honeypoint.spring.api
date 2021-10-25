package com.ek.honeypoint.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMember {
  @JsonProperty("rNo")
	private String rNo;
  @JsonProperty("mNo")
	private int mNo;
  @JsonProperty("mId")
	private String mId;
  @JsonProperty("mPwd")
	private String mPwd;
  @JsonProperty("mEmail")
	private String mEmail;
  @JsonProperty("mEnrollDate")
	private Date mEnrollDate;
  @JsonProperty("mModifyDate")
	private Date mModifyDate;
  @JsonProperty("mStatus")
	private char mStatus;
  @JsonProperty("rConf")
	private char rConf;
  @JsonProperty("mOriginPf")
	private String mOriginPf;
  @JsonProperty("mStrePf")
	private String mStrePf;
  @JsonProperty("rTel")
	private String rTel;
  @JsonProperty("rType")
	private String rType;
  @JsonProperty("rName")
	private String rName;
  @JsonProperty("rAddress")
	private String rAddress;
  @JsonProperty("rOAddress")
	private String rOAddress;
  @JsonProperty("rTag")
	private String rTag;
  @JsonProperty("rPrice")
	private String rPrice;
  @JsonProperty("rParking")
	private char rParking;
  @JsonProperty("rStartTime")
	private String rStartTime;
  @JsonProperty("rEndTime")
	private String rEndTime;
  @JsonProperty("rRating")
	private double rRating;
  @JsonProperty("rIntro")
	private String rIntro;
  @JsonProperty("resveYn")
	private char resveYn;
  @JsonProperty("rRestDay")
	private String rRestDay;
  @JsonProperty("rConfm")
	private char rConfm;
  @JsonProperty("rStatus")
	private char rStatus;
  @JsonProperty("rCount")
	private int rCount;	
	
}