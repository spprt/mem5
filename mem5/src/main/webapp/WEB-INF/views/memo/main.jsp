<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<style type="text/css">
.card:hover {
	box-shadow: 5px 5px 5px #dae0e5;
	transition: all 0.3s ease-out;
	cursor: pointer;
}
</style>
<body>
<!-- Page Content -->
  <div class="container h-100">
    <!-- Page Features -->
    <div class="row h-100">
    <c:catch>
		<c:choose>
			<c:when test="${empty authInfo}">
				<!-- css처리 -->
				<div>로그인해 주세요. </div>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${fn:length(list) > 0}">
						<c:forEach items="${list}" var="item">
							<div class="col-lg-3 col-md-6 mb-2 mt-4">
								<div class="card" onclick="viewMemo(${item.id});"><!-- h-100 -->
									<div class="card-body">
										<h4 class="card-title" title="${item.title}">${item.id}. ${item.title}</h4>
										<p class="card-text" title="${item.content}">
											<c:choose><c:when test="${empty item.content}"><span style="color:#adb5bd;">내용 없음</span></c:when><c:otherwise>${item.content}</c:otherwise></c:choose>
										</p>
										<!-- 태그처리 -->
										<p class="text-primary" style="font-size: 12px;">
											<c:forEach items="${item.tags}" var="tag">
											#${tag.tag}&nbsp;
											</c:forEach>
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
				    <div class="col-sm-12 my-auto text-center">
						<i class="fas fa-edit fa-5x"></i>
						<h5 class="pt-4">작성한 메모가 없습니다.</h5>
				    </div>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</c:catch>
    </div>
    <!-- /.row -->
  </div>
  <!-- /.container -->
</body>
</html>