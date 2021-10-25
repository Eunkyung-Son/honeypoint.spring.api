package com.ek.honeypoint.models;

import com.ek.honeypoint.models.GnrlMember;
import com.ek.honeypoint.models.Member;
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
public class Review {
	@JsonProperty("revNo")
	private int revNo;
	@JsonProperty("rNo")
	private int rNo;
	@JsonProperty("revCn")
	private String revCn;
	@JsonProperty("revDate")
	private String revDate;
	@JsonProperty("score")
	private int score;
	@JsonProperty("revStatus")
	private char revStatus;
	@JsonProperty("mNo")
	private int mNo;

	@JsonProperty("member")
	private Member member;
	@JsonProperty("gnrlMember")
	private GnrlMember gnrlMember;
}
