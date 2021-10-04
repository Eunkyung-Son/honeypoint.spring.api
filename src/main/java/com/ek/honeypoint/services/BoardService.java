package com.ek.honeypoint.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.SearchOption;

public interface BoardService {
  ArrayList<Board> selectList();
  ArrayList<Board> selectList(Integer boardType);
  ArrayList<Board> searchList(HashMap<String, Object> search);

}
