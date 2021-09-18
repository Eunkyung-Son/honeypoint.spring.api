package com.ek.honeypoint.mypage.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageInfo {

	private int currentPage;	// 현재 페이지
	private int listCount;		// 전체 게시글
	private int pageLimit;		// 한 페이지에 보여질 글 수
	
	private int maxPage;		// 마지막 페이지
	private int startPage;		// 시작 페이지
	private int endPage;		// 마지막 페이지
	
	private int boardLimit;
	
/*	public PageInfo(int currentPage, int listCount, int pageLimit, int maxPage, int startPage, int endPage, int boardLimit) {};*/
	
}
