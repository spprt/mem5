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
	let rightPage = '${param.rightPage}';
	if (rightPage) {
		loadRightArea(rightPage)
	} else {
		<c:if test="${empty authInfo}">
		$('#wrapper').addClass('toggled');
		</c:if>
		loadRightArea('${pageContext.request.contextPath}/memo/main');
	}
	
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
				var categoryId = $(this).parent().data('id');
				selectCtgr(categoryId);
			});
			
			selectCtgr('${param.ctgrId}');
		}
	});
	
	setSlimScroll();
	window.addEventListener('resize', function() {
		setSlimScroll();
	});
});
<%-- Category Selected Manage Start --%>
var selectedCtgrId;
function selectCtgr(ctgrId) {
	if (!ctgrId) {
		ctgrId = -1;
		clickCtgr(ctgrId);
	} else {
		if ((typeof selectedCtgrId ==='undefined') || selectedCtgrId != ctgrId) {
			clickCtgr(ctgrId);
		}
	}
}
function clickCtgr(categoryId) {
	$('#memoList').empty();
	getMemoList(categoryId).done(function(result) {
		if (result.array && result.array.length > 0) {
			let arr = result.array;
			for (i = 0; i < arr.length; i++) {
				var div = $('<div class="memoItem" draggable="true" ondragstart="dragMemo(event)" >');
				var a = $('<a class="list-group-item list-group-item-action bg-light">').appendTo(div);
				var type = arr[i][2];
				if (type == <%= com.makao.memo.entity.Memo.TYPE_NOTE %>) {
					$('<i class="fas fa-sticky-note"></i>').appendTo(a);
				} else if (type == <%= com.makao.memo.entity.Memo.TYPE_TODO %>) {
					$('<i class="fas fa-list-ul"></i>').appendTo(a);
				}
				$('<span class="title">').css('padding-left', '5px').attr({'title': arr[i][1]}).text(arr[i][1]).appendTo(a);
				
				div.attr({'data-id': arr[i][0], 'data-title' : arr[i][1], 'onclick': 'viewMemo('+arr[i][0]+');'}).appendTo('#memoList');
			}
		} else {
			$('<div class="list-group-item list-group-item-action bg-light">').text('표시할 목록이 없습니다.').appendTo('#memoList');
		}
	}).fail(function(result) {
		$('<div class="list-group-item list-group-item-action bg-light">').text('표시할 목록이 없습니다.').appendTo('#memoList');
		console.error(result);
	});
	selectedCtgrId = categoryId;
	$('#category-wrapper .list-group-item').removeClass('selected');
	
	var item = $('#category-wrapper').find('.item[data-id="'+categoryId+'"]');
	item.find('.list-group-item').addClass('selected');
}
<%-- Category Selected Manage End --%>
<%-- index우측화면 페이지로드 --%>
function loadRightArea(page) {
	$('#rightContainer').load(page);
}
function settingCtgr(id, name) {
	var modal = $('#ctgr-modal');
	modal.addClass('update');
	modal.attr('data-id', id);
	modal.find('#ctgrName').val(name);
	<%--모달 닫았을때 초기화--%>
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
	if (id) {
		frm.appendChild(addData("id", id));
	}
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
function viewMemo(id) {
	loadRightArea('${pageContext.request.contextPath}/memo/view?id=' + id)
}
function getMemoList(ctgrId) {
	var url = (ctgrId == -1) ? '/memo/allMyList' : '/memo/myList';
	return $.ajax({
		type: 'get',
		data: {ctgrId: ctgrId},
		dataType: 'json',
		contentType: 'application/json',
		url: '${pageContext.request.contextPath}' + url
	});
}
function memoType() {
	loadRightArea('${pageContext.request.contextPath}/memo/selectType?ctgrId=' + selectedCtgrId);
}
<%-- 메모검색 --%>
function memoSearch(){
    var value, title, item, i;

    value = document.getElementById("searchMemoInput").value.toUpperCase();
    item = document.getElementsByClassName("memoItem");

    for (i = 0;i < item.length;i++) {
      title = item[i].getElementsByClassName("title");
      if (title[0].innerHTML.toUpperCase().indexOf(value) > -1) {
        item[i].style.display = 'block';
      } else {
        item[i].style.display = 'none';
      }
    }
}
<%-- 메모 분류이동 Drag Drop--%>
function allowDropMemo(ev) {
  ev.preventDefault();
}

function dragMemo(ev) {
  ev.dataTransfer.setData("id", ev.target.dataset.id);
}

function dropMemo(ev) {
  ev.preventDefault();
  const memoId = ev.dataTransfer.getData("id");
  const ctgrId = ev.target.dataset.id;
  if (ctgrId != -1) {
	  moveMemo(memoId, ctgrId).done(function(r){
		  // 클라이언트단에서 메모 리스트에서 분류변경된 메모는 빼준다(단, 현재 선택된 분류가 전체보기 일 경우는 빼지않음)
		  if (r) {
			  if (r.result == 'success' && selectedCtgrId != -1) {
				  $('.memoItem[data-id='+memoId+']').remove();
			  }
		  }
	  }).fail(function(result) {
		  console.error('move Memo Error:', result);
	  });
  }
}

function moveMemo(memoId, ctgrId) {
	return $.ajax({
				type: 'get',
				data: {memoId : memoId, ctgrId: ctgrId},
				dataType: 'json',
				contentType: 'application/json',
				url: '${pageContext.request.contextPath}/memo/move'
			});
}
<%-- slimscroll --%>
function areaSlimScroll(headerSelectorId, slimDivId) {
	const headerHeight = document.getElementById(headerSelectorId).offsetHeight;
	let slimHeight = window.innerHeight - headerHeight;
	$('#' + slimDivId).slimScroll({height: slimHeight});
}
function setSlimScroll() {
	areaSlimScroll('sidebarHeader', 'myCtgr');
	areaSlimScroll('memoHeader', 'memoList');
	areaSlimScroll('rightHeader', 'rightContainer');
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
      <div class="container-fluid" id="rightContainer">
<!--         <h1 class="mt-4">Mem<b>5</b></h1> -->
<!--         <p>The starting state of the menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will change.</p> -->
<!--         <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>. The top navbar is optional, and just for demonstration. Just create an element with the <code>#menu-toggle</code> ID which will toggle the menu when clicked.</p> -->
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
		</div>
	</div>
</div>
</body>
</html>
