$.widget('custom.applyMeeting',
{
	options : {
		urlContext : "",
		guestsNames : {},
		isOwner : false,
		isGuest : false,
		state : ""

	},

	_create : function() {
		this._bindVars();
		this._initialize();
		this._bindEvents();
	},

	_bindVars : function() {
		this.urlContext = this.options.urlContext;
		this.guestsNames = this.options.guestsNames;
		this.isOwner = this.options.isOwner;
		this.isGuest = this.options.isGuest;
		this.state = this.options.state
		this.date = this.element.find("#datepicker");
		this.inputGuest = this.element.find("#inputGuest");
		this.guestsIds = this.element.find("#guestsIds");
		this.ulGuests = this.element.find("#ulGuests");
		this.hiddenGuestId = this.element.find("#hiddenGuestId");
	},

	_initialize : function() {
		this._createDatePicker();
		this._createAtocompletar();
		this._loadGuestNames();
		this._loadGuestsIds();
		this._hideInputs();

	},

	_loadGuestsIds : function() {
		var string = "";
		for (var i = 0; i < this.guestsNames.length; i++) {
			var array = this.guestsNames[i].split(",");
			if (i == (this.guestsNames.length - 1)) {
				string += array[1];
			} else {
				string += array[1] + ",";
			}
		}
		this.guestsIds.val(string);
	},

	_hideInputs : function() {
		debugger;
		if (this.isOwner) {
			this.element.find(".guest").hide();
		}
		if (this.isGuest) {
			this.element.find(".linkDelete").hide();// no se puede deshabilitar un href
			this.element.find(".owner").attr("disabled", "disabled");
			if (this.state == "1" || this.state == "2" ) {
				this.element.find(".guest").attr("disabled", "disabled");
				this.element.find("#btn-edit").attr("disabled", "disabled");
			}
		}
	},

	_bindEvents : function() {
		this.element.find("#btn-addUser").click(
				$.proxy(this._addUser, this));
		this.ulGuests.on('click', '.itemDelete', $.proxy(
				this._removeUser, this));
	},

	_removeUser : function(li) {
		li.toElement.parentElement.parentElement.remove();
		this._loadGuest();
	},

	_addUser : function() {
		$('<li class="ui-state-default li-user" value="' + this.hiddenGuestId.val() + '">' + this.inputGuest.val() + '<a class="itemDelete linkDelete" href="#"><span class="glyphicon glyphicon-trash"/></a><span class="glyphicon glyphicon-exclamation-sign"/></li>')
				.appendTo(this.ulGuests);
		this._loadGuest();
	},

	_loadGuest : function() {
		var string = "";
		var array = this.element.find(".li-user");
		for (var i = 0; i < array.length; i++) {
			if (i == (array.length - 1)) {
				string += array[i].value.toString();
			} else {
				string += array[i].value.toString() + ",";
			}
		}
		this.guestsIds.val(string);
	},

	_createDatePicker : function() {
		this.date.datepicker({
			dateFormat : 'dd/mm/yy'
		});
	},

	_createAtocompletar : function() {
		var url = this.urlContext + "/services/getUsers";
		this.inputGuest.autocomplete({
			minLength : 3,
			source : function(request, response) {
				$.ajax({
					url : url,
					type : "GET",
					data : {
						term : request.term
					},
					dataType : "json",
					success : function(data) {
						response($.map(data.users, function(
								item) {
							return {
								label : item.label,
								value : item.label,
								key : item.value
							};
						}));
					}
				});
			},
			select : $.proxy(this._loadHiddenGuestId, this)
		});
	},

	_loadHiddenGuestId : function(event, ui) {
		this.hiddenGuestId.val(ui.item.key);
	},

	_loadGuestNames : function() {
		for (var i = 0; i < this.guestsNames.length; i++) {
			var guest = this.guestsNames[i].split(",");
			var state = "";
			
			if(guest[2] == "1") {
				state = '<span class="glyphicon glyphicon-ok-circle"/>';
			}
			else if (guest[2] == "2") {
				state = '<span class="glyphicon glyphicon-ban-circle"/>';
			} else if (guest[2] == "3") {
				state = '<span class="glyphicon glyphicon-exclamation-sign"/>';
			}
			
			$('<li class="ui-state-default li-user" value=' + guest[1] + '>' + guest[0]
				+ ' <a class="itemDelete linkDelete" href="#"><span class="glyphicon glyphicon-trash"/></a>' + state + '</li>')
				.appendTo(this.ulGuests);
		}
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
