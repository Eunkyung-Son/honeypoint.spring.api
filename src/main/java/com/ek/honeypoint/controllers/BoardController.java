package com.ek.honeypoint.controllers;

import java.util.ArrayList;

import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.HPResponse;
import com.ek.honeypoint.services.BoardService;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
  @Autowired
  private BoardService boardService;

  @GetMapping(value="api/boards")
  @ResponseBody
	public HPResponse boardList (
		@RequestParam(value = "boardType", defaultValue = "1", required = false) int boardType
  ) {
    HPResponse response = new HPResponse();
    ArrayList<Board> boardList = null;

    if (boardType == 1) {
      boardList = boardService.selectList(boardType);
    } else {
      boardList = boardService.selectList();
    }
    if (boardList != null) {
      response.put("boards", boardList);

      int total = boardList.size();
      response.put("total", total);
    } else {
      // throw new boardException("게시글 목록 조회에 실패하였습니다");
    }
    return response;
	}
}
