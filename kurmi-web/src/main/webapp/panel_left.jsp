
<script type="text/javascript">

    function addTab(title, url){
        if ($('#tabs').tabs('exists', title)){
            $('#tabs').tabs('select', title);
        } else {
            $('#tabs').tabs('add',{
                title:title,
                href:url,
                closable:true
            });
        }
    }
    
</script>
<li iconCls="icon-candado" state="closed">
    <span>Kurmi</span>
    <ul>
        <li iconCls="icon-push"><span><a href="#" onclick="addTab('Libros Donados','libros/libros.jsp')">Libros Donados</a></span></li>
        <li iconCls="icon-push"><span>Solicitud de Donaciones</span>
            <ul>
                <li iconCls="icon-push"><span><a href="#" onclick="addTab('Opciones por Rol','./gestionRoles.do?metodo=listarAplicacionesYRolesParaCombosDep&id=1')">Nacionales</a></span></li>
                <li iconCls="icon-push"><span><a href="#" onclick="addTab('Valores por Rol','./gestionRoles.do?metodo=listarAplicacionesYRolesParaCombosDep&id=2')">Extranjero</a></span></li>
            </ul>
        </li>
        <li iconCls="icon-push"><span>Transportes</span>
            <ul>
                <li iconCls="icon-push"><span><a href="#" onclick="addTab('Listado de Suplencias','ajax/suplencias/gestion-suplencias.jsp')">Aire</a></span></li>
                <li iconCls="icon-push"><span><a href="#" onclick="addTab('Listado de Suplencias','ajax/suplencias/gestion-suplencias.jsp')">Terrestre</a></span></li>
            </ul>
        </li>
    </ul>
</li>
