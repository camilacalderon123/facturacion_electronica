<%@page import="co.edu.ufps.facturacion.entities.*"%>
<%@page import="co.edu.ufps.facturacion.dao.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Custom fonts for this template-->

<title>Datos fiscales</title>

<jsp:include page="cssVistas.jsp" />
<script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jQuery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#municipio').prop('disabled', 'disabled');
		
		function loadJSON(callback) {
			var xobj = new XMLHttpRequest();
			xobj.overrideMimeType("application/json");
			xobj.open("GET", "https://raw.githubusercontent.com/marcovega/colombia-json/master/colombia.min.json", true); // Reemplaza colombia-json.json con el nombre que le hayas puesto
			xobj.onreadystatechange = function () {
				if (xobj.readyState == 4 && xobj.status == "200") {
					callback(xobj.responseText);
				}
			};
			xobj.send(null);
		}
		function llenarDepartamentos() {
			loadJSON(function (response) {
				// Parse JSON string into object
				
				var datos = JSON.parse(response);
				$('#departamento').empty();
				for(var i in datos){
					$('#departamento').prepend("<option value='"+datos[(datos.length-1)-i].departamento+"' >"+datos[(datos.length-1)-i].departamento+"</option>");
				}
				$('#departamento').prepend("<option value='' selected>Seleccione departamento</option>");
				$('#municipio').prop('disabled', 'disabled');
			});
		}
		
		$('#departamento').one("click", function() {
			llenarDepartamentos();
		});
		
		
		
		$('#departamento').change(function() {
			cambiarMunicipio(this, '#municipio');
		});
		
		function cambiarMunicipio(combo1, combo2) {
			combo2 = $(combo2);
			limpiarCombo(combo2);
			combo2.disabled = true;
			llenarMunicipios(combo1.selectedIndex);
			combo1.disabled =false;
			combo2.disabled =false;
		}
		
		function limpiarCombo(combo){
			$('#municipio').empty();
			$('#municipio').prepend("<option value='' selected>Seleccione municipio</option>");
			$('#municipio').prop('disabled', false);
		}
		
		function llenarMunicipios(id) {
			loadJSON(function (response) {
				var datos = JSON.parse(response);
				$('#municipio').empty();
				for(var i=datos[id-1].ciudades.length-1; i>=0;i--){
					$('#municipio').prepend("<option value='"+datos[(id-1)].ciudades[i]+"' >"+datos[(id-1)].ciudades[i]+"</option>");
				}
				$('#municipio').prepend("<option value='' selected>Seleccione municipio</option>");
			});
		}
		
		$('#contribuyente').one("click", function() {
			$('#contribuyente').empty();
			$('#contribuyente').prepend("<option>Persona Natural</option>");
			$('#contribuyente').prepend("<option>Persona Jurídica</option>");
			$('#contribuyente').prepend("<option>Elija tipo</option>");
		});
		
		$('#regimen').one("click", function() {
			$('#regimen').empty();
			$('#regimen').prepend("<option>Simplificado</option>");
			$('#regimen').prepend("<option>Común</option>");
			$('#regimen').prepend("<option>Elija tipo</option>");
		});
});
</script>
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="navMenu.jsp" />
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">
							<input type="text" class="form-control bg-light border-0 small"
								placeholder="Buscar" aria-label="Search"
								aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">
									<i class="fas fa-search fa-sm"></i>
								</button>
							</div>
						</div>
					</form>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Buscar" aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>


						<!-- Dropdown - Alerts -->

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><jsp:include
								page="imgUsuario.jsp" /><!-- Dropdown - User Information --></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4 titulo-container">
						<h1 class="h3 heading-section titulo">Información de la
							empresa</h1>
					</div>

					<!--CARDS -->
					<div class="container">
						<form
							action="<%=request.getContextPath()%>/inicio/empresa/agregar/validar"
							method="post">
							<div class="user-details">
								<%
									Empresa e = (Empresa) request.getAttribute("em");
								if(e!=null){
								%>

								<div class="input-box">
									<span class="details">Nit empresa</span> <input type="number"
										placeholder="NIT" name="nit"  value="<%=e.getNit()%>" readonly>
								</div>
								<div class="input-box">
									<span class="details">Razón social empresa</span> <input
										type="text" placeholder="Razón social" name="razonSocial" value="<%=e.getRazonSocial()%>" 
										required>
								</div>
								<div class="input-box">
									<span class="details">Dirección de la empresa</span> <input
										type="text" placeholder="Dirección" name="direccion" value="<%=e.getDireccion()%>"  required>
								</div>
								<div class="input-box">
									<span class="details">Correo empresa</span> <input type="email"
										placeholder="Ingrese el correo" name="correo" value="<%=e.getCorreoEmpresa()%>"  required>
								</div>
								<div class="input-box">
									<span class="details">Teléfono de la empresa</span> <input
										type="number" placeholder="Ingrese teléfono" value="<%=e.getTelefono()%>"  name="telefono"
										required>
								</div>
								<div class="input-box">
										<span class="details">Departamento</span> <select
											name="departamento" id="departamento">
											<option value="<%=e.getDepartamento()%>"><%=e.getDepartamento()%></option>
										</select>
									</div>
									<div class="input-box">
										<span class="details">Municipio/ciudad</span> <select 
											name="municipio" id="municipio" >
											<option value="<%=e.getMunicipio()%>"><%=e.getMunicipio()%></option>
										</select>
									</div>
								<div class="input-box">
										<span class="details">Tipo de documento</span> <select
											name="tipoDocumento" disabled>
											<option value="<%=e.getTipoDocumentoBean().getIdTipoDocumento()%>"><%=e.getTipoDocumentoBean().getIdTipoDocumento()%>. <%=e.getTipoDocumentoBean().getDescripcion()%></option>
										</select>

									</div>
								<div class="input-box">
									<span class="details">Número documento</span> <input
										type="number" value="<%=e.getNumeroDocumento()%>" 
										name="documento" required>
								</div>
								<div class="input-box">
									<span class="details">Nombre representante legal</span> <input
										type="text" value="<%=e.getNombreRepresentante()%>" 
										name="nombreRepresentante" required>
								</div>
							</div>
							<%}else{
								response.sendRedirect(request.getContextPath() + "/inicio");
							} %>
							<div class="button">
								<button class="button_style">Guardar</button>
							</div>
							
							<div class="button2">
								<button class="button_style"
									href="<%=request.getContextPath()%>/inicio">Cancelar</button>
							</div>
						</form>
					</div>
					<!-- /.container-fluid -->

					<!-- End of Content Wrapper -->

					<!-- End of Page Wrapper -->

					<!-- Scroll to Top Button-->
					<a class="scroll-to-top rounded" href="#page-top"> <i
						class="fas fa-angle-up"></i>
					</a>

					<!-- Bootstrap core JavaScript-->
					<jsp:include page="scriptsVistas.jsp" />
</body>
</html>