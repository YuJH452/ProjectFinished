package org.covid19.ourapp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.covid19.ourapp.VO.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 큼지막한 페이지 이동
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name. method =
	 * RequestMethod.GET
	 */
	@RequestMapping(value = { "/", "/login" })
	public String home(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);

		if (loginChecker(session)) {
			return "index"; // 이미 로그인 되어있으면 로그인 페이지로 못들어게 막기
		}

		// 사용자의 쿠키 쭉 받아오기
		Cookie[] remember_me = request.getCookies();

		if (remember_me != null) {
			for (int i = 0; i < remember_me.length; i++) {
				// System.out.println(i + "번째 쿠키 이름: " + remember_me[i].getName());
				// System.out.println(i + "번째 쿠키 값: " + remember_me[i].getValue());

				// 받아온 쿠키 배열에서 remember_email이라는 이름을 가진 쿠키의 값 추출
				if (remember_me[i].getName().equals("remember_email")) {
					model.addAttribute("remember_email", remember_me[i].getValue());
				}
				// 받아온 쿠키 배열에서 remember_pw라는 이름을 가진 쿠키의 값 추출
				if (remember_me[i].getName().equals("remember_pw")) {
					model.addAttribute("remember_pw", remember_me[i].getValue());
				}

			}
		}

		return "login";
	}

	@RequestMapping(value = "/index")
	public String index(Locale locale, Model model, HttpSession session) {
		logger.info("로그인성공");

		if (loginChecker(session)) {
			return "index"; // 로그인 안되어있으면 로그인하라고 보내기
		}
		;

		return "login";
	}

	@RequestMapping(value = "/charts")
	public String charts(Locale locale, Model model, HttpSession session) {
		logger.info("코로나 상세");
		if (loginChecker(session)) {
			return "charts"; // 로그인 되어있으면 돌려보내기
		}

		return "login";
	}

	public static boolean loginChecker(HttpSession session) {

		MemberVO memberVO = (MemberVO) session.getAttribute("member");

		try {
			logger.info("로그인 사용자정보: " + memberVO.toString());
			return true;
		} catch (NullPointerException e) {
			return false;
		}

	}
}
