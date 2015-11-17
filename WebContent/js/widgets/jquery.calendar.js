$.widget('custom.applyCalendar', {
	options : {
		urlContext : "",
	},

	_create : function() {
		this._bindVars();
		this._initialize();
		this._bindEvents();
	},

	_bindVars : function() {
		this.urlContext = this.options.urlContext;
		this.table = this.element.find(".table");
		this.week = new Array();
		this.events = new Array();
		this.drag = new Object();
		this.dragPosition = {};
	},

	_initialize : function() {
		this._loadEventsCallback(this._getCurrentDate());
	},
	
	_createEventDrag : function(event,column) {
		$(event).draggable({
			grid: [200,20],
			containment : column,
			start : $.proxy(this._setDrag, this),
			stop : $.proxy(this._updateEventCallback, this)
		});
	},
	
	_setDrag : function(event, ui) {
		this.drag = $(ui.helper[0]);
		this.dragPosition.top =  ui.position.top;
		this.dragPosition.left = ui.position.left;
	},
	
	_updateEventCallback : function(event, ui) {
		var url = this.urlContext + "/services/updateTimeEvent/" + this.drag.find(".id").val() ;
		meeting={};
		meeting.id = this.drag.find(".id").val();
		var position = this._getPosition(this.drag);
		meeting.top = position.top;
		meeting.height = position.height;
		meeting.flat = position.flat;
		$.ajax({
			url : url,
			type: "POST",
			data: JSON.stringify(meeting),
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			success : $.proxy(this._processEvents, this),
			error :  $.proxy(this._processError, this),
		});
	},
	
	_processError: function() {
		this.drag.css({
			top: this.dragPosition.top +"px",
			left: this.dragPosition.left +"px"
		});
		alert("Error al modidicar la hora del evento ");
	},
	_processEvents : function(data) {
		if(!data.error){
			var clazz = this.drag.attr('class').split(" ");
			this.drag.removeClass(clazz[0]);
			this.drag.removeClass(clazz[1]);
			this.drag.removeClass(clazz[2]);		
			this._ordenarCeldaActual(this.drag, clazz[0]);
			this.drag.addClass(clazz[0]);
			this.drag.addClass(clazz[1]);
			this.drag.addClass(clazz[2]);
			this._ordenarCeldas($("div."+clazz[0]));
			alert("El horario se modifico correctamente");
		} else {
			alert(data.message);
			this.drag.css({
				top: this.dragPosition.top +"px",
				left: this.dragPosition.left +"px"
			});
		}
	},
	
	_bindEvents : function() {
		this.element.find("#btn-next").click($.proxy(this._loadNextCalendar,this));
		this.element.find("#btn-previous").click($.proxy(this._loadPreviuosCalendar,this));
	},
	
	_getCurrentDate : function (){
		var date = new Date();
		var formCalendar = {};
		formCalendar.date= date.getDate().toString() +'/' +  (date.getMonth() + 1).toString() + '/' + date.getFullYear().toString() ;
		formCalendar.actualPage = 0;
		return formCalendar;
	},
	
	_loadNextCalendar: function() {
		var formCalendar = {};
		formCalendar.date = this.week[6];
		formCalendar.actualPage = 1;
		this._loadEventsCallback(formCalendar);
	},
	
	_loadPreviuosCalendar : function() {
		var formCalendar = {};
		formCalendar.date = this.week[0];
		formCalendar.actualPage = -1;
		this._loadEventsCallback(formCalendar);
	},
	
	_loadEventsCallback: function(formCalendar) {
		var url = this.urlContext + "/services/getEvents";
		$.ajax({
			url : url,
			type: "POST",
			data: JSON.stringify(formCalendar),
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			success : $.proxy(this._loadEvents, this),
			error : function() {
				alert("Error al obtener los eventos");
			}
		});
	},
	
	_loadEvents : function(data) {
		if(!data.error){
			this._loadThead(data.week);
			this._loadBody(data);
		} else{
			alert(data.message);
		}
	},
	
	_loadThead: function(week) {
		 this.week = week;
		 this.table.find("#sundayId").html('<div>'+this.week[0]+'</div>');
		 this.table.find("#mondayId").html('<div>'+this.week[1]+'</div>');
		 this.table.find("#tuesdayId").html('<div>'+this.week[2]+'</div>');
		 this.table.find("#wednesdayId").html('<div>'+this.week[3]+'</div>');
		 this.table.find("#thursdayId").html('<div>'+this.week[4]+'</div>');
		 this.table.find("#fridayId").html('<div>'+this.week[5]+'</div>');
		 this.table.find("#saturdayId").html('<div>'+this.week[5]+'</div>');
	},
	
	_loadBody: function(data) {
		for(var i = 0; i < data.eventsSunday.length; i++){
			var event = this._createEventDiv(data.eventsSunday[i], 0);
			$(event).appendTo(this.element.find('.columnSunday'));
		}
		this._createEventDrag(".eventsSunday", ".columnSunday");
		this._ordenarCeldas($("div.eventsSunday"));
		
		
		for(var i = 0; i < data.eventsMonday.length; i++){
			var event = this._createEventDiv(data.eventsMonday[i], 1);
			$(event).appendTo(this.element.find('.columnMonday'));
		}
		this._createEventDrag(".eventsMonday", ".columnMonday");
		this._ordenarCeldas($("div.eventsMonday"));
		
		for(var i = 0; i < data.eventsTuesday.length; i++){
			var event = this._createEventDiv(data.eventsTuesday[i], 2);
			$(event).appendTo(this.element.find('.columnTuesday'));
		}
		this._createEventDrag(".eventsTuesday", ".columnTuesday");
		this._ordenarCeldas($("div.eventsTuesday"));
		
		for(var i = 0; i < data.eventsWednesday.length; i++){
			var event = this._createEventDiv(data.eventsWednesday[i], 3);
			$(event).appendTo(this.element.find('.columnWednesday'));
		}
		this._createEventDrag(".eventsWednesday", ".columnWednesday");
		this._ordenarCeldas($("div.eventsWednesday"));
		
		for(var i = 0; i < data.eventsThursday.length; i++){
			var event = this._createEventDiv(data.eventsThursday[i], 4);
			$(event).appendTo(this.element.find('.columnThursday'));
		}
		this._createEventDrag(".eventsTuesday" , ".columnTuesday");
		this._ordenarCeldas($("div.eventsTuesday"));
		
		for(var i = 0; i < data.eventsFriday.length; i++){
			var event = this._createEventDiv(data.eventsFriday[i], 5);
			$(event).appendTo(this.element.find('.columnFriday'));
		}
		this._createEventDrag(".eventsFriday", ".columnFriday");
		this._ordenarCeldas($("div.eventsFriday"));
		
		for(var i = 0; i < data.eventsSaturday.length; i++){
			var event = this._createEventDiv(data.eventsSaturday[i], 6);
			$(event).appendTo(this.element.find('.columnSaturday'));
		}
		this._createEventDrag(".eventsSaturday", ".columnSaturda");
		this._ordenarCeldas($("div.eventsSaturday"));
	},
	
	_createEventDiv : function(event, day) {
		
		if(day == 0){
			$div = $('<div class="eventsSunday"></div>');
		}
		if(day == 1){
			$div = $('<div class="eventsMonday"></div>');
		}
		if(day == 2){
			$div = $('<div class="eventsTuesday"></div>');
		}
		if(day == 3){
			$div = $('<div class="eventsWednesday"></div>');
		}
		if(day == 4){
			$div = $('<div class="eventsThursday"></div>');
		}
		if(day == 5){
			$div = $('<div class="eventsFriday"></div>');
		}
		if(day == 6){
			$div = $('<div class="eventsSaturday"></div>');
		}
		
		var url="";
		
		if(event.tipo == "meeting"){
			url = this.urlContext + "/detailMeeting.htm?id=" +  event.id;
		}
		
		if(event.tipo == "privateEvent"){
			url = this.urlContext + "/detailPrivateEvent.htm?id=" +  event.id;
		}
		
		texto = '<a href="' + url + '">' + event.name + '</a>' + '<br/>' + event.startTime + ' ' + event.endTime ;
		$div.html(texto);
		$inputIndex = $('<input type="hidden" class="index">');
		$inputIndex.val(event.index);
		$($inputIndex).appendTo($div);
		$inputId = $('<input type="hidden" class="id">');
		$inputId.val(event.id);
		$($inputId).appendTo($div);
		$inputDate = $('<input type="hidden" class="date">');
		$inputDate.val(event.date);
		$($inputDate).appendTo($div);
		$div.css({
			top: event.top +"px",
			height: event.height+"px"
		});
		
		return $div;
	},
	
	_ordenarCeldas : function(events) {
			var matriz = new Array(48);
			for(var i = 0; i < matriz.length; i++){
				matriz[i] = new Array();
			}
			
			for(var i = 0; i < 48; i++){
				array = matriz[i];
				var top = this._obtenerTop(i);
				for(var j = 0; j< events.length; j++){
					var evento = $(events[j]);
					var positionEvent = this._getPosition(evento);
					if(positionEvent.top == top || (top > positionEvent.top && top < positionEvent.flat) ){
						array.push(evento); 
					}
				}
				if(array.length > 0){
					this._ordenarCeldaByIndex(array, top);
				}
			}
	},
	
	_obtenerTop: function (index) {
		var top = 0;
		for(var i = 0; i < 48; i++){
			if(i == index){
				return top;	
			}
			top+=20;
		}
	},
	
	_ordenarCeldaByIndex : function(array, top) {
		array.sort(function(argA, argB) {
			var a = $(argA);
			var b = $(argB);
			return parseInt(a.find(".index").val())
					- parseInt(b.find(".index").val());
		});
		this._desplazar(this._ordenarArray(array, top), top);
	},
	
	_ordenarArray: function(events, top) {
		var array = new Array();
		for(var i = 0; i < events.length; i++){
			var evento = $(events[i]);
			var positionEvent = this._getPosition(evento);
			if(array.length > 0){
				if(top != positionEvent.top){
					if(!array[evento.find(".index").val()]){
						array[evento.find(".index").val()] = evento;
					} else {// si la posicion del array2 i ya se encuentra ocupada igualmente la reemplazo por el evento de mayor prioridad y desplazo a los demas en 1 
						// logica para cuando tengo 2 eventos con el mismo indice, pero uno tiene mayor prioridad
						var arrayAux = new Array();
					    var aux = 0;
						for(var index = parseInt(evento.find(".index").val()); index < array.length; index++, aux++){
							arrayAux[aux] = array[index];
						}
						array[evento.find(".index").val()] = evento;
						arrayAux = this._ordenarArray(arrayAux, top);
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
					array[evento.find(".index").val()] = evento;
				} else {
					array[0] = evento;
				}
			}
		}
		return array;
	},
	
	_desplazar : function (array, top) {
		for (var i = 0; i < array.length; i++) {
			if(array[i])
			{
				evento = $(array[i]);
				positionEvent = this._getPosition(evento);
				if(positionEvent.top == top){
					evento.find(".index").val(i);
					var index = i + 1;
					var left = this._obtenerDesplazamiento(index);
					evento.css({
						zIndex : index,
						left : left + "px"
					});	
				}	

			}
		}
	},
	
	_ordenarCeldaActual : function (drag, clazz) {
		var events = $('div.'+clazz);
		var array = new Array();
		var positionDrag = this._getPosition(drag);
		for (var i = 0; i < events.length; i++) {
			var evento = $(events[i]);
			positionEvent = this._getPosition(evento);
			if ((positionEvent.top == positionDrag.top || this._existeColisionCeldaActual(positionEvent, positionDrag)) && this._existeIndice(array, evento.find(".indice").val())) {
				if(positionEvent.top != positionDrag.top){
					array[evento.find(".index").val()] = evento;
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
		var index = this._getIndexLibre(array);
		drag.find(".index").val(index);
		index = index + 1;
		var left = this._obtenerDesplazamiento(index);
		drag.css({
			left : left + "px",
			zIndex : index,
		});
		drag.find(".top").val(positionDrag.top);
	},
	
	_getPosition : function (object){
		var pos = 0;
		var position = {};
		pos = object.css("top").indexOf("px");
		position.top = parseInt(object.css("top").substring(0,pos));
		pos = object.css("height").indexOf("px");
		position.height = parseInt(object.css("height").substring(0,pos));
		position.flat = position.top + position.height;
		return position;
	},
	
	_existeIndice : function (array, index){
		for(var i = 0; i < array.length; i++){
			if(array[i]){
				if(array[i].find(".index").val() == index){
					return false;
				}
			}
		}
		return true;
	},
	
	_existeColisionCeldaActual : function (positionEvent, positionDrag) {
		if(positionEvent.height > positionDrag.height){
			return  positionEvent.top < positionDrag.top && positionEvent.flat > positionDrag.flat;
		} else {
			return   positionEvent.top > positionDrag.top && positionEvent.flat < positionDrag.flat;
		}
	},

	_getIndexLibre : function (array) {
		var index = 0;
		for (var i = 0; i < array.length; i++) {
			if (!array[i]) {
				index = i;
				return index;
			}
		}
		return i;
	},

	_obtenerDesplazamiento : function (index) {
		var left;
		left = -20;
		for (var i = 0; i < index; i++) {
			left = left + 20;
		}
		return left;
	},
	
	destroy : function() {
		for ( var name in this.calls) {
			this.calls[name].abort();
			this.calls[name] = null;
		}

		if (typeof (CollectGarbage) == "function") {
			CollectGarbage();
		}
	}

});
