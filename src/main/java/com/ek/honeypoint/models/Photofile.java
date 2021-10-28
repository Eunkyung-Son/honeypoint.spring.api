package com.ek.honeypoint.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Photofile {
	@JsonProperty("photofileId")
	private int photofileId;
	@JsonProperty("rNo")
	private int rNo;
	@JsonProperty("originFileName")
	private String originFileName;
	@JsonProperty("streFileName")
	private String streFileName;
	@JsonProperty("imgType")
	private int imgType;
}
