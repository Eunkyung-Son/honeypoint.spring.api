package com.ek.honeypoint.services;

import java.util.ArrayList;

import com.ek.honeypoint.daos.FavorDao;
import com.ek.honeypoint.models.Favor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("favorService")
public class FavorServiceImpl implements FavorService{

  @Autowired
  FavorDao favorDao;

  @Override
  public int favor(Favor favor) {
    return favorDao.favor(favor);
  }

  @Override
  public int unFavor(Favor favor) {
    return favorDao.unFavor(favor);
  }

  @Override
  public ArrayList<Favor> getMemberFavorList(int memberNo) {
    return (ArrayList)favorDao.getMemberFavorList(memberNo);
  }

  @Override
  public Boolean checkFavor(Favor favor) {
    Favor favorResult = favorDao.checkFavor(favor);
    Boolean isFavor = false;
    if (favorResult != null)  isFavor = true;
    return isFavor;
  }

  @Override
  public int restaurantFavorCount(int restaurantNo) {
    return favorDao.getFavorCount(restaurantNo);
  }

}
