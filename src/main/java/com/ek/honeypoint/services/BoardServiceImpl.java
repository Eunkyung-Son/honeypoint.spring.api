package com.ek.honeypoint.services;

import java.util.ArrayList;

import com.ek.honeypoint.daos.BoardDao;
import com.ek.honeypoint.models.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
  @Autowired
  BoardDao boardDao;

  @Override
	public ArrayList<Board> selectList() {
		return boardDao.selectList();
	}

  @Override
  public ArrayList<Board> selectList(Integer boardType) {
    return boardDao.selectList(boardType);
  }

}
