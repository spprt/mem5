    <div class="bg-light border-right" id="memolist-wrapper">
   	<div class="list-group-item list-group-item-action bg-light">
   		<input type="text"/> 
		<a href="${pageContext.request.contextPath}/memo/selectType"><i class="fas fa-plus"></i></a>
	</div>
    <div class="list-group list-group-flush">
      <!-- add memo -->
      	<%@ include file="/WEB-INF/views/memo/memoList.jsp" %>
      </div>
    </div>