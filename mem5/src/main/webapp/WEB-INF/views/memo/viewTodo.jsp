<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/views/includes/00_head.jsp"%>
<style>
.todolist {
	background-color: #FFF;
	padding: 20px 20px 10px 20px;
	margin-top: 30px;
}
.todolist h1 {
	margin: 0;
	padding-bottom: 20px;
/* 	text-align: center; */
}
.todolist li .checkSpan {
	padding-left: 5px;
	line-height: 33px;
}
.todolist li.done .checkSpan {
	text-decoration: line-through;
}
.todolist li .remove-item {
	display: none;
}
.todolist li:hover .remove-item {
	display: inline-block;
}
.form-control {
	border-radius: 0;
}
li.ui-state-default {
	background: #fff;
	border: none;
	border-bottom: 1px solid #ddd;
}
li.ui-state-default:last-child {
	border-bottom: none;
}
.todo-footer {
	background-color: #F4FCE8;
	margin: 0 -20px -10px -20px;
	padding: 10px 20px;
}
#checkAllDone, #checkAllDoing {
	margin-top: 10px;
}
</style>
<script>
$(function(){
	$("#todo-sortable").sortable({
		stop: function(event, ui) {
			var $this = $(this);
			var lis = $this.find('.ui-state-default');
			var ids = [];
			for (var i = 0; i < lis.length; i++) {
				ids.push(lis[i].getAttribute('data-id'));
			}
			sortTodo(ids.join(','));
        }
	});
	$("#todo-sortable").disableSelection();

	countTodos();

	// all done btn
	$("#checkAllDone").click(function() {
		allDoneTodo();
	});
	
	$("#checkAllDoing").click(function() {
		allDoingTodo();
	});

	//create todo
	$('.add-todo').on('keypress', function(e) {
		e.preventDefault;
		if (e.which == 13) {
			if ($(this).val() != '') {
				var todo = $(this).val();
				createTodo(todo);
				countTodos();
			} else {
				// some validation
			}
		}
	});
	// mark task as done
	$('.todolist').on('change', '#todo-sortable li input[type="checkbox"]', function() {
		doneTodo(this);
	});

	//delete done task from "already done"
	$('.todolist').on('click', '.remove-item', function() {
		removeItem(this);
	});
});
//count tasks
function countTodos() {
	var allCount = $("#todo-sortable li").length;
	var doneCount = $("#todo-sortable li").not('.done').length;
	$('.count-todos').html(doneCount + ' of ' + allCount);
}

//create task
function createTodo(text) {
	<%--서버에서 append--%>
	var memoId = ${memo.id};
	$.ajax({
		type: "get",
		data: {id:memoId, title:text},
		dataType : "json",
		contentType: "application/json",
		url: "${pageContext.request.contextPath}/memo/todo/add",
		success : function(result) {
			if (result) {
				var li = $('<li>').addClass('ui-state-default');
				li.html('<div class="checkbox">\
						<label><input type="checkbox" value=""/><span class="checkSpan"></span></label>\
						<button class="remove-item btn btn-default btn-xs pull-right">\
							<i class="fas fa-trash-alt"></i>\
						</button>\
					</div>');
				li.attr('data-id', result.id);
				li.find('.checkSpan').text(text);
				li.appendTo($('#todo-sortable'));
				$('.add-todo').val('');
			}
		}		
	});
	countTodos();
}

//mark task as done
function doneTodo(element) {
	var li = $(element).closest('li');
	if (li.hasClass('done')) {
		li.removeClass('done');
	} else {
		li.addClass('done');
	}
	var todoId = li.data('id');
	var checked = $(element).prop('checked'); 
	<%--서버에서 checked--%>
	$.ajax({
		type: "get",
		data: {id:todoId, check:checked},
		url: "${pageContext.request.contextPath}/memo/todo/check",
		success : function(result) {}		
	});
	countTodos();
}

function allDoneTodo() {
	$('#todo-sortable li').each(function() {
		var $this = $(this);
		$this.addClass('done');
		$this.find('input').attr('checked', 'true');
		$this.find('input').get(0).checked = true;
	});
	allCheck(true);
}

function allDoingTodo() {
	$('#todo-sortable li').each(function() {
		var $this = $(this);
		$this.removeClass('done');
		$this.find('input').get(0).checked = false;
	});
	allCheck(false);
}

function allCheck(checked) {
	var memoId = ${memo.id};
	<%--서버에서 일괄 처리--%>
	$.ajax({
		type: "get",
		data: {id:memoId, check:checked},
		url: "${pageContext.request.contextPath}/memo/todo/allCheck",
		success : function(result) {
			alert('success');
		}		
	});
	countTodos();
}

//remove done task from list
function removeItem(element) {
	<%--서버에서 todo 삭제처리--%>
	var $element = $(element);
	var todoId = $element.closest('li').data('id');
	$.ajax({
		type: "get",
		data: {id:todoId},
		url: "${pageContext.request.contextPath}/memo/todo/delete",
		success : function(result) {
			alert('success');
		}		
	});
	$element.closest('li').remove();
	countTodos();
}
function sortTodo(ids) {
	var memoId = ${memo.id};
	<%--서버에서 일괄 처리--%>
	$.ajax({
		type: "get",
		data: {memoId:memoId, ids:ids},
		url: "${pageContext.request.contextPath}/memo/todo/sort",
		success : function(result) {
			alert('success');
		}		
	});
}
</script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="todolist not-done">
				<h1>${memo.title}</h1>
				<c:if test="${not empty memo.tags }">
					<div class="media text-muted">
					  <p class="media-body pb-3 mb-0 small lh-125">
					  	<c:forEach var="tag" items="${memo.tags}">
							#<c:out value="${tag.tag}"/>
						</c:forEach>
					  </p>
					</div>
				</c:if>
				<input type="text" class="form-control add-todo" placeholder="Add todo">
				<button id="checkAllDone" class="btn btn-primary">Mark all as done</button>
				<button id="checkAllDoing" class="btn btn-primary">Mark all as doing</button>
				<hr>
				<ul id="todo-sortable" class="list-unstyled">
					<c:forEach var="todo" items="${memo.todos}">
						<li class="ui-state-default <c:if test="${todo.complete == true}">done</c:if>" data-id="${todo.id}">
						<div class="checkbox">
							<label> <input type="checkbox" value="" <c:if test="${todo.complete == true}">checked="true"</c:if>/><span class="checkSpan">${todo.title}</span></label>
							<button class="remove-item btn btn-default btn-xs pull-right">
								<i class="fas fa-trash-alt"></i>
							</button>
						</div>
					</li>
					</c:forEach>
				</ul>
				<div class="todo-footer">
					<strong><span class="count-todos"></span></strong> Items Left
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>