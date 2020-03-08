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
		<form role="form" method="post" action="${pageContext.request.contextPath }/category/saveAdd">
			<label for="ctgrName">분류명</label><input type="text" id="ctgrName" name="ctgrName" />
			<button type="submit">추가</button>
		</form>
	</div>
</body>
<%@ include file="/WEB-INF/views/includes/09_footer.jsp"%>
</html>