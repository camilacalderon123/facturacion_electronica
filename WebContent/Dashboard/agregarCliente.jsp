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

<title>Clientes</title>

<jsp:include page="cssVistas.jsp" />
<script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jQuery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
		llenarDepartamentos();
		
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
				for(var i in datos){
					$('#departamento').prepend("<option value='"+datos[(datos.length-1)-i].departamento+"' >"+datos[(datos.length-1)-i].departamento+"</option>");
				}
				$('#departamento').prepend("<option value='' selected>Seleccione departamento</option>");
				$('#municipio').prop('disabled', 'disabled');
			});
		}
		
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
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>

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
											placeholder="Buscar" aria-label="Buscar"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>



						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"> 
						<jsp:include page="imgUsuario.jsp" /><!-- Dropdown - User Information -->
						</li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="title_client">Agregar clientes</h1>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<form
								action="<%=request.getContextPath()%>/inicio/cliente/agregar/validar"
								method="post">
								<div class="user-details">

									<%
										TipoDocumentoDAO tDAO = new TipoDocumentoDAO();

									List<TipoDocumento> tipos = tDAO.list();
									%>
									<div class="input-box">
										<span class="details">Tipo de documento</span> <select
											name="tipoDocumento">
											<%
												for (TipoDocumento t : tipos) {
											%>
											<option value="<%=t.getIdTipoDocumento()%>"><%=t.getIdTipoDocumento()%>. <%=t.getDescripcion()%></option>
											<%
												}
											%>
										</select>

									</div>
									<div class="input-box">
										<span class="details">Número de documento</span> <input
											type="number" name="documento" required>
									</div>
									<div class="input-box">
										<span class="details">Nombre comercial</span> <input
											type="text" name="nombreComercial" required>
									</div>
									<div class="input-box">
										<span class="details">Nombre </span> <input type="text"
											name="nombre" required>
									</div>
									<div class="input-box">
										<span class="details">Dirección</span> <input type="text"
											name="direccion" required>
									</div>
									<div class="input-box">
										<span class="details">País</span> <select name="pais">
											<option value="Colombia">Colombia</option>
										</select>
									</div>
									<div class="input-box">
										<span class="details">Departamento</span> <select
											name="departamento" id="departamento">
										</select>
									</div>
									<div class="input-box">
										<span class="details">Municipio/ciudad</span> <select 
											name="municipio" id="municipio" >
											<option>Seleccione municipio</option>
										</select>
									</div>
									<div class="input-box">
										<span class="details">Correo</span> <input type="email"
											name="correo" required>
									</div>
									<div class="input-box">
										<span class="details">Teléfono</span> <input type="number"
											name="telefono" required>
									</div>
									<div class="input-box">
										<span class="details">Contribuyente</span> <select name="contribuyente">
											<option>Elija tipo</option>
											<option>Persona Natural</option>
											<option>Persona Jurídica</option>
										</select>
									</div>
									<div class="input-box">
										<span class="details">Régimen contable</span> <select name="regimen">
											<option>Elija tipo</option>
											<option>Común</option>
											<option>Simplificado</option>
										</select>
									</div>
								</div>
								<div class="button">
									<button class="button_style">Guardar</button>
								</div>

								<div class="button2">
									<button class="button_style"
										href="<%=request.getContextPath()%>/inicio/cliente/ver">Cancelar</button>
								</div>
							</form>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white"> </footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary"
						href="<%=request.getContextPath()%>/inicio">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<jsp:include page="scriptsVistas.jsp" />

</body>

</html>