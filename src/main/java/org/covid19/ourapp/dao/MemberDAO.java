package org.covid19.ourapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.covid19.ourapp.VO.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	public int insertMember(MemberVO memberVO) {

		System.out.println("memberVO:" + memberVO);
		return sqlSession.insert("member.insertMember", memberVO);
	}

	public int updateMember(MemberVO memberVO) {
		
		//닉네임 기준 이미 존재하는게 있으면 0 반환
		if (sqlSession.selectOne("member.selectOtherMem", memberVO) != null) {
			return 0;
		} else {
			return sqlSession.update("member.updateMember", memberVO);
			
		}
	}

	public MemberVO selectOneMember(MemberVO memberVO) {

		return sqlSession.selectOne("member.selectOneMember", memberVO);
	}
	
	public int selectResult(MemberVO memberVO) {
		if(sqlSession.selectOne("member.selectOneMember",memberVO)!=null) return 1;
		else return 0;
		
	}

	public int nickCheck(String nickName) {
	      
	      return sqlSession.selectOne("member.nickCheck",nickName);
	   }

	
}
