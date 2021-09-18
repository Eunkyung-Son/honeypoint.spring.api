package com.ek.honeypoint.daos;

import java.util.ArrayList;

import com.ek.honeypoint.models.Photofile;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("fileDao")
public class FileDao {
  @Autowired
	private SqlSessionTemplate sqlSession;

  public Photofile getImageFromRestaruant(String fileId) {
    return sqlSession.selectOne("fileMapper.getImageFromRestaurant", fileId);
  }

}
