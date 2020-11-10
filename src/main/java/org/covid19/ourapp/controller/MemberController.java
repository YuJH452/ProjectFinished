package org.covid19.ourapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.covid19.ourapp.VO.MemberVO;
import org.covid19.ourapp.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.CookieGenerator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String String = null;
	/**
	 * 주요 구현 목표
	 * 
	 * 1. 회원가입 후 성공메시지 출력한 뒤 로그인 창으로 이동 2. 로그인 성공 시 세션에 지역명과 userid 붙여준 후 return
	 * "index"; / 로그인 실패시 에러메시지 띄우고 로그인 창으로 돌려보내기 3. 로그아웃 시 세션에서 지역명과 userid 값 삭제 /
	 * 아니면 세션을 통째로 invalidate()
	 */
	@Autowired
	MemberService memberService;// 서비스 호출하기 위해 의존성 주입
	
	@Inject // 서비스를 호출하기 위해서 의존성 주입
	JavaMailSender mailSender; // 메일 서비스를 사용하기 위해 의존성을 주입함
	// 암호화
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/crystal")
	public String crystal(Locale locale, Model model, HttpSession session) {
		logger.info("회원 수정");
		if (HomeController.loginChecker(session)) {
			MemberVO logged_mem = (MemberVO) session.getAttribute("member");
			String e_mail = logged_mem.getEmail();
			
			model.addAttribute("is_recovery", true);
			model.addAttribute("button", "수정");
			model.addAttribute("e_mail",e_mail);
			model.addAttribute("action", "updateMember.do");
			return "register"; // 로그인 안되어있으면 로그인하라고 보내기
		}
		
		return "login";
	}

	@RequestMapping(value = { "/email", "/password" })
	public String email_injeung(Locale locale, Model model, HttpSession session, HttpServletRequest req) {
		logger.info("회원가입");

		StringBuffer url = req.getRequestURL();
		String url_string = url.toString();
		System.out.println("들어온 경로명" + url_string.split("/")[4]);
		String curUrl = url_string.split("/")[4];

		if (curUrl.equals("email")) {
			if (HomeController.loginChecker(session)) {
				return "index"; // 로그인 되어있으면 돌려보내기
			}

			model.addAttribute("action", "auth.do");
			model.addAttribute("cardHeader", "회원가입");
		} else {
			model.addAttribute("action", "reset");
			model.addAttribute("cardHeader", "비밀번호 찾기");
		}

		model.addAttribute("sub1", "Email");
		model.addAttribute("placeholder", "이메일을 입력해 주세요..");
		model.addAttribute("btn", "전송");

		return "password";
	}

	// mailSending 코드
	@RequestMapping(value = { "auth.do", "reset" }, method = RequestMethod.POST)
	public ModelAndView mailSending(HttpServletRequest request, String e_mail, HttpServletResponse response_email)
			throws IOException {

		Random r = new Random();
		StringBuffer url = request.getRequestURL();
		String url_string = url.toString();
		//System.out.println(url_string);
		//System.out.println(url_string.split("/")[4]);
		String curUrl = url_string.split("/")[4];
		response_email.setContentType("text/html; charset=UTF-8");
		PrintWriter out_email = response_email.getWriter();
		
		ModelAndView mv = new ModelAndView();
		// ModelAndView로 보낼 페이지를 지정하고, 보낼 값을 지정한다.

		
		MemberVO m = new MemberVO();
		m.setEmail(e_mail);
		
		
		if (curUrl.equals("auth.do")) { // 이메일로 SELECT해서 뭐가 나오면 if
			if (memberService.selectResult(m) == 1) {
				mv.setViewName("email");
				//System.out.println("auth.do 들어왔다 " + memberService.selectResult(m));
				out_email.println("<script>alert('이미 가입된 이메일입니다.');" + "history.go(-1);</script>");
				out_email.flush();
				out_email.close();
				return mv;
				
			}

		} else {
			if (memberService.selectResult(m) == 0) {
				mv.setViewName("password");
				//System.out.println("reset 들어왔다 " + memberService.selectResult(m));
				out_email.println("<script>alert('존재하지 않는 이메일 입니다.');" + "history.go(-1);</script>");
				out_email.flush();
				out_email.close();
				return mv;
			}

		}

		int dice = r.nextInt(4589362) + 49311; // 이메일로 받는 인증코드 부분 (난수)

		String setfrom = "COVID12444@gmail.com";
		String tomail = e_mail; // 받는 사람이메일
		String title = "회원가입 인증 이메일 입니다."; // 제목

		String content =
				// 한줄씩 줄간격 두기위해 작성
				System.getProperty("line.separator") + System.getProperty("line.separator") + "안녕하세여 COVID임니다."
						+ System.getProperty("line.separator") + System.getProperty("line.separator") + "인증번호는" + dice
						+ "임니다." + System.getProperty("line.separator") + System.getProperty("line.separator")
						+ "받으신 인증번호를 홈페이지 입력해주세요"; // 내용

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		mv.setViewName("password");

		if (curUrl.equals("auth.do")) {
			mv.addObject("action2", "injeung.do");
			mv.addObject("cardHeader", "회원가입");

		} else {
			mv.addObject("action2", "crystal2");
			mv.addObject("cardHeader", "비밀번호 찾기");
		}

		mv.addObject("e_mail", e_mail);
		mv.addObject("dice", dice);
		mv.addObject("sub1", "인증번호");
		mv.addObject("placeholder", "인증번호를 입력하세요..");
		mv.addObject("btn", "확인");

		//System.out.println("mv : " + mv);
		
		out_email.println("<script>alert('인증메일이 발송되었습니다.');</script>");
		out_email.flush();
		

		return mv;
	}

	// 이메일로 받은 인증번호를 입력하고 전송 버튼을 누르면 맵핑되는 메소드.
	// 내가 입력한 인증번호와 메일로 입력한 인증번호가 맞는지 확인해서 맞으면 회원가입 페이지로 넘어가고,
	// 틀리면 다시 원래 페이지로 돌아오는 메소드

	@RequestMapping(value = { "injeung.do", "crystal2" }, method = RequestMethod.POST)
	public ModelAndView join_injeung(@RequestParam String e_mail, String email_injeung, @RequestParam String dice,
			HttpServletResponse response_equals, HttpServletRequest request) throws IOException {

		System.out.println("마지막 : email_injeung : " + email_injeung);
		System.out.println("마지막 : dice : " + dice);
		System.out.println("마지막: e_mail :" + e_mail);
		StringBuffer url = request.getRequestURL();
		String url_string = url.toString();
		// System.out.println(url_string);
		MemberVO m = new MemberVO();
		m.setEmail(e_mail);
		m = memberService.selectOneMember(m);

		System.out.println("들어온 경로명" + url_string.split("/")[4]);
		String curUrl = url_string.split("/")[4];

		// 페이지이동과 자료를 동시에 하기위해 ModelAndView를 사용해서 이동할 페이지와 자료를 담음

		ModelAndView mv = new ModelAndView();
		// mv.setViewName("/register");

		
		
		// mv.addObject("e_mail",e_mail);

		if (email_injeung.equals(dice)) {
			if (curUrl.equals("crystal2")) {
				mv.addObject("action", "updateMember.do");
				mv.addObject("is_recovery", true);
				mv.addObject("button", "수정");
				mv.addObject("member",m);
				
			} else {
				mv.addObject("action", "insertMember.do");
				mv.addObject("button", "회원가입");
			}
			// 인증번호가 일치할 경우 인증번호가 맞다는 창을 출력하고 회원가입창으로 이동함

			mv.setViewName("/register");
			mv.addObject("e_mail", e_mail);

			// 만약 인증번호가 같다면 이메일을 회원가입 페이지로 같이 넘겨서 이메일을
			// 한번더 입력할 필요가 없게 한다.

			/*
			 * response_equals.setContentType("text/html; charset=UTF-8"); PrintWriter
			 * out_equals = response_equals.getWriter();
			 * out_equals.println("<script>alert('인증번호가 일치하였습니다. 회원가입창으로 이동합니다.');</script>"
			 * ); out_equals.flush();
			 */

			return mv;

		} else if (email_injeung != dice) {

			ModelAndView mv2 = new ModelAndView();

			mv2.setViewName("/email_injeung");

			response_equals.setContentType("text/html; charset=UTF-8");
			PrintWriter out_equals = response_equals.getWriter();
			out_equals.println("<script>alert('인증번호가 일치하지않습니다. 인증번호를 다시 입력해주세요.'); history.go(-1);</script>");
			out_equals.flush();

			return mv2;

		}

		return mv;

	}

	// 회원 가입
	@RequestMapping(value = "insertMember.do", method = RequestMethod.POST)
	public String insert(MemberVO memberVO, Model model, HttpServletResponse response) throws IOException {
		logger.info("회원 가입 성공");
		String password = memberVO.getPassword();
		//System.out.println("암호화 전 비밀번호 : " + password);

		String encPassword = bCryptPasswordEncoder.encode(password);

		//System.out.println("암호화 후 비밀번호 : " + encPassword);

		memberVO.setPassword(encPassword);

		int result = memberService.insertMember(memberVO);

		if (result > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out_equals = response.getWriter();
			out_equals.println("<script>alert('회원 가입 성공. 로그인하세요.');</script>");
			out_equals.flush();
		}

		model.addAttribute("member", memberVO);

		return "login";
	}

	@RequestMapping("memberDelete.do")
	public String deleteMember(HttpSession session, Model model) {

		return "login";
	}

	// 회원 수정
	@RequestMapping("updateMember.do")
	// @ResponseBody
	public void updateMember(HttpSession session, HttpServletResponse response, MemberVO memberVO, Model model)
			throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out_equals = response.getWriter();

		String password = memberVO.getPassword();
		//System.out.println("암호화 전 비밀번호 : " + password);
		String encPassword = bCryptPasswordEncoder.encode(password);
		//System.out.println("암호화 후 비밀번호 : " + encPassword);
		memberVO.setPassword(encPassword);
		int result = memberService.updateMember(memberVO);

		if (result > 0) {

			session.removeAttribute("member");
			MemberVO m = memberService.selectOneMember(memberVO);
			session.setAttribute("member", m);
			out_equals.println("<script>alert('회원 수정 완료.'); location.href='index'</script>");
			out_equals.flush();
			out_equals.close();
		} else {
			out_equals.println("<script>alert('오류가 발생했습니다.'); history.go(-1);</script>");
			out_equals.flush();
			out_equals.close();
		}

	}

	@RequestMapping("memberLogin.do")
	public ModelAndView memberLogin(MemberVO memberVO, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response,
			Model model) throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");

		try {
			MemberVO m = memberService.selectOneMember(memberVO);
			System.out.println(m);

			if (m != null && bCryptPasswordEncoder.matches(memberVO.getPassword(), m.getPassword())) {

				httpSession.setAttribute("member", m);
				mv.addObject("member", m);
				response.setContentType("text/html; charset=UTF-8");
				//remember me 체크값 받아오기
				String remember_me = request.getParameter("remember_me");
				
				//remember_me 체크값이 들어오면 쿠키 생성
				if(remember_me!=null && remember_me.equals("yes")) {
				CookieGenerator cg = new CookieGenerator();
				cg.setCookieMaxAge(3600000);
				//email값 쿠키 등록
				cg.setCookieName("remember_email");
				cg.addCookie(response, memberVO.getEmail());
				//pw값 쿠키 등록
				cg.setCookieName("remember_pw");
				cg.addCookie(response, memberVO.getPassword());
				
				}
				
				
				
				//세션으로 회원 정보 등록
				httpSession.setAttribute("member", m);
				// mv.addObject("member", m);
				mv.setViewName("index");

			} else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out_equals = response.getWriter();
				out_equals.println("<script>alert(' 로그인 실패. 다시 입력하세요.');</script>");
				out_equals.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	@RequestMapping("memberLogout.do")
	public String memberLogout(HttpSession session) {
		logger.info("로그 아웃");
		session.invalidate();

		return "redirect:/";
	}

	
	
}
