package com.ek.honeypoint.mypage.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private int bNo;
	private String bTitle;
	private Date bEnrollDate;
	private String bType;
	private String bCategory;
}
