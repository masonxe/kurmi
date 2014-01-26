<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:include page="toolbar-aplicaciones.jsp" />

<br />
<br />
<table align="center">
    <tr>
        <td>
            <table id="tbl-aplicaciones" ></table>
            <div id="tbl-aplicaciones-pager"></div>
        </td>
    </tr>
</table>
<br />
<br />


<script type="text/javascript">
    //TOOLBAR
    $("#btn_add_app").click(function(){
        jQuery("#tbl-aplicaciones").jqGrid('editGridRow',"new",{
            modal:true,
            width:350,
            addCaption: "Agregar Aplicación",
            closeAfterAdd : true,
            beforeSubmit : function(postdata, formid) {
                var password = postdata.usuarioPassword;
                var repeat = postdata.passwordValid;
                if(password != repeat){
                    return[false,"Las contraseñas no son iguales"];
                }
                return[true,""];
            },
            afterSubmit : function(response, postdata){              
                var json=response.responseText; //in my case response text form server is "{sc:true,msg:''}"
                var result=eval("("+json+")"); //create js object from server reponse
                if(result.mensaje!=""){
                    return [false,result.mensaje,null];
                }
                return [true,""];
            }
        });
    });
    $("#btn_edit_app").click(function(){
        var gr = jQuery("#tbl-aplicaciones").jqGrid('getGridParam','selrow');
        if( gr != null ) {
            jQuery("#tbl-aplicaciones").jqGrid('editGridRow',gr,{
                editCaption: "Actualizar Aplicación",
                closeAfterEdit  : true,
                bSubmit:"Actualizar",
                beforeSubmit : function(postdata, formid) {
                    var password = postdata.usuarioPassword;
                    var repeat = postdata.passwordValid;
                    if(password != repeat){
                        return[false,"Las contraseñas no son iguales"];
                    }
                    return[true,""];
                },
                afterSubmit : function(response, postdata){
                    var json=response.responseText; //in my case response text form server is "{sc:true,msg:''}"
                    var result=eval("("+json+")"); //create js object from server reponse
                    if(result.mensaje!=""){
                        return [false,result.mensaje,null];
                    }
                    return [true,""];
                },
                width:350
            });

        }else{
            jAlert(SELECCIONE_REGISTRO, 'Mensaje');
        }

    });

    $("#btn_vigencia_app").click(function(){
        var gr = jQuery("#tbl-aplicaciones").jqGrid('getGridParam','selrow');
        if( gr != null ){
            jQuery("#tbl-aplicaciones").jqGrid('delGridRow',gr,{
                msg: CONFIRMAR_ACT_VIGENCIA,
                caption: "Mensaje",
                bSubmit:"Actualizar"
            });

        }else{
            jAlert(SELECCIONE_REGISTRO,'Mensaje');
        }
    });

    $("#btn_del_app").click(function(){
        var gr = jQuery("#tbl-aplicaciones").jqGrid('getGridParam','selrow');
        if( gr != null ){
            jConfirm(CONFIRMAR_ELIMINAR, 'Mensaje', function(r) {
                if(r){
                    $.get("./gestionAplicaciones.do", {
                        metodo: "mantenimientoAplicaciones",
                        oper: "elim",
                        idAplicacion : gr,
                        userLogin: $('#userLogin').html()
                    },
                    function(){
                        load_tbl_aplicaciones('./gestionAplicaciones.do?metodo=listarAplicacionesEnFormatoJSON');
                    });
                }
            });

        }else{
            jAlert(SELECCIONE_REGISTRO, 'Mensaje');
        }
    });

</script>

<script type="text/javascript">

    //CREACION DE LA TABLA APLICACIONES
    $(document).ready(function(){
        jQuery("#tbl-aplicaciones").jqGrid({
            url:'./rest/libros',
            datatype:"json",
            colNames:['Id_Libros','Titulo'],
            colModel:[
                {
                    name:'idLibro',
                    index:'idLibro',
                    width:90,
                    align:'center',
                    sortable:true,
                    editable:true,
                    editrules:{
                        edithidden:true,
                        required:false
                    },
                    editoptions: {
                        disabled: true
                    }
                },

                {
                    name:'titulo',
                    index:'titulo',
                    width:160,
                    align:"center",
                    editable:true,
                    editrules:{
                        edithidden:true,
                        required:true
                    }
                }

              
            ],
            rownumbers:true,
            sortname: 'idLibro',
            sortorder: 'desc',
            viewrecords: true,
            modal: true,
            gridview: false,
            height:300,
            pgbuttons:false,
            pginput:false,
            pager: jQuery('#tbl-aplicaciones-pager'),
            shrinkToFit: true,
            forceFit: false,
            editurl:"gestionAplicaciones.do?metodo=mantenimientoAplicaciones&userLogin="+$('#userLogin').html() //The Add/Edit function call
        });

        jQuery("#grilla").jqGrid('navGrid','#paginador',{edit:false,add:false,del:false,refresh :false,search:false});

    })

    //funcion de recarga de grid
    function load_tbl_aplicaciones(url){
        jQuery("#tbl-aplicaciones").jqGrid('setGridParam',{
            url:url,
            datatype:"json"
        }).trigger("reloadGrid");
    }
</script>
