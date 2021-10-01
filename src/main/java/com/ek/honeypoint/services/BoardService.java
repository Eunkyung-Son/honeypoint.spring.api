package com.ek.honeypoint.services;

import java.util.ArrayList;

import com.ek.honeypoint.models.Board;

public interface BoardService {
  ArrayList<Board> selectList();
  ArrayList<Board> selectList(Integer boardType);
}
