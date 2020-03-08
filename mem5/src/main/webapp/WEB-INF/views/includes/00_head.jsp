<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-client_id" content="493592123487-pgq51iarjll7k6opie5o6vcatqadvoh1.apps.googleusercontent.com">
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style-signup.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
    
    <title>mem5</title>
	<script>
		function makeForm(actionURL, formName) {
			var f = document.createElement("form");
			f.name = formName;
			f.action = actionURL;
			f.method = "post";
			f.target = "";
			return f;
		}
		function addData(name, value) {
			var elem = document.createElement("input");
			elem.setAttribute("type", "hidden");
			elem.setAttribute("name", name);
			elem.setAttribute("value", value);
			return elem;
		}
		function onLoad() {
			gapi.load('auth2', function() {
				gapi.auth2.init();
			});
		}
		function onSignIn(googleUser) {
			var profile = googleUser.getBasicProfile();
			console.log('ID : ' + profile.getId());
			console.log('Name : ' + profile.getName());
			console.log('Image URL : ' + profile.getImageUrl());
			console.log('Email : ' + profile.getEmail());
// 			gapi.auth2.init();
			if (profile) {
// 				var frm = makeForm("${pageContext.request.contextPath}/signin", "loginCommand");
				var frm = document.loginCommand;
				frm.appendChild(addData("email", profile.getEmail()));
				frm.appendChild(addData("name", profile.getName()));
				frm.appendChild(addData("imageUrl", profile.getImageUrl()));
				frm.appendChild(addData("oauthId", profile.getId()));
				frm.submit();
			}
		}
		function signOut() {
			var auth2 = gapi.auth2.getAuthInstance();
			auth2.signOut().then(function () {
				console.log('User signed out.');
				location.href = "${pageContext.request.contextPath}/logout";
			});
		}
	</script>