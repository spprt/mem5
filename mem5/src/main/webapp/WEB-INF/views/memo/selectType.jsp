<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
</head>
<body>
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">Make Memo</h1>
  <i></i>
</div>
<div class="container">
  <div class="card-deck mb-2 text-center">
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal">NOTE</h4>
      </div>
      <div class="card-body">
        <ul class="list-unstyled mt-2 mb-1">
          <li><a><i class="fas fa-sticky-note" style="font-size: 48px;"></i></a></li>
        </ul>
        <button type="button" class="btn btn-lg btn-block btn-outline-primary"  onclick="location.href='/memo/add?ctgrId=${ctgrId}&amp;type=<%= com.makao.memo.entity.Memo.TYPE_NOTE%>'">Select Note</button>
      </div>
    </div>
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal">TO-DO</h4>
      </div>
      <div class="card-body">
        <ul class="list-unstyled mt-2 mb-1">
          <li><i class="fas fa-list-ol" style="font-size: 48px;"></i></li>
        </ul>
        <button type="button" class="btn btn-lg btn-block btn-outline-primary" onclick="location.href='/memo/add?ctgrId=${ctgrId}&amp;type=<%= com.makao.memo.entity.Memo.TYPE_TODO%>'">Select TO-DO</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>