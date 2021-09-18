package com.ek.honeypoint.models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateReviewImg {
	private ArrayList<String> originFileList;
	private ArrayList<String> renameFileList;
	private int revNo;
	private int rNo;
	private ArrayList<Integer> lastNumberCount;
	
}


