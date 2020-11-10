package org.covid19.ourapp.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.covid19.ourapp.VO.BoardVO;
import org.covid19.ourapp.VO.MemberVO;
import org.covid19.ourapp.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardController {

	Logger log = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private BoardService boardService;
	private BoardVO boardVO;

	// 게시판 목록 홈페이지
	@RequestMapping(value = "/tables")
	public String tables(Model model, HttpSession session) {
		log.info("게시판");
		if (HomeController.loginChecker(session)) {
			List<BoardVO> articlesList = boardService.listAllArticles();
			model.addAttribute("articlesList", articlesList);
			return "tables";
		}

		return "login";

	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(HttpSession session) {
		if (HomeController.loginChecker(session)) {

			return "write";
		}
		return "login";
	}

	// 게시판 추가된 테이블
	@PostMapping("insert")
	public String insert(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws Exception {
		log.info("게시판(추가)");
		BoardVO vo = new BoardVO();
		MemberVO logged_member = (MemberVO) session.getAttribute("member");

		if (HomeController.loginChecker(session)) {
			// 로그인 상태일때만 vo객체 만들어서 insert문 실행

			vo.setTitle(request.getParameter("Title"));
			// System.out.println(request.getParameter("Title"));
			vo.setArea(request.getParameter("Area"));
			// System.out.println(request.getParameter("Area"));
			vo.setContent(request.getParameter("Content"));
			// System.out.println(request.getParameter("Content"));
			vo.setNickName(logged_member.getNickName());

			// System.out.println(vo);
			boardService.create(vo);

		}

		return tables(model, session);
	}

	// 게시물 조회
	@GetMapping("article")
	public String readArticle(BoardVO boardVO, Model model, HttpSession session) throws Exception {
		log.info("게시물 조회");
		if (HomeController.loginChecker(session)) {
			BoardVO boardVO2 = boardService.readArticle(boardVO.getBno());
			System.out.println(boardVO2);
			if (boardVO2 != null) {
				//게시글 내용이 들어오면 readArticle이라는 이름으로 붙여서 article로 이동
				model.addAttribute("readArticle", boardVO2);
				return "article";

			}
			//게시글 내용이 없으면 error페이지 이동
			return "error";

		}
		//로그인이 안되어있으면 로그인으로
		return "login";
	}

	// 게시판 수정된 테이블
	@PostMapping("updateView")
	public String updateView(BoardVO boardVO, Model model, HttpSession session) throws Exception {
		log.info("수정후 조회");
		if (HomeController.loginChecker(session)) {
			model.addAttribute("updateView", boardService.readArticle(boardVO.getBno()));
			return "updateView";
		}

		return "login";
	}

	// 게시판 수정
	@PostMapping("update")
	public String update(BoardVO boardVO, Model model, HttpSession session) throws Exception {
		log.info("게시글 수정");
		// request.getParameter("")
		if (HomeController.loginChecker(session)) {

			boardService.update(boardVO);
		}

		return readArticle(boardVO, model, session);
	}

	// 게시판 삭제
	@PostMapping("delete")
	public String delete(BoardVO boardVO, Model model, HttpSession session) throws Exception {
		log.info("게시글 삭제");
		if (HomeController.loginChecker(session)) {
			boardService.delete(boardVO.getBno());
		}

		return tables(model, session);
	}
}
