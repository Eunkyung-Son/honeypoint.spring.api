package com.ek.honeypoint.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
  @JsonProperty("cmtNo")
	private int cmtNo;
  @JsonProperty("mNo")
	private int mNo;
  @JsonProperty("mId")
	private String mId;
  @JsonProperty("mNickname")
	private String mNickname;
  @JsonProperty("bNo")
	private int bNo;
  @JsonProperty("cmtContent")
	private String cmtContent;
  @JsonProperty("cmtEnrollDate")
	private String cmtEnrollDate;
  @JsonProperty("cmtModifyDate")
	private String cmtModifyDate;
  @JsonProperty("cmtStatus")
	private char cmtStatus; 
}
