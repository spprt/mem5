<div class="bg-light border-right" id="category-wrapper">
	<div class="sidebar-heading" id="sidebarHeader">
		<a href="${pageContext.request.contextPath}" class="text-dark text-decoration-none">mem5</a>
		<a href="#" class="ctgrAdd" data-toggle="modal" data-target="#ctgr-modal"><i class="fas fa-plus"></i></a>
	</div>
	<div class="list-group list-group-flush">
		<!-- add category -->
		<div id="myCtgr">
			<%@ include file="/WEB-INF/views/category/ctgrList.jsp" %>
		</div>
	</div>
</div>