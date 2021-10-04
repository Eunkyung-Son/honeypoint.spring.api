package com.ek.honeypoint.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.HPResponse;
import com.ek.honeypoint.models.SearchOption;
import com.ek.honeypoint.services.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    if (boardType != 0) {
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

  	// 검색 기능
	@GetMapping(value="api/searchBoards/{boardType}")
	@ResponseBody
	public HPResponse searchBoard (
    @PathVariable(value = "boardType") int boardType,
    @RequestParam(value = "searchOption", defaultValue = "", required = false) String searchOptions
  ) throws JsonMappingException, JsonProcessingException {

    ObjectMapper objectMapper = new ObjectMapper();
    SearchOption options = new SearchOption();
    options = objectMapper.readValue(searchOptions, SearchOption.class);
    HPResponse response = new HPResponse();
    ArrayList<Board> boardList = null;

    HashMap<String, Object> search = new HashMap<String, Object>();
    search.put("boardType", boardType);
    search.put("condition", options.getCondition());
    search.put("value", options.getValue());


    boardList = boardService.searchList(search);
    System.out.println(search);
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
