package org.covid19.ourapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.covid19.ourapp.VO.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	// 게시물 목록 조회
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVO> selectAllArticlesList() throws DataAccessException {
		List<BoardVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}

	// 게시물추가
	public int insertArticle(Map articleMap) throws DataAccessException {

		int bno = selectNewbno();
		articleMap.put("bno", bno);
		sqlSession.insert("mapper.board.insertArticle", articleMap);
		return bno;
	}
	
	
	
	
	//게시물 조회
	public BoardVO readArticle(int bno) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.readArticle", bno);
	}
	
	//게시물 목록 조회
	private int selectNewbno() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewbno");
	}
	
	//게시물 추가
	public int create(BoardVO vo) throws DataAccessException {
		return sqlSession.insert("mapper.board.insertArticle", vo);

	}
	
	//게시물 수정
	public void update(BoardVO vo) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", vo);
	}
	
	//게시물 삭제
	public void delete(int bno) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", bno);
	}
}
