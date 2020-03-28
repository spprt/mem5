<script type="text/javascript">
$(function(){
	$('#myCtgr').sortable({
		items: 'div:not(#ctgr_All)',
		axis: 'y', 
		update : function(event, ui) {
			const ctgrId = ui.item.data('id');
			const idx = ui.item.index() - 1;
			sortCtgr(ctgrId, idx);
		}
	});
	$("#myCtgr").disableSelection();
});
function sortCtgr(ctgrId, idx) {
	$.ajax({
		type: 'get',
		dataType: 'json',
		contentType: 'application/json',
		data: {ctgrId:ctgrId, idx:idx},
		url: "${pageContext.request.contextPath}/category/changeIdx",
		success : function(r) {
			if(r.result != 'success') {
				alert('오류발생');
				console.log(result);
			}
		},
		error : function(e) {
			console.error(e);
		}
	});
}
</script>
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