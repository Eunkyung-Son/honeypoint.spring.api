package com.ek.honeypoint.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class Menu {
	@JsonProperty("menuNo")
	private int menuNo;
	@JsonProperty("rNo")
	private int rNo;
	@JsonProperty("menuName")
	private String menuName;
	@JsonProperty("menuPrice")
	private int menuPrice;
	
	public Menu() {}

	public Menu(int menuNo, int rNo, String menuName, int menuPrice) {
		super();
		this.menuNo = menuNo;
		this.rNo = rNo;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}
	
	

	public Menu(int rNo, String menuName, int menuPrice) {
		super();
		this.rNo = rNo;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	@Override
	public String toString() {
		return "Menu [menuNo=" + menuNo + ", rNo=" + rNo + ", menuName=" + menuName + ", menuPrice=" + menuPrice + "]";
	}
	
	
}
