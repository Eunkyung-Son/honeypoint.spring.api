package com.ek.honeypoint.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GnrlMember {
	private int mNo;
	private String mNickname;
	private int mBirthday;
	private int mPhone;
	private String mAddress;
	private int mGrad;
	private int mPoint;
}
