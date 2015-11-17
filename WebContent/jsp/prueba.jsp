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
		<title>Autocomplete</title>
		<style>
/* 			.evento { */
/* 				width: 60px; */
/* 				height: 50px; */
/* 				background: orange; */
/* 				border: 1px solid yellow; */
/* 				position: absolute; */
/* 				z-index: 1; */
/* 				top: 0px; */
/* /* 				lo incilizo en 0 para probar */ */
/* 			} */
			
/* 			.columna { */
/* 				overflow: hidden; */
/* 				width: 200px; */
/* 				height: 600px; */
/* 				background: green; */
/* 				position: relative;  */
/* 			} */
			
		</style>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/lib/jquery-1.11.3.js"></script>
		<script src="${pageContext.request.contextPath}/js/lib/jquery-ui.js"></script>

		<script>

			$(function() {
				
				$("#usuarios").autocomplete({
					 	minLength: 3,
						source : function(request, response) {
					    	var url = "${pageContext.request.contextPath}/services/getUsers";
							$.ajax({
								url : url,
								type: "POST",
								data: { term: request.term },
								dataType : "json",
								success : function(data) {
									response($.map(data.users, function(item) {
										return {
											label : item.label,
											value : item.label,
											key : item.value
										};
									}));
								}
							});
						},
						select: function (event, ui) {
					        $("#usuarioId").val(ui.item.key); // save selected id to hidden input
					    }
				});
				
				$("#btn-addUsuario").click(function() {
					var url = "#?id=" + $("#usuarioId").val();
					$('<li class="li-user" value='+$("#usuarioId").val()+ '>' + $("#usuarios").val() +' <a href="'+url+'">Eliminar</a></li>').appendTo("#ul-addUser");	
					var array = $(".li-user");
					var input = $("#listUser");
					var cadena="";
					for(var i= 0; i<array.length; i++){
						if(i == (array.length - 1)){
							cadena+= array[i].value.toString();
						} else {
							cadena+= array[i].value.toString()+",";
						}
					}
					debugger;
					input.val(cadena);
				});
				
				$('.evento').draggable({
					grid: [200,25],
					containment : '.columna'
				});

				$(".columna").droppable({
					drop : function(event, ui) {
						ui.draggable.removeClass("evento");	
						ordenarCeldaActual(ui.draggable);
						ui.draggable.addClass("evento");
						ordenarCeldas();
					}
				});

				function ordenarCeldas() {
					var events = $('div.evento');
 					var matriz = new Array(48);
 					
 					for(var i = 0; i < matriz.length; i++){
 						matriz[i] = new Array();
 					}
					
 					for(var i = 0; i < 48; i++){
 						array = matriz[i];
 						var top = obtenerTop(i);
 						for(var j = 0; j< events.length; j++){
 							var evento = $(events[j]);
 							var positionEvent = getPosition(evento);
 							if(positionEvent.top == top || (top > positionEvent.top && top < positionEvent.flat) ){
 								array.push(evento); 
 							}
 						}
 						if(array.length > 0){
 							ordenarCeldaByIndex(array, top);
 						}
 					}
				}
				
				function obtenerTop(index) {
					var top = 0;
					for(var i = 0; i < 48; i++){
						if(i == index){
							return top;	
						}
						top+=25;
					}
				}
				
				function ordenarCeldaByIndex(array, top) {
					array.sort(function(argA, argB) {
						var a = $(argA);
						var b = $(argB);
						return parseInt(a.find(".indice").val())
								- parseInt(b.find(".indice").val());
					});
					desplazar(ordenarArray(array, top), top);
				}
				
				function ordenarArray(events, top) {
					var array = new Array();
					for(var i = 0; i < events.length; i++){
						var evento = $(events[i]);
						var positionEvent = getPosition(evento);
						if(array.length > 0){
							if(top != positionEvent.top){
								if(!array[evento.find(".indice").val()]){
									array[evento.find(".indice").val()] = evento;
								} else {// si la posicion del array2 i ya se encuentra ocupada igualmente la reemplazo por el evento de mayor prioridad y desplazo a los demas en 1 
									// logica para cuando tengo 2 eventos con el mismo indice, pero uno tiene mayor prioridad
									var arrayAux = new Array();
								    var aux = 0;
									for(var index = parseInt(evento.find(".indice").val()); index < array.length; index++, aux++){
										arrayAux[aux] = array[index];
									}
									array[evento.find(".indice").val()] = evento;
									arrayAux = ordenarArray(arrayAux, top);
									array = array.concat(arrayAux);
								}
								
							} else {
								for(var j = 0; j<array.length; j++){
									if(!array[j]){
										break;
									}
								}
								array[j] = evento;
							}
						} else {
							if(top != positionEvent.top){
								array[evento.find(".indice").val()] = evento;
							} else {
								array[0] = evento;
							}
						}
					}
					return array;
				}
				
				function desplazar(array, top) {
					for (var i = 0; i < array.length; i++) {
						if(array[i])
						{
							evento = $(array[i]);
							positionEvent = getPosition(evento);
							if(positionEvent.top == top){
								evento.find(".indice").val(i);
								var indice = i + 1;
								var left = obtenerDesplazamiento(indice);
								evento.css({
									zIndex : indice,
									left : left + "px"
								});	
							}
						}
					}
				}
				
				function ordenarCeldaActual(drag) {
					var events = $('div.evento');
					var array = new Array();
					var positionDrag = getPosition(drag);
					for (var i = 0; i < events.length; i++) {
						var evento = $(events[i]);
						positionEvent = getPosition(evento);
						if ((positionEvent.top == positionDrag.top || existeColisionCeldaActual(positionEvent, positionDrag)) && existeIndice(array, evento.find(".indice").val())) {
							if(positionEvent.top != positionDrag.top){
								array[evento.find(".indice").val()] = evento;
							} else {
								if(array.length > 0){
									for(var j = 0; j < array.length; j++){
										if(!array[j]){
											break;
										}
									}
									array[j] = evento;
									
								} else {
									array[0] = evento; 
								}
							}
						}
					}
					var indice = getIndexLibre(array);
					drag.find(".indice").val(indice);
					indice = indice + 1;
					var left = obtenerDesplazamiento(indice);
					drag.css({
						left : left + "px",
						zIndex : indice,
					});
					drag.find(".top").val(positionDrag.top);
				}
				
				function getPosition(object){
					var pos = 0;
					var position = {};
					pos = object.css("top").indexOf("px");
					position.top = parseInt(object.css("top").substring(0,pos));
					pos = object.css("height").indexOf("px");
					position.height = parseInt(object.css("height").substring(0,pos));
					pos = object.css("left").indexOf("px");
					position.left = parseInt(object.css("left").substring(0,pos));
					position.flat = position.top + position.height;
					return position;
				}
				
				function existeIndice(array, indice){
					for(var i = 0; i < array.length; i++){
						if(array[i]){
							if(array[i].find(".indice").val() == indice){
								return false;
							}
						}
					}
					return true;
				}
				
				function existeColisionCeldaActual(positionEvent, positionDrag) {
					if(positionEvent.height > positionDrag.height){
						return  positionEvent.top < positionDrag.top && positionEvent.flat > positionDrag.flat;
					} else {
						return   positionEvent.top > positionDrag.top && positionEvent.flat < positionDrag.flat;
					}
				}

				function getIndexLibre(array) {
					var indice = 0;
					for (var i = 0; i < array.length; i++) {
						if (!array[i]) {
							indice = i;
							return indice;
						}
					}
					return i;
				}

				// obtiene los desplazamientos
				function obtenerDesplazamiento(indice) {
					var left;
					left = -40;
					for (var i = 0; i < indice; i++) {
						left = left + 40;
					}
					return left;
				}
			});
		</script>
	</head>
<body>
	<input id="usuarios"/>
	<input type="hidden" id="usuarioId" />
	<input type="text" id="listUser"/>
	<button id="btn-addUsuario">Agregar usuario</button>
	<ul id="ul-addUser">
	</ul>
	<div class="columna">
<!-- 		<div class="evento"> -->
<!-- 			<div class="head"> -->
<!-- 				Lalala<br /> Hora: 00 -->
<!-- 			</div> -->
<!-- 			<input type="hidden" class="indice" value="0"> -->
<!-- 		</div> -->
<!-- 		<div class="evento"> -->
<!-- 			<div class="head"> -->
<!-- 				Pepepe <br />Hora: 00 -->
<!-- 			</div> -->
<!-- 			<input type="hidden" class="indice" value="0"> -->
<!-- 		</div> -->
<!-- 		<div class="evento"> -->
<!-- 			<div class="head"> -->
<!-- 				Pepepe <br />Hora: 00 -->
<!-- 			</div> -->
<!-- 			 <input type="hidden" class="indice" value="0"> -->
<!-- 		</div> -->
<!-- 		<div class="evento" style="height: 600px;"> -->
<!-- 			<div class="head"> -->
<!-- 				Jajaja<br /> Hora: 5 -->
<!-- 			</div> -->
<!-- 			<input type="hidden" class="indice" value="0"> -->
<!-- 		</div> -->
	</div>
</body>
</html>


    