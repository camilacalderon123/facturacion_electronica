<%@page import="co.edu.ufps.facturacion.entities.*"%>
<%@page import="java.util.*"%>

<script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jQuery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#departamento').change(function() {
			cambiarMunicipio(this, '#municipio');
		});

	});
</script>

<!--Inicio de la barra rateral -->
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!--Titulo empresa -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="<%=request.getContextPath()%>/inicio"> <!-- <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Empresa</div> -->
		<div class="sidebar-brand-text mx-3">Empresa</div> <img alt="logo"
		height="200%"
		src="<%=request.getContextPath()%>/Dashboard/img/soltec.png">
	</a>

	<!-- Divider -->
	<div class="sidebar-heading">Opciones</div>

	<!-- Opcion inicio -->
	<li class="nav-item"><a class="nav-link"
		href="<%=request.getContextPath()%>/inicio"> <i
			class="fas fa-home"></i> <span>Inicio</span></a></li>
	<hr class="sidebar-divider">

	<!--Opcion de producto -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
		aria-controls="collapseTwo"> <i class="fas fa-box-open"></i> <span>Producto</span>
	</a>

		<div id="collapseTwo" class="collapse show"
			aria-labelledby="headingTwo" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Opciones</h6>



				<a class="collapse-item "
					href="<%=request.getContextPath()%>/inicio/producto/ver">Ver
					productos</a> <a class="collapse-item"
					href="<%=request.getContextPath()%>/inicio/producto/agregar">Agregar
					producto</a>

			</div>
		</div></li>

	<!-- Opcion de cliente-->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseUtilities"
		aria-expanded="true" aria-controls="collapseUtilities"> <i
			class="fas fa-user"></i> <span>Cliente</span>
	</a>
		<div id="collapseUtilities" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Opciones</h6>
				<a class="collapse-item"
					href="<%=request.getContextPath()%>/inicio/cliente/ver">Ver
					clientes</a> <a class="collapse-item"
					href="<%=request.getContextPath()%>/inicio/cliente/agregar">Agregar
					cliente</a>
			</div>
		</div></li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Opcion facturación -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapsePages"
		aria-expanded="true" aria-controls="collapsePages"> <i
			class="fas fa-fw fa-folder"></i> <span>Facturación</span>
	</a>
		<div id="collapsePages" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<h6 class="collapse-header">Opciones</h6>

				<a class="collapse-item"
					href="<%=request.getContextPath()%>/inicio/factura/ver">Ver
					facturas</a> <a class="collapse-item"
					href="<%=request.getContextPath()%>/inicio/factura/agregar">Emitir
					factura</a> <a class="collapse-item"
					href="<%=request.getContextPath()%>/inicio/factura/rango">Rangos
					de numeración</a>
			</div>
		</div></li>
	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>
<!--Fin de la barra rateral -->
