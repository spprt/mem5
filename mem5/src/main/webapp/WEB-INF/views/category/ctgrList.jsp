<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:catch>
	<c:choose>
		<c:when test="${empty authInfo}">
			<div class="list-group-item bg-light">로그인해 주세요.</div>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="item">
				<div id="ctgr${item.id}" style="position:relative;">
					<a href="#" class="list-group-item list-group-item-action bg-light">${item.ctgrName}</a>
					<span class="settingCtgr" data-id="${item.id}"><i class="fas fa-cog"></i></span>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</c:catch>
