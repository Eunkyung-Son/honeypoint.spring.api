package com.ek.honeypoint.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
  @JsonProperty("bNo")
	private int bNo;
  @JsonProperty("mNo")
	private int mNo;
  @JsonProperty("mId")
	private String mId;
  @JsonProperty("mNickname")
	private String mNickname;
  @JsonProperty("bType")
	private String bType;
  @JsonProperty("bCategory")
	private String bCategory;
  @JsonProperty("bTitle")
	private String bTitle;
  @JsonProperty("bContent")
	private String bContent;
  @JsonProperty("bEnrollDate")
	private String bEnrollDate;
  @JsonProperty("bModifyDate")
	private String bModifyDate;
  @JsonProperty("bCount")
	private int bCount;
  @JsonProperty("bStatus")
	private char bStatus;
}
