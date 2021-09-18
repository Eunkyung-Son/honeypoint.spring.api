package com.ek.honeypoint.mypage.member.model.vo;

import lombok.Data;

@Data
public class PasswordRequest {
	private String oldPassword;
	private String newPassword;
	private String mId;
}
