<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<body>
<!-- Page Content -->
  <div class="container">
    <!-- Page Features -->
    <div class="row pt-5">
    <c:catch>
		<c:choose>
			<c:when test="${empty authInfo}">
				<!-- css처리 -->
				<div>로그인해 주세요.</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list}" var="item">
					<div class="col-lg-3 col-md-6 mb-4">
						<div class="card h-100">
							<div class="card-body">
								<h4 class="card-title">${item.title}</h4>
								<p class="card-text">${item.content}</p>
								<!-- 태그처리 -->
								<p class="text-primary" style="font-size: 12px;">#태그1 #태그2</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</c:catch>
    </div>
    <!-- /.row -->
  </div>
  <!-- /.container -->
</body>
</html>