<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<body>
<div class="col-md-12">
<c:choose>
	<c:when test="${empty authInfo}">
		 <h4 class="mb-3">로그인을 먼저 해주세요</h4>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>