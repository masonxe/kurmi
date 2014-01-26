<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript">
<%String cerrar = request.getParameter("cerrar");%>
<%if(cerrar != null){%>
    fn_cerrar_session('<%=cerrar%>');
<%}%>
    function fn_cerrar_session(cerrar){
    	 if(cerrar == '1'){
             parent.frames.location.href = '<%=request.getContextPath()%>/login.do?metodo=logout';
         }else{
             location.href = '<%=request.getContextPath()%>/login.do?metodo=logout';
         }
    }
</script>

<div id="logo_cabecera">
    <div id="logo_cabecera_aplicacion" style="float: left; text-align: left; margin-top:2px;margin-left: 5px;">
        <span style="color: #0D386F; font-size: 18px; font-weight: bold">Kurmi</span><br />
        <span style="color: white; font-size: 10px;"><c:out value="${servidorBD}" /></span>
        
    </div>
    <div id="logo_cabecera_session">
        <img style="width: 16px;height: 10px; padding-right: 2px;" src="images/cabecera/user_add.png" alt="bandera"/>
        <span>Bienvenido, <span id="userLogin"><c:out value="${session_usuario.nombre}" /></span></span><br />
        <span><a id="cerrarx" onclick="fn_cerrar_session()" href="#">Cerrar Sesi&oacute;n</a></span>
    </div>
</div>
