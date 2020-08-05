<%@page import="model.Mutter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	<h1>どこつぶメイン</h1>
	<p>
		${loginUser.getName()}さん、ログイン中 <a href="/docoTsubu/Logout">ログアウト</a>
	</p>
	<p>
		<a href="/docoTsubu/Main">更新</a>
	</p>
	<form action="/docoTsubu/Main" method="post">
		<input type="text" name="text"> <input type="submit"
			value="つぶやく">
	</form>
	<%-- <% if(errorMsg != null){ %>
	<p><%= errorMsg %></p>
	<% } %>
	<% for(Mutter mutter : mutterList){ %>
	 <p><%= mutter.getUserName() %>:<%= mutter.getText() %></p>
	<%} %>
	 --%>
	<c:if test="${not empty errorMsg}">
		<p>${errorMsg}</p>
	</c:if>
	<c:forEach var="mutter" items="${mutterList}">
		<table>
			<tr>
				<td>${mutter.userName}:${mutter.text}
					<form action="/docoTsubu/Main" method="post">
						<button name="state" value="delete,${mutter.userName}">削除</button>
					</form>
				</td>
			</tr>
		</table>

	</c:forEach>

	<%
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/footer.jsp");
		dispatcher.include(request, response);
	%>
	<%--<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include> --%>
	<%--@ include file="/WEB-INF/jsp/footer.jsp" --%>
</body>
</html>