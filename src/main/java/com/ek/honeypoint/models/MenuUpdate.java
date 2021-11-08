package com.ek.honeypoint.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuUpdate {
  @JsonProperty("menus")
  private ArrayList<Menu> menus;
}
