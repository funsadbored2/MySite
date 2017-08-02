<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

	<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items = "${boardList }" var ="vo">
						<tr>
							<td>${vo.no}</td>
							<td><a href="/mysite/board?a=read&no=${vo.no}">${vo.title }</a></td>
							<td>${vo.name }</td>
							<td>${vo.hit_number }</td>
							<td>${vo.date }</td>
							<c:choose>
			 					<c:when test = "${authUser == null}">
			 						<td>로그인 필요</td>
			 					</c:when>
			 					<c:otherwise>
			 					<c:if test ="${authUser.no == vo.user_no }">
									<td><a href="/mysite/board?a=delete&no=${vo.no }" class="del">삭제</a></td>
								</c:if>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				   
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li><a href="">5</a></li>
						<li><a href="">6</a></li>
						<li><a href="">7</a></li>
						<li><a href="">8</a></li>
						<li><a href="">9</a></li>
						<li><a href="">10</a></li>
						<li><a href="">▶</a></li>
					</ul>
				</div>		
						
				<div class="bottom">
				<c:if test = "${authUser != null}">
					<a href="/mysite/board?a=insertform" id="new-book">글쓰기</a>
				</c:if>
				</div>				
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div>
</body>
</html>