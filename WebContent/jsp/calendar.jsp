<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Calendar</title>
		<!-- css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/lib/jquery-1.11.3.js"></script>
		<script src="${pageContext.request.contextPath}/js/lib/jquery-ui.js"></script>
		<script src="${pageContext.request.contextPath}/js/widgets/jquery.calendar.js"></script>
		<script>
			$(function() {
				$("#content").applyCalendar({
					urlContext : '${pageContext.request.contextPath}'
				});
			});
		</script>
	</head>
	<body>
		<a href='<c:url value="/logout.htm"/>'>Salir</a>
		<div id="content">
			<div id="table" >
				<table class="table table-bordered"  id="table" style="overflow: scroll; width: 1200px;">
					<thead>
						<tr>
							<th>Hours</th>
							<th>Sunday<div id="sundayId"></div></th>
							<th>Monday<div id="mondayId"></div></th>
							<th>Tuesday<div id="tuesdayId"></div></th>
							<th>Wednesday<div id="wednesdayId"></div></th>
							<th>Thursday<div id="thursdayId"></div></th>
							<th>Friday<div id="fridayId"></div></th>
							<th>Saturday<div id="saturdayId"></div></th>
						</tr>
					</thead>
					<tbody>
						 <tr>
						 	<th>
							 	<div style="width: 30px">
							 		<div style="top: 10px">00:00</div><div style="top: 30px">00:30</div><div style="top: 50px">01:00</div>
							 		<div style="top: 70px">01:30</div><div style="top: 90px">02:00</div><div style="top: 110px">02:30</div>
							 		<div style="top: 130px">03:00</div><div style="top: 150px">03:30</div><div style="top: 170px">04:00</div>
							 		<div style="top: 190px">04:30</div><div style="top: 210px">05:00</div><div style="top: 230px">05:30</div>
							 		<div style="top: 250px">06:00</div><div style="top: 270px">06:30</div><div style="top: 290px">07:00</div>
							 		<div style="top: 310px">07:30</div><div style="top: 330px">08:00</div><div style="top: 350px">08:30</div>
							 		<div style="top: 370px">09:00</div><div style="top: 390px">09:30</div><div style="top: 410px">10:00</div>
							 		<div style="top: 430px">10:30</div><div style="top: 450px">11:00</div><div style="top: 470px">11:30</div>
							 		<div style="top: 490px">12:00</div><div style="top: 510px">12:30</div><div style="top: 530px">13:00</div>
							 		<div style="top: 550px">13:30</div><div style="top: 570px">14:00</div><div style="top: 590px">14:30</div>
							 		<div style="top: 610px">15:00</div><div style="top: 630px">15:30</div><div style="top: 650px">16:00</div>
							 		<div style="top: 670px">16:30</div><div style="top: 690px">17:00</div><div style="top: 710px">17:30</div>
							 		<div style="top: 730px">18:00</div><div style="top: 750px">18:30</div><div style="top: 770px">19:00</div>
							 		<div style="top: 790px">19:30</div><div style="top: 810px">20:00</div><div style="top: 830px">20:30</div>
							 		<div style="top: 850px">21:00</div><div style="top: 870px">21:30</div><div style="top: 890px">22:00</div>
							 		<div style="top: 910px">12:30</div><div style="top: 930px">23:00</div><div style="top: 950px">23:30</div>
							 	</div>
						 	</th>
							<td><div class="columnSunday"></div></td>
							<td><div class="columnMonday"></div></td>
							<td><div class="columnTuesday"></div></td>
							<td><div class="columnWednesday"></div></td>
							<td><div class="columnThursday"></div></td>
							<td><div class="columnFriday"></div></td>
							<td><div class="columnSaturday"></div></td>
						</tr>
					</tbody>
				</table>
			</div>
			<button id="btn-next">Siguiente</button>
			<button id="btn-previous">Anterior</button>
			<a href='<c:url value="/newMeeting.htm" />' > Crear reunion</a>
			<a href='<c:url value="/newPrivateEvent.htm" />' > Crear evento privado</a>
		</div>
	</body> 
</html>