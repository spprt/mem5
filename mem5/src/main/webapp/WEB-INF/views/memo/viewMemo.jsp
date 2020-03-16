<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<body>
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">View Memo</h1>
  <i></i>
</div>
<div class="container">
view Memo
${memo.title}
<c:out value="${memo.type == 2 ? 'todo' : 'memo'}"/>
</div>
</body>
</html>