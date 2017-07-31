<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
int no =  Integer.parseInt(request.getParameter("no"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
<title>Insert title here</title>
</head>
<body>


	<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

	<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>


	<div id="wrapper">
		<div id="content">
			<div id="guestbook" class="delete-form">

				<form method="post" action="/mysite/guestbook">
				<input type = "hidden" name = "a" value = "delete">
				<input type = "hidden" name = "no" value = "<%=no%>">

					<label>비밀번호</label> <input type="password" name="password">
					<input type="submit" value="확인">
				</form>
				<a href="/mysite/guestbook">방명록 리스트</a>

			</div>
		</div>
		<!-- /content -->
	</div>
	<!-- /wrapper -->

	<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>

	<!-- /container -->

</body>
</html>