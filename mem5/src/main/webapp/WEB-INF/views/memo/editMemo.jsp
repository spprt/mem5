<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<script type="text/javascript">
function formsubmit(f){
	let isValid = f.checkValidity();
    f.classList.add('was-validated');
    if (isValid) {
		let tag = f.tag;
		let tagArr = [];
	    if(tag.value.length > 0){
	    	tagArr = Array.from(new Set(tag.value.split(',')));
	    	tagArr = tagArr.filter(v =>  v != '');
	    }
	    
	  	let $tags = $('input[name*="tags"');
	  	if($tags) {
	  		$tags.remove();
	  	}
	  	tagArr.forEach((t, idx) => {
	  		if (idx < 10)
	  			f.appendChild(addData('tags['+ idx +'].tag', t));
	  	});
	    
		f.method = 'post';
		f.submit();
    }
}
</script>
<body>
<div class="col-md-12">
<c:choose>
	<c:when test="${empty authInfo}">
		 <h4 class="mb-3">로그인을 먼저 해주세요</h4>
	</c:when>
	<c:otherwise>
	      <h4 class="mb-3">Edit Note</h4>
	      <form class="needs-validation" novalidate="" method="post" action="${pageContext.request.contextPath }/memo/saveEditMemo">
	      <input type="hidden" name="id" id="id" value="${memo.id}">
	      	<input type="hidden" name="modUserId" id="modUserId" value="${authInfo.id}">
	      	<input type="hidden" name="regUserId" id="regUserId" value="${memo.regUserId}">
	      	<input type="hidden" name="ctgrId" id="ctgrId" value="${ctgrId}">
            <input type="hidden" name="type" value="${memo.type}">
			<div class="mb-3">
	            <label for="title">Title</label>
	            <input type="text" class="form-control" name="title" id="title" placeholder="" value="${memo.title}" maxlength="255" required="">
	            <div class="invalid-feedback">
	              Valid title is required.
	            </div>
			</div>
	
	        <div class="mb-3">
	          <label for="username">Username</label>
	          <div class="input-group">
	            <div class="input-group-prepend">
	              <span class="input-group-text">@</span>
	            </div>
	            <input type="text" class="form-control" placeholder="Username" required="" value="${authInfo.name}" readonly>
	          </div>
	        </div>
	
	        <div class="mb-3">
	          <label for="tag">TAG <span class="text-muted">(Optional)</span></label>
	          <input type="text" class="form-control" name="tag" placeholder="쉼표(,)를 구분해서 입력해주세요" value="${tagStr}">
	          <div class="invalid-feedback">
	            쉼표(,)를 구분해서 입력해주세요
	          </div>
	        </div>
			<c:set var="noteType" value="<%=com.makao.memo.entity.Memo.TYPE_NOTE %>"/>
			<c:if test="${memo.type eq noteType }">
	        <div class="mb-3">
	          <label for="content">Content</label>
	          <textarea type="text" class="form-control" name="content" id="content" required="">${memo.content}</textarea>
	        </div>
	        </c:if>
	        <hr class="mb-4">
	        <input type="button" value="Register" class="btn btn-primary btn-lg btn-block" onclick="formsubmit(this.form)">
	      </form>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>