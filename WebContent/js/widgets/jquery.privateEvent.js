$.widget('custom.applyPrivateEvent', {
	options : {
		urlContext : ""
	},

	_create : function() {
		this._bindVars();
		this._initialize();
		this._bindEvents();
	},

	_bindVars : function() {
		this.urlContext = this.options.urlContext;
		this.date = this.element.find("#date");
	},

	_initialize : function() {
		this._createDatepicker();
	},

	_bindEvents : function() {
	},

	_createDatepicker : function() {
		$( "#datepicker" ).datepicker({
			dateFormat: 'dd/mm/yy'
		});
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
