<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Meeting</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- css -->
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/css/jquery-ui.css"
			type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
		</head>
	<body>
		<!-- editar, crear y borrar event -->
		<div id="content">
			<c:url value="${url}" var="urlAction"/>
			<form:form commandName="formMeeting" action='${urlAction}' method="POST">
				<!--id evento -->
				<form:hidden path="id" />
				<!--nombre -->
				<form:label path="name">
					<fmt:message key="label.name" />
				</form:label>
				<form:input class="owner" path="name" />
				<form:errors path="name" cssStyle="color: red" />
				<br />
				<!--tema -->
				<form:label path="theme">
					<fmt:message key="label.theme" />
				</form:label>
				<form:input class="owner" path="theme" />
				<br />
				<!--fecha del evento -->
				<form:label path="date">
					<fmt:message key="label.date" />
				</form:label>
				<form:input class="owner" id="datepicker" path="date" />
				<form:errors path="date" cssStyle="color: red" />
				<br />
				<!--hora de inicio-->
				<form:label path="startTime">
					<fmt:message key="label.startTime" />
				</form:label>
				<form:select class="owner" id="startTimepicker" path="startTime">
					<form:options items="${hours}" />
				</form:select>
				<br />
				<!--hora de finalizacion-->
				<form:label path="endTime">
					<fmt:message key="label.endTime" />
				</form:label>
				<form:select class="owner" id="endTimepicker" path="endTime">
					<form:options items="${hours}" />
				</form:select>
				<form:errors path="endTime" cssStyle="color: red" />
				<br />
				<!--salas-->
				<form:label path="hallId">
					<fmt:message key="label.hall" />
				</form:label>
				<form:select class="owner" path="hallId">
					<form:options items="${halls}" itemLabel="description"
						itemValue="id" />
				</form:select>
				<br />
				<!--Solo si sos invitado, si se pone en true, no se modifica mas-->
				
				<fmt:message key="label.confirm" />
				
				SI<form:radiobutton class="guest" path="state" value="1"/>
				NO<form:radiobutton class="guest" path="state" value="2"/>
				
				<br />
				<!--invitados-->
				<fmt:message key="label.guests" />
				<!-- se ingrea el user para el autocompletar -->
				<input class="owner" id="inputGuest" />
				<!-- va almacenar los ids de los user nuevos -->
				<form:hidden id="guestsIds" path="guestsIds" />
				<!-- va almacenar los ids de los user nuevos -->
				<input type="hidden" id="hiddenGuestId" />
				<!-- va a cargar el user actual -->
				<a class="linkDelete" id="btn-addUser" href="#">Agregar usuario</a>
				<!-- va listar los usuarios seleccionados del autocompletar -->
				<ul id="ulGuests">
				</ul>
				<form:button id="btn-edit">
					<fmt:message key="label.edit" />
				</form:button>
			</form:form>
			
			<c:url value="/deleteEvent.htm" var="urlDelete" >
					<c:param name="id" value='${formMeeting.id}'/> 
			</c:url>
			<a class="linkDelete" href='${urlDelete}'><fmt:message key="label.delete"/></a>
			<a href='<c:url value="/calendar.htm" />'><fmt:message key="label.cancel" /></a>
		</div>
				
		<!-- js -->
		<script src="${pageContext.request.contextPath}/js/lib/jquery-1.11.3.js"></script>
		<script src="${pageContext.request.contextPath}/js/lib/jquery-ui.js"></script>
		<script src="${pageContext.request.contextPath}/js/widgets/jquery.meeting.js"></script>
		<script>
				$(function() {
					$("#content").applyMeeting({
						urlContext : '${pageContext.request.contextPath}',
						guestsNames: ${formMeeting.guestsNames},
						isOwner:${formMeeting.isOwner},
						isGuest:${formMeeting.isGuest},
						state:${formMeeting.state}
					});
				});
		</script>
	</body>
</html>