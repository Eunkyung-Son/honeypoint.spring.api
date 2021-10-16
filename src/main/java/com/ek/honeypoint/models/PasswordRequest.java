package com.ek.honeypoint.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data
public class PasswordRequest {
	// @JsonProperty("oldPassword")
	private String oldPassword;
	// @JsonProperty("newPassword")
	private String newPassword;
	@JsonProperty("mId")
	private String mId;
}
