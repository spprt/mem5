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
      <small>
      	<fmt:formatDate value="${memo.regDate }" pattern="yyyy-MM-dd HH:mm:ss "/> 
      </small>
      <c:if test="${not empty ctgrName}">
      	<p><small>${ctgrName}</small></p>
      </c:if>
    </div>
  </div>
	<div class="my-3 p-3 bg-white rounded shadow-sm">
		<h6 class="border-bottom border-gray pb-2 mb-0" > ${memo.content}</h6>
		<c:if test="${not empty memo.tags }">
		<div class="media text-muted pt-3">
		  <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
		  	<c:forEach var="tag" items="${memo.tags}">
				#<c:out value="${tag.tag}"/>
			</c:forEach>
		  </p>
		</div>
		</c:if>
		<small class="d-block text-right mt-3">
	      <c:if test="${memo.del == true}">
	      	<a href="${pageContext.request.contextPath }/memo/restore?id=${memo.id}">복원</a>
	      	<a> | </a>
	      	<a href="${pageContext.request.contextPath }/memo/remove?id=${memo.id}">폐기</a>
	      </c:if>
	      
	      <c:if test="${memo.del == false}">
      		<c:if test="${favorite == false}">
      		<a href="${pageContext.request.contextPath }/memo/favorite?id=${memo.id}&check=true">즐겨찾기</a>
      		</c:if>
      		<c:if test="${favorite == true}">
      		<a href="${pageContext.request.contextPath }/memo/favorite?id=${memo.id}&check=false">즐겨찾기해제</a>
      		</c:if>
	      	<a> | </a>
	      	<a href="#" onclick="editMemo(${memo.id});">수정</a>
	      	<a> | </a>
	      	<a href="${pageContext.request.contextPath }/memo/del?id=${memo.id}">삭제</a>
	      </c:if>
		</small>
	</div>
</main>
</body>
</html>