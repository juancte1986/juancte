<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/juego.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
		<title><fmt:message key="label.title"/></title>
	</head>
	<body>
		<form:form commandName="formLogin" action='${pageContext.request.contextPath}/processFormLogin.htm' method="POST">
			<form:label path="user">
				<fmt:message key="label.user" />
			</form:label>
			<form:input path="user"/>
			<form:errors path="user" cssStyle="color: red" />
			<br/>
			<form:label path="password">
				<fmt:message key="label.password" />
			</form:label>
			<form:input path="password"/>
			<form:errors path="password" cssStyle="color: red" />
			<br/>
			<form:label path="remember">
				<fmt:message key="label.remember" />
			</form:label>
			<form:checkbox path="remember"/>
			<form:button><fmt:message key="label.start"/></form:button>
		</form:form>
		<c:if test="${error}">
			<fmt:message key="errors.login.start" />
		</c:if>
	</body>
</html>