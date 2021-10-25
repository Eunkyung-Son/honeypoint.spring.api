package com.ek.honeypoint.models;

import java.sql.Date;

import lombok.Data;

public class NewMember {
	
	private int mNo;
	private String mId;
	private String mName;
	private String mEmail;
	private Date mEnrollDate;
	private Date mModifyDate;
	private char mStatus;
	private int rConf;
	private String mOriginPf;
	private String mStrePf;
	private int mSortNo;
	private String mNickname;
	private String mBirthday;
	private String mPhone;
	private String mAddress;
	private String mGrad;
	private int mPoint;
	private String mPwd;
	
	public NewMember() {}
	
	
	
}
