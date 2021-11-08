package com.ek.honeypoint.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu {
	@JsonProperty("menuNo")
	private int menuNo;
	@JsonProperty("rNo")
	private int rNo;
	@JsonProperty("menuName")
	private String menuName;
	@JsonProperty("menuPrice")
	private int menuPrice;
}
