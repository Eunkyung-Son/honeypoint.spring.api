package com.ek.honeypoint.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Restaurant {
	
	private String rNo;
	private int mNo;
	private String mId;
	private String mPwd;
	private String mEmail;
	private Date mEnrollDate;
	private Date mModifyDate;
	private char mStatus;
	private char rConf;
	private String mOriginPf;
	private String mStrePf;
	private String rTel;
	private String rType;
	private String rName;
	private String rAddress;
	private String rOAddress;
	private String rTag;
	private String rPrice;
	private char rParking;
	private String rStartTime;
	private String rEndTime;
	private double rRating;
	private String rIntro;
	private char resveYn;
	private String rRestDay;
	private char rConfm;
	private char rStatus;
	private int rCount;

	public Restaurant() {}

	public Restaurant(String rNo, int mNo, String mId, String mPwd, String mEmail, Date mEnrollDate, Date mModifyDate,
			char mStatus, char rConf, String mOriginPf, String mStrePf, String rTel, String rType, String rName,
			String rAddress, String rOAddress, String rTag, String rPrice, char rParking, String rStartTime,
			String rEndTime, double rRating, String rIntro, char resveYn, String rRestDay, char rConfm, char rStatus,
			int rCount) {
		super();
		this.rNo = rNo;
		this.mNo = mNo;
		this.mId = mId;
		this.mPwd = mPwd;
		this.mEmail = mEmail;
		this.mEnrollDate = mEnrollDate;
		this.mModifyDate = mModifyDate;
		this.mStatus = mStatus;
		this.rConf = rConf;
		this.mOriginPf = mOriginPf;
		this.mStrePf = mStrePf;
		this.rTel = rTel;
		this.rType = rType;
		this.rName = rName;
		this.rAddress = rAddress;
		this.rOAddress = rOAddress;
		this.rTag = rTag;
		this.rPrice = rPrice;
		this.rParking = rParking;
		this.rStartTime = rStartTime;
		this.rEndTime = rEndTime;
		this.rRating = rRating;
		this.rIntro = rIntro;
		this.resveYn = resveYn;
		this.rRestDay = rRestDay;
		this.rConfm = rConfm;
		this.rStatus = rStatus;
		this.rCount = rCount;
	}

	public String getrNo() {
		return rNo;
	}

	public void setrNo(String rNo) {
		this.rNo = rNo;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPwd() {
		return mPwd;
	}

	public void setmPwd(String mPwd) {
		this.mPwd = mPwd;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public Date getmEnrollDate() {
		return mEnrollDate;
	}

	public void setmEnrollDate(Date mEnrollDate) {
		this.mEnrollDate = mEnrollDate;
	}

	public Date getmModifyDate() {
		return mModifyDate;
	}

	public void setmModifyDate(Date mModifyDate) {
		this.mModifyDate = mModifyDate;
	}

	public char getmStatus() {
		return mStatus;
	}

	public void setmStatus(char mStatus) {
		this.mStatus = mStatus;
	}

	public char getrConf() {
		return rConf;
	}

	public void setrConf(char rConf) {
		this.rConf = rConf;
	}

	public String getmOriginPf() {
		return mOriginPf;
	}

	public void setmOriginPf(String mOriginPf) {
		this.mOriginPf = mOriginPf;
	}

	public String getmStrePf() {
		return mStrePf;
	}

	public void setmStrePf(String mStrePf) {
		this.mStrePf = mStrePf;
	}

	public String getrTel() {
		return rTel;
	}

	public void setrTel(String rTel) {
		this.rTel = rTel;
	}

	public String getrType() {
		return rType;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getrAddress() {
		return rAddress;
	}

	public void setrAddress(String rAddress) {
		this.rAddress = rAddress;
	}

	public String getrOAddress() {
		return rOAddress;
	}

	public void setrOAddress(String rOAddress) {
		this.rOAddress = rOAddress;
	}

	public String getrTag() {
		return rTag;
	}

	public void setrTag(String rTag) {
		this.rTag = rTag;
	}

	public String getrPrice() {
		return rPrice;
	}

	public void setrPrice(String rPrice) {
		this.rPrice = rPrice;
	}

	public char getrParking() {
		return rParking;
	}

	public void setrParking(char rParking) {
		this.rParking = rParking;
	}

	public String getrStartTime() {
		return rStartTime;
	}

	public void setrStartTime(String rStartTime) {
		this.rStartTime = rStartTime;
	}

	public String getrEndTime() {
		return rEndTime;
	}

	public void setrEndTime(String rEndTime) {
		this.rEndTime = rEndTime;
	}

	public double getrRating() {
		return rRating;
	}

	public void setrRating(double rRating) {
		this.rRating = rRating;
	}

	public String getrIntro() {
		return rIntro;
	}

	public void setrIntro(String rIntro) {
		this.rIntro = rIntro;
	}

	public char getResveYn() {
		return resveYn;
	}

	public void setResveYn(char resveYn) {
		this.resveYn = resveYn;
	}

	public String getrRestDay() {
		return rRestDay;
	}

	public void setrRestDay(String rRestDay) {
		this.rRestDay = rRestDay;
	}

	public char getrConfm() {
		return rConfm;
	}

	public void setrConfm(char rConfm) {
		this.rConfm = rConfm;
	}

	public char getrStatus() {
		return rStatus;
	}

	public void setrStatus(char rStatus) {
		this.rStatus = rStatus;
	}

	public int getrCount() {
		return rCount;
	}

	public void setrCount(int rCount) {
		this.rCount = rCount;
	}

	@Override
	public String toString() {
		return "Restaurant [rNo=" + rNo + ", mNo=" + mNo + ", mId=" + mId + ", mPwd=" + mPwd + ", mEmail=" + mEmail
				+ ", mEnrollDate=" + mEnrollDate + ", mModifyDate=" + mModifyDate + ", mStatus=" + mStatus + ", rConf="
				+ rConf + ", mOriginPf=" + mOriginPf + ", mStrePf=" + mStrePf + ", rTel=" + rTel + ", rType=" + rType
				+ ", rName=" + rName + ", rAddress=" + rAddress + ", rOAddress=" + rOAddress + ", rTag=" + rTag
				+ ", rPrice=" + rPrice + ", rParking=" + rParking + ", rStartTime=" + rStartTime + ", rEndTime="
				+ rEndTime + ", rRating=" + rRating + ", rIntro=" + rIntro + ", resveYn=" + resveYn + ", rRestDay="
				+ rRestDay + ", rConfm=" + rConfm + ", rStatus=" + rStatus + ", rCount=" + rCount + "]";
	}
	
	
}