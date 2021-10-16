package com.ek.honeypoint.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.ek.honeypoint.daos.BoardDao;
import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.Comment;
import com.ek.honeypoint.models.SearchOption;

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
  public ArrayList<Comment> selectComments(int bNo) {
    return boardDao.selectComments(bNo);
  }

  @Override
  public ArrayList<Board> selectList(Integer boardType) {
    return boardDao.selectList(boardType);
  }

  @Override
	public ArrayList<Board> searchList(HashMap<String, Object> search) {
		return boardDao.searchList(search);
	}

  @Override
  public int insertComment(Comment comment) {
    return boardDao.insertComment(comment);
  }

  @Override
  public int updateComment(Comment comment) {
    return boardDao.updateComment(comment);
  }

  @Override
  public Comment selectComment(int commentNo) {
    return boardDao.selectComment(commentNo);
  }

  @Override
  public int deleteComment(int commentId) {
    return boardDao.deleteComment(commentId);
  }

  @Override
  public int deleteBoard(int boardId) {
    return boardDao.deleteBoard(boardId);
  }

}
