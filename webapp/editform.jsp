<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@	page import="java.util.List" %>
<%@	page import="com.javaex.vo.PersonVo" %>
<%
 	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("personList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호부</h1>
	
	<h2>수정폼</h2>
	
	<%
    // 전달받은 personId 파라미터 가져오기
    String personId = request.getParameter("no");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String company = request.getParameter("company");

    // 가져온 personId를 확인하기 위해 출력해보기
    out.println("전달받은 personId: " + personId);
    out.println(name);
    out.println(hp);
    out.println(company);
    
    int id = Integer.parseInt(personId);
    
	%>
	
	<form action="http://localhost:8080//phonebook3/pbc" method="">	
		<div>
		<label>아이디(personId)</label>
		<input type="number" name="no" value= "<%=id %>" readonly>
		</div>
		
		<div>
		<label>이름(name)</label>
		<input type="text" name="name" value= "<%=name %>" >
		</div>
		
		<div>
		<label>핸드폰(hp)</label>
		<input type="text" name="hp" value="<%=hp %>">
		</div>
		
		<div>
		<label>회사(company)</label>
		<input type="text" name="company" value="<%=company %>">
		</div>
		
		 <input type="text" name="action" value="edit">
		<button type="submit">수정</button>
	</form> 
	
	<br><br>
	<a href="">리스트페이지로 이동</a>

</body>
</html>