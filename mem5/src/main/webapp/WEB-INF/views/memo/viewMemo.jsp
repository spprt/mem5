<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<body>
<main role="main" class="container">
<div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-sm" style="background: #005dc1;">
    <div class="lh-100">
      <h6 class="mb-0 text-white lh-100">${memo.title}</h6>
      <small>${memo.regDate }</small>
    </div>
  </div>
	<div class="my-3 p-3 bg-white rounded shadow-sm">
		<h6 class="border-bottom border-gray pb-2 mb-0" > ${memo.content}</h6>
		<div class="media text-muted pt-3">
		  <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
		  	#태그 #태그자리
		  </p>
		</div>
		<%-- 
		<div class="media text-muted pt-3">
		  <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#6f42c1"></rect><text x="50%" y="50%" fill="#6f42c1" dy=".3em">32x32</text></svg>
		  <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
		    <strong class="d-block text-gray-dark">@username</strong>
		    Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
		  </p>
		</div>
		--%>
		<small class="d-block text-right mt-3">
		  <a href="#">수정</a>
		</small>
	</div>
</main>
</body>
</html>