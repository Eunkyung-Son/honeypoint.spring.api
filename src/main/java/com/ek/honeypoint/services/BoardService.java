package com.ek.honeypoint.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.Comment;

public interface BoardService {
  ArrayList<Board> selectList();
  ArrayList<Board> selectList(Integer boardType);
  ArrayList<Board> searchList(HashMap<String, Object> search);
  ArrayList<Comment> selectComments(int bNo);
  int insertComment(Comment comment);
  int updateComment(Comment comment);
  Comment selectComment(int commentNo);
  int deleteComment(int commentId);
  int deleteBoard(int boardId);
  Board selectBoard(int bNo);

}
