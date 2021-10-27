package com.ek.honeypoint.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.Comment;

public interface BoardService {
  ArrayList<Board> selectList();
  ArrayList<Board> selectList(int boardType);
  ArrayList<Board> selectMyBoard(int mNo);
  Board selectBoard(int bNo);
  int insertBoard(Board board);
  int updateBoard(Board board);
  int deleteBoard(int boardId);
  ArrayList<Board> searchList(HashMap<String, Object> search);
  ArrayList<Comment> selectComments(int bNo);
  ArrayList<Comment> selectMyComment(int mNo);
  Comment selectComment(int commentNo);
  int insertComment(Comment comment);
  int updateComment(Comment comment);
  int deleteComment(int commentId);

}
