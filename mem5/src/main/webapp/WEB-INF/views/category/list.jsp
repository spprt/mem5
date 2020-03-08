<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/03_header.jsp"%>
	<div class="container">
		<a href="${pageContext.request.contextPath }/category/add">분류추가</a>
		<table width="510" border="1">
			<tr>
				<td>번호</td>
				<td>분류명</td>
				<td>삭제</td>
			</tr>
			<c:forEach items="${categoryList}" var="category" varStatus="status">
				<tr>
					<td>[${category.id}]</td>
					<td><a href="${pageContext.request.contextPath}/category/edit?id=${category.id}">${category.ctgrName}</a></td>
					<td><a href="${pageContext.request.contextPath}/category/del?id=${category.id}">Del</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
</html>