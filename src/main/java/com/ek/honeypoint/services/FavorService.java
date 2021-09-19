package com.ek.honeypoint.services;

import java.util.ArrayList;

import com.ek.honeypoint.models.Favor;

public interface FavorService {
  int favor(Favor favor);

	int unFavor(Favor favor);

  ArrayList<Favor> getMemberFavorList(int memberNo);

  Boolean checkFavor(Favor favor);

  int restaurantFavorCount(int restaurantNo);
}
