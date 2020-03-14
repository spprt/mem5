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
	      <h4 class="mb-3">Write Note</h4>
	      <form class="needs-validation" novalidate="" method="post" action="${pageContext.request.contextPath }/memo/saveNote">
	          <div class="mb-3">
	            <label for="title">Title</label>
	            <input type="text" class="form-control" id="title" placeholder="" value="" required="">
	            <div class="invalid-feedback">
	              Valid title is required.
	            </div>
	          </div>
	
	        <div class="mb-3">
	          <label for="username">Username</label>
	          <div class="input-group">
	            <div class="input-group-prepend">
	              <span class="input-group-text">@</span>
	            </div>
	            <input type="hidden" id="regUserId" value="${authInfo.id}">
	            <input type="text" class="form-control" placeholder="Username" required="" value="${authInfo.name}" readonly>
	          </div>
	        </div>
	
	        <div class="mb-3">
	          <label for="tag">TAG <span class="text-muted">(Optional)</span></label>
	          <input type="text" class="form-control" id="tag" placeholder="쉼표(,)를 구분해서 입력해주세요">
	          <div class="invalid-feedback">
	            쉼표(,)를 구분해서 입력해주세요
	          </div>
	        </div>
	
	        <div class="mb-3">
	          <label for="content">Content</label>
	          <textarea type="text" class="form-control" id="content" required=""></textarea>
	        </div>
	        <hr class="mb-4">
	        <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
	      </form>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>