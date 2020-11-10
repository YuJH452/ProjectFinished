package org.covid19.ourapp.service;

import java.sql.Date;
import java.util.List;

import org.covid19.ourapp.VO.BoardVO;
import org.covid19.ourapp.dao.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardService{
	
	@Autowired
	BoardDAO boardDAO;
	
	//게시물 목록 조회
	public List<BoardVO> listAllArticles() throws DataAccessException{
		return boardDAO.selectAllArticlesList();
	}
	
	//게시물 작성
	public void create(BoardVO vo) throws DataAccessException{
		boardDAO.create(vo);
	}
		
	//게시물 조회
	public BoardVO readArticle(int bno) throws DataAccessException {
		//System.out.println(boardDAO.readArticle(bno));
		return boardDAO.readArticle(bno);
	}
	
	//게시물 수정
	public void update(BoardVO vo) throws DataAccessException{
		
		boardDAO.update(vo);
	}
	
	//게시물 삭제
		public void delete(int bno) throws DataAccessException{
			
			boardDAO.delete(bno);
		}
}
