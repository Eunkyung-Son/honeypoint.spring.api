package com.ek.honeypoint.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Photofile {
	private int photofileId;
	private int rNo;
	private String originFileName;
	private String streFileName;
	private int imgType;
}
