if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'P&aacute;gina';
	$.fn.pagination.defaults.afterPageText = 'de {pages}';
	$.fn.pagination.defaults.displayMsg = 'Mostrando {from} a {to} de {total} items';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = 'Procesando, por favor espere ...';
}
if ($.messager){
	$.messager.defaults.ok = 'Aceptar';
	$.messager.defaults.cancel = 'Cancelar';
}
if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = 'Este campo es requerido.';
	$.fn.validatebox.defaults.rules.email.message = 'Por favor, ingrese una direcci�n de e-mail valida.';
	$.fn.validatebox.defaults.rules.url.message = 'Por favor, ingrese una URL valida .';
	$.fn.validatebox.defaults.rules.length.message = 'Please enter a value between {0} and {1}.';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = 'Este campo es requerido.';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = 'Este campo es requerido.';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = 'Este campo es requerido.';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['D','L','M','M','J','V','S'];
	$.fn.calendar.defaults.months = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = 'Hoy';
	$.fn.datebox.defaults.closeText = 'Cerrar';
	$.fn.datebox.defaults.missingMessage = 'Este campo es requerido.';
}
