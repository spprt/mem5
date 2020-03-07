<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/views/includes/00_head.jsp" %>
    <title>step2 | mem5</title>
</head>
<body class="signup-pages">

    <div class="signup-box">
        <div class="signup-logo">
            <a href="#"><b>mem</b>5</a>
        </div>

        <div class="signup-box-body">
            <p class="box-msg">Register a new membership</p>
            <form:form role="form" modelAttribute="user" action="${pageContext.request.contextPath }/employee/save" method="post">
                <div class="form-group has-feedback">
                    <form:input type="text" class="form-control" placeholder="EMAIL" path="email"/>
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    <form:errors path="email" class="signup-errors"/>
                </div>
                <div class="form-group has-feedback">
                    <form:input type="password" class="form-control" placeholder="PASSWORD" path="password"/>
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    <form:errors path="password" class="signup-errors"/>
                </div>
                <div class="form-group has-feedback">
                    <form:input type="password" class="form-control" placeholder="Retype PASSWORD" path="checkPassword"/>
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    <form:errors path="checkPassword" class="signup-errors"/>
                </div>
                <div class="form-group has-feedback">
                    <form:input type="text" class="form-control" placeholder="NAME" path="name"/>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <form:errors path="name" class="signup-errors"/>
                </div>
                <div class="row">
                    <div class="col-xs-8">
                    </div>
                    <div class="col-xs-3">
                        <button type="submit" class="btn btn-style">sign in</button>
                    </div>
                </div>
            </form:form>
            <div class="social-auth-links text-center">
                <p>- OR -</p>
            </div>

            <a href="login.html" class="text-center">I already have a membership</a>
        </div>
    </div>
</body>
</html>