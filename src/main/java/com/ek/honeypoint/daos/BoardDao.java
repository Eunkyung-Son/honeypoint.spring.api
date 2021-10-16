package com.ek.honeypoint.daos;

import java.util.ArrayList;
import java.util.HashMap;

import com.ek.honeypoint.models.Board;
import com.ek.honeypoint.models.Comment;
import com.ek.honeypoint.models.SearchOption;

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

  public ArrayList<Board> searchList(HashMap<String, Object> search) {
		return (ArrayList)sqlSession.selectList("boardMapper.searchList", search);
	}

  public ArrayList<Comment> selectComments(int bNo) {
    return (ArrayList)sqlSession.selectList("boardMapper.selectComments", bNo);
  }

	public int insertComment(Comment comment) {
		return sqlSession.insert("boardMapper.insertComment", comment);
	}

	public int updateComment(Comment comment) {
		return sqlSession.update("boardMapper.updateComment", comment);
	}

	public Comment selectComment(int commentNo) {
		return sqlSession.selectOne("boardMapper.selectComment", commentNo);
	}

	public int deleteComment(int commentId) {
		return sqlSession.delete("boardMapper.deleteComment", commentId);
	}

  public int deleteBoard(int boardId) {
    return sqlSession.delete("boardMapper.deleteBoard", boardId);
  }

}
