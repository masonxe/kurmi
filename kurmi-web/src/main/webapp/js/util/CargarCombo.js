function classAjax() {
    this.loadCmbJson=loadCmbJson;
}
/**
     *Carga un select con los datos de un JSON
     *@param url @type String: Direccion para obtener el JSON
     *@param data @type String: datos de envio
     *@param cmb @type String: Nombre del Objeto Select
     */
function loadCmbJson(url,data,cmb){
    //invoca el metodo obtener Json por Get
    $.getJSON(url,data, function(json){
        //borramos el contenido de los option del select
        $("#"+cmb).html("");
        //recorremos todas las filas del resultado del proceso que obtenemos en Json

        var msjSeleccione = '<option value="0">--- Seleccione ----</option>';
        if(json.resultado.length=="0"){
            $("#"+cmb).append('<option value="0">No se encontraron Roles</option>');
        }else{
            $("#"+cmb).append(msjSeleccione);
            $.each(json.resultado, function(i,item){
                $("#"+cmb).append('<option value="'+item.id+'">'+item.descripcion+'</option>');
            });
        }


    })
}