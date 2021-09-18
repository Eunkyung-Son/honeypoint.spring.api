package com.ek.honeypoint.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
// public class HPResponse implements Serializable {
//   private HashMap<String, Object> data;
//   // private int total;

//   // // TODO: 이미지는 rest api형태로 하려면 전체 다시 작업들어가야함
//   // // 이미지
//   // private ArrayList<Photofile> images;
  
//   // // 레스토랑
//   // private Restaurant restaurant;
//   // private ArrayList<Restaurant> restaurants;
//   // private ArrayList<RstrntMenu> menus;
//   // private ArrayList<Reservation> reservations;
//   // private int favorCount;

//   // //리뷰
//   // private ArrayList<Review> reviews;
// }

public class HPResponse extends HashMap<String, Object> {

  private static final long serialVersionUID = 1L;

}
