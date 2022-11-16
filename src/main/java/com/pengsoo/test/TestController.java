package com.pengsoo.test;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pengsoo.test.dao.IDao;

@Controller
public class TestController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/")
	public String goList() {
		return "redirect:join";
	}

	@RequestMapping(value = "join")//join 화면만 보여준다.
	public String join() {
		
		return "join";
	}
	
	@RequestMapping(value = "joinMember")//파라미터보내준다.
	public String joinMember(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);//IDao로 반환
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		dao.memberJoin(mid, mpw, mname, memail);//반환하는 데이터타입이 없다.db에 회원정보 삽입
		
		model.addAttribute("memberID", mid);//memberID임의로 정해도 된다
		
		return "joinOk";
	}
	
	
}
