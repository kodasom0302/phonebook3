package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	//필드
	private static final long serialVersionUID = 1L;
	
		//생성자 - 기본 생성자 사용
	
		//메소드 - gs
		
		//메소드 - 일반
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("PhonebookController.goGet()");
		
		String action=request.getParameter("action");
		System.out.println(action);
		
		if("wform".equals(action)) {
			System.out.println("wform:등록폼");
			
			//jsp한테 html 그리기	응답해라 -> 포워드
			RequestDispatcher rd=request.getRequestDispatcher("/writeForm.jsp");
			rd.forward(request, response);
		} else if("insert".equals(action)) {
			System.out.println("insert:등록");
			
			String name=request.getParameter("name");
			String hp=request.getParameter("hp");
			String company=request.getParameter("company");
			
			//Vo로 묶기
			PersonVo personVo=new PersonVo(name, hp, company);
			System.out.println(personVo.toString());
			
			//db 관련 업무
			PhoneDao phoneDao=new PhoneDao();
			
			//db에 저장
			phoneDao.personInsert(personVo);
			
			//리다이렉트 - 주소 치고 엔터 친 효과를 낸다
			response.sendRedirect("http://localhost:8080/phonebook3/pbc?action=list");
			/*
			//db에서 전체 데이터 가져오기
			List<PersonVo> personList=phoneDao.personSelect();
			
			//request에 담기
			request.setAttribute("personList", personList);
			
			//포워드
			RequestDispatcher rd=request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			*/
		} else if("list".equals(action)) {
			System.out.println("list:리스트");
			
			//db 사용
			PhoneDao phoneDao=new PhoneDao();
			
			//리스트 가져오기
			List<PersonVo> personList=phoneDao.personSelect();
			
			//데이터 담기	- 포워드
			request.setAttribute("personList", personList);
			
			RequestDispatcher rd=request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			
		} else if("delete".equals(action)) {
			System.out.println("delete:삭제");
			int no=Integer.parseInt(request.getParameter("no"));
			System.out.println(no);
			
			//db 사용
			PhoneDao phoneDao=new PhoneDao();
			
			//삭제
			phoneDao.personDelete(no);
			
			//리다이렉트
			response.sendRedirect("/phonebook3/pbc?action=list");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
