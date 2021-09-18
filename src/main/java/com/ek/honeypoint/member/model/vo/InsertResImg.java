package com.ek.honeypoint.member.model.vo;

import java.util.ArrayList;

public class InsertResImg {
	private ArrayList<String> originFileList;
	private ArrayList<String> renameFileList;
	private int rNo;
	
	public InsertResImg() {}

	public InsertResImg(ArrayList<String> originFileList, ArrayList<String> renameFileList, int rNo) {
		super();
		this.originFileList = originFileList;
		this.renameFileList = renameFileList;
		this.rNo = rNo;
	}

	public ArrayList<String> getOriginFileList() {
		return originFileList;
	}

	public void setOriginFileList(ArrayList<String> originFileList) {
		this.originFileList = originFileList;
	}

	public ArrayList<String> getRenameFileList() {
		return renameFileList;
	}

	public void setRenameFileList(ArrayList<String> renameFileList) {
		this.renameFileList = renameFileList;
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	@Override
	public String toString() {
		return "InsertResImg [originFileList=" + originFileList + ", renameFileList=" + renameFileList + ", rNo=" + rNo
				+ "]";
	}
	
	
}
