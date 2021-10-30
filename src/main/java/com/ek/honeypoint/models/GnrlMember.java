package com.ek.honeypoint.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnrlMember {
	@JsonProperty("mNo")
	private int mNo;
	@JsonProperty("mNickname")
	private String mNickname;
	@JsonProperty("mBirthday")
	private int mBirthday;
	@JsonProperty("mPhone")
	private int mPhone;
	@JsonProperty("mAddress")
	private String mAddress;
	@JsonProperty("mGrad")
	private int mGrad;
	@JsonProperty("mPoint")
	private int mPoint;
}
