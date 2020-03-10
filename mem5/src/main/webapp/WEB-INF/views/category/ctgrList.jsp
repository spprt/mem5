<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:catch>
	<c:choose>
		<c:when test="${empty authInfo}">
			<div class="list-group-item">로그인해 주세요.</div>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="item">
				<div id="ctgr${item.id}">
					<a href="#" class="list-group-item list-group-item-action bg-light">${item.ctgrName}</a>
					<button type="button" class="btn btn-default btn-xs"> <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Star</button>
					<button type="button" class="btn btn-default btn-xs"> <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>Star</button>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</c:catch>
