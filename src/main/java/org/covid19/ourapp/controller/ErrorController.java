package org.covid19.ourapp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.covid19.ourapp.VO.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * method = RequestMethod.GET
	 */
	@RequestMapping(value = "/401")
	public String error401(Locale locale, Model model, HttpSession session) {
		logger.info("401error");
		
		
		return "401";
	}
	
	@RequestMapping(value = "/404")
	public String error404(Locale locale, Model model, HttpSession session) {
		logger.info("404error");
		
		
		return "404";
	}
	
	@RequestMapping(value = "/405")
	public String error405(Locale locale, Model model, HttpSession session) {
		logger.info("404error");
		
		
		return "405";
	}
	
	@RequestMapping(value = "/500")
	public String error500(Locale locale, Model model, HttpSession session) {
		logger.info("500error");
		
		
		
		return "500";
	}
	
}
