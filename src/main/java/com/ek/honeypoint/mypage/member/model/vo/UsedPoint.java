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
public class UsedPoint {
	private Date pointDate;
	private String rName;
	private int pointHistory;
	private int mPoint;
}
