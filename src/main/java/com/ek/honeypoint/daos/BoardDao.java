package com.ek.honeypoint.daos;

import java.util.ArrayList;

import com.ek.honeypoint.models.Board;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDao {
  @Autowired
  private SqlSessionTemplate sqlSession;

  public ArrayList<Board> selectList() {
		return (ArrayList)sqlSession.selectList("boardMapper.selectAll");
	}

  public ArrayList<Board> selectList(Integer boardType) {
		return (ArrayList)sqlSession.selectList("boardMapper.selectAllWithType", boardType);
	}

}
