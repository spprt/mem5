<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:catch>
	<c:choose>
		<c:when test="${empty authInfo}">
			<div class="item list-group-item bg-light">로그인해 주세요.</div>
		</c:when>
		<c:otherwise>
			<div id="ctgr_All" data-id="-1" class="all item" style="position:relative;">
				<a href="#" class="list-group-item list-group-item-action bg-light selected">전체보기</a>
			</div>
			<c:forEach items="${list}" var="item">
				<div id="ctgr" data-id="${item.id}" class="item" style="position:relative;">
					<a href="#" class="list-group-item list-group-item-action bg-light">${item.ctgrName}</a>
					<span class="settingCtgr" onclick="settingCtgr('${item.id}', '${item.ctgrName}');" data-id="${item.id}"><i class="fas fa-pencil-alt"></i></span>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</c:catch>
