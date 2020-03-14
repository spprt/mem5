<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<!-- Menu Toggle Script -->
<script>
$(function(){
	$("#menu-toggle").click(function(e) {
	    e.preventDefault();
	    $("#wrapper").toggleClass("toggled");
	});
	
	<%--카테고리 목록--%>
	var myCtgrDiv = $('#myCtgr');
	$.ajax({
		type: "get",
		url: "${pageContext.request.contextPath}/category/myList",
		success : function(result) {
			myCtgrDiv.find('.item').remove();
			$(result).appendTo(myCtgrDiv);
			$('#category-wrapper .list-group .settingCtgr').click(function(e) {
				e.stopPropagation();
				$('#ctgr-modal').modal();
			});
			$('#category-wrapper .list-group-item').click(function(e) {
				e.stopPropagation();

				<%--카테고리아이디--%>
				var categoryId = $(this).parent().data('cid');
				console.log(categoryId);
				getMemoList(categoryId).done(function(result){
					console.log('reuslt', result)
				}).fail(function(result){
					console.error(result);
				});
				$("#wrapper").toggleClass("sub");
			});
		}
	});
});
function settingCtgr(id, name) {
	var modal = $('#ctgr-modal');
	modal.addClass('update');
	modal.attr('data-id', id);
	modal.find('#ctgrName').val(name);
	modal.on('hidden.bs.modal', function() {
		modal.find('#ctgrName').val('');
		modal.removeClass('update');
		modal.removeAttr('data-id');
		modalCtgrId = -1;
	});
}
function saveCtgr(obj) {
	var id = $(obj).parents('#ctgr-modal').data('id');
	var frm = document.category;
	if (id)
		frm.appendChild(addData("id", id));
	frm.action = "${pageContext.request.contextPath}/category/saveAdd";
	frm.method = "post";
	frm.submit();
}
function deleteCtgr(obj) {
	var id = $(obj).parents('#ctgr-modal').data('id');
	var frm = document.category;
	frm.appendChild(addData("id", id));
	frm.action = "${pageContext.request.contextPath}/category/del";
	frm.method = "get";
	frm.submit();
}
function getMemoList(ctgrId) {
	return $.ajax({
		type:'get',
		dataType : 'json',
		contentType:"application/json",
		url: '${pageContext.request.contextPath}/memo/allMyList'
	});
}
</script>
<body>
  <div class="d-flex" id="wrapper">
	<div id="sidebar-wrapper">
    <!-- Category -->
    <%@ include file="/WEB-INF/views/includes/01_category.jsp" %>
    <!-- /#Category-wrapper -->
    
    <!-- memo list -->
    <%@ include file="/WEB-INF/views/includes/02_memolist.jsp" %>
    <!-- /#memo list-wrapper -->
    </div>

    <!-- Page Content -->
    <div id="page-content-wrapper">
    <%@ include file="/WEB-INF/views/includes/04_nav.jsp" %>
      <div class="container-fluid">
        <h1 class="mt-4">Simple Sidebar</h1>
        <p>The starting state of the menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will change.</p>
        <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>. The top navbar is optional, and just for demonstration. Just create an element with the <code>#menu-toggle</code> ID which will toggle the menu when clicked.</p>
      </div>
    </div>
    <!-- /#page-content-wrapper -->
  </div>
  <!-- /#wrapper -->
<!-- Category Add Modal -->
<div class="modal fade" id="ctgr-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">Close</span>
				</button>
			</div>
			<div class="modal-body">
				<p class="insert">분류를 추가해주세요.</p>
				<p class="update">분류를 수정해주세요.</p>
				<form name="category" action="${pageContext.request.contextPath}/category/saveAdd" method="post">
					<div class="input-group">
						<input id="ctgrName" name="ctgrName" type="text" class="form-control" placeholder="분류명">
					</div>
					<br/>
					<button type="button" onclick="saveCtgr(this);" value="sub" name="sub" class="btn btn-primary">저장</button>
					<button type="button" onclick="deleteCtgr(this);" value="sub" name="sub" class="btn btn-secondary update">삭제</button>
				</form>
			</div>
<!-- 			<div class="modal-footer"> -->
<!-- 				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
<!-- 			</div> -->
		</div>
	</div>
</div>
</body>
</html>
