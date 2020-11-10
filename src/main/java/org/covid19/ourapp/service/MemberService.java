package org.covid19.ourapp.service;


import org.covid19.ourapp.VO.MemberVO;
import org.covid19.ourapp.dao.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDAO ; // dao 사용하기위해 의존성 주입
	
	public int insertMember(MemberVO memberVO) {
		
		return memberDAO.insertMember(memberVO);
	}

	public int updateMember(MemberVO memberVO) {
		
		return memberDAO.updateMember(memberVO);
	}

	public MemberVO selectOneMember(MemberVO memberVO) {
		
		return memberDAO.selectOneMember(memberVO);
	}
	
	public int selectResult(MemberVO memberVO) {
		return memberDAO.selectResult(memberVO);
	}
	
	public int nickNameCheck(String nickName) {
	      
	      return memberDAO.nickCheck(nickName);
	   }
}
