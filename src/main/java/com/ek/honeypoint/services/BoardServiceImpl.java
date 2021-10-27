package com.ek.honeypoint.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.ek.honeypoint.daos.BoardDao;
import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.Comment;

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
  public ArrayList<Board> selectList(int boardType) {
    return boardDao.selectList(boardType);
  }

  @Override
  public ArrayList<Board> selectMyBoard(int mNo) {
    return boardDao.selectMyBoard(mNo);
  }

  @Override
  public Board selectBoard(int bNo) {
    return boardDao.selectBoard(bNo);
  }

  @Override
  public ArrayList<Board> searchList(HashMap<String, Object> search) {
    return boardDao.searchList(search);
  }

  @Override
  public int insertBoard(Board board) {
    return boardDao.insertBoard(board);
  }
  
  @Override
  public int updateBoard(Board board) {
    return boardDao.updateBoard(board);
  }
  
  @Override
  public int deleteBoard(int boardId) {
    return boardDao.deleteBoard(boardId);
  }
  
  @Override
  public ArrayList<Comment> selectComments(int bNo) {
    return boardDao.selectComments(bNo);
  }

  @Override
  public ArrayList<Comment> selectMyComment(int mNo) {
    return boardDao.selectMyComment(mNo);
  }

  @Override
  public Comment selectComment(int commentNo) {
    return boardDao.selectComment(commentNo);
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
  public int deleteComment(int commentId) {
    return boardDao.deleteComment(commentId);
  }

}
