package com.ek.honeypoint.daos;

import java.util.ArrayList;

import com.ek.honeypoint.models.Favor;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("favorDao")
public class FavorDao {
  @Autowired
  private SqlSessionTemplate sqlSession;

  public int favor(Favor favor) {
    return sqlSession.insert("favorMapper.favor", favor);
  }

  public int unFavor(Favor favor) {
    return sqlSession.delete("favorMapper.unFavor", favor);
  }

  public ArrayList<Favor> getMemberFavorList(int memberNo) {
    return (ArrayList)sqlSession.selectList("favorMapper.getMemberFavorList", memberNo);
  }

  public Favor checkFavor(Favor favor) {
    return sqlSession.selectOne("favorMapper.checkFavor", favor);
  }

  public int getFavorCount(int restaurantNo) {
    return sqlSession.selectOne("favorMapper.getFavorCount", restaurantNo);
  }
}