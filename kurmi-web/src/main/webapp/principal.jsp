<%--
    Document   : principal
    Created on : 20/11/2012, 10:42:18 AM
    Author     : Meison.Chirinos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; utf-8" />
        <link href="css/template.css" rel="stylesheet" type="text/css" />
        <link href="css/toolbar.css" rel="stylesheet" type="text/css" />
        <link href="css/varios.css" rel="stylesheet" type="text/css" />
        <link href="css/mensajes.css" rel="stylesheet" type="text/css" />
        <link href="css/formulario.css" rel="stylesheet" type="text/css" />
        <link href="jquery-easyui/easyui.css" rel="stylesheet" type="text/css" />
        <link href="jquery-easyui/icon.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="jquery-easyui/base/jquery-1.4.4.min.js"></script>
        <script type="text/javascript" src="jquery-easyui/base/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="js/arbol-izquierdo.js"></script>

        <!----- Configuracion de las librerias javascript ---->
        <!-----    Archivos para trabajar con jqgrid      ---->
        <script type="text/javascript" src="js/i18n/grid.locale-sp.js" ></script> <!--Idioma español-->
        <script type="text/javascript">
            jQuery.jgrid.no_legacy_api = true;
            $(document).ready(function() {
                $('#menu_izq').tree();
                $.alerts.overlayColor = "#000";
                $.alerts.overlayOpacity = "0.1";                
            });
        </script>
        <script type="text/javascript" src="js/jqgrid/jquery.jqGrid.min.js"></script><!-- JqGrid -->
        <script type="text/javascript" src="js/jqgrid/grid.subgrid.js" ></script> <!-- SubGrilla-->
        <script type="text/javascript" src="js/jqgrid/grid.common.js"></script>
        <script type="text/javascript" src="js/jqgrid/grid.formedit.js" ></script>
        <script type="text/javascript" src="js/jqgrid/jqModal.js" ></script>
        <script type="text/javascript" src="js/jqgrid/jqDnR.js"></script>
        <!-- Los estilos-->
        <link href="js/jqgrid/ui.jqgrid.css" media="screen" rel="stylesheet" type="text/css"/>
        <link href="js/jqgrid/redmond/jquery-ui-1.8.2.custom.css" media="screen" rel="stylesheet" type="text/css"/>
        <!-- Fin de la configuracion de JQGrid -->

        <!-- JAlert-->
        <script type="text/javascript" src="js/jalert/jquery.alerts.js"></script>
        <link href="js/jalert/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />
        <!--Mensajes -->
        <script type="text/javascript" src="js/mensajes.js"></script>

        <!-- Configuracion de Dialog Jquery-->
        <script src="js/jquery-ui-1.8.9/js/jquery.ui.core.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.9/js/jquery.ui.widget.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.9/js/jquery.ui.mouse.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.9/js/jquery.ui.button.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.9/js/jquery.ui.draggable.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.9/js/jquery.ui.position.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.9/js/jquery.ui.resizable.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.8.9/js/jquery.ui.dialog.js" type="text/javascript"></script>

    </head>

    <body class="easyui-layout">
         <!-- Top - norte -->
        <div region="north" split="false" style="height:42px;background:#A4BED4;padding: 0;">
            <jsp:include page="panel_top.jsp" />
        </div>
       
        <!-- Menu Principal - izquierdo -->
        <div region="west" split="false" title="SCA" style="width:200px;padding-left: 0px;">
            <div class="easyui-accordion" fit="false" border="false">
                <ul id="menu_izq" style="text-align: left">
                    <jsp:include page="panel_left.jsp" />
                </ul>
            </div>
        </div>

        <!--  Region Centro -->
        <div region="center">
            <div id="tabs" class="easyui-tabs" fit="true" border="false"></div>
        </div>

    </body>
</html>
