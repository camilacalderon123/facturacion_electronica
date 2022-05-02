<%@page import="co.edu.ufps.facturacion.entities.*"%>
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

<title>Productos</title>
<jsp:include page="cssVistas.jsp" />

</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="navMenu.jsp" />
		<!-- End of Sidebar -->


		<!-- Content Wrapper -->
		<div class="d-flex flex-column" id="content-wrapper">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button class="btn btn-link d-md-none rounded-circle mr-3"
						id="sidebarToggleTop">
						<i class="fa fa-bars"> </i>
					</button>
					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">
							<input aria-describedby="basic-addon2" aria-label="Search"
								class="form-control bg-light border-0 small"
								placeholder="Buscar..." type="text">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">
									<i class="fas fa-search fa-sm"> </i>
								</button>
							</div>
							</input>
						</div>
					</form>
					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							aria-expanded="false" aria-haspopup="true"
							class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
							id="searchDropdown" role="button"> <i
								class="fas fa-search fa-fw"> </i>
						</a> <!-- Dropdown - Messages -->
							<div aria-labelledby="searchDropdown"
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input aria-describedby="basic-addon2" aria-label="Search"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." type="text">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"> </i>
											</button>
										</div>
										</input>
									</div>
								</form>
							</div></li>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><jsp:include
								page="imgUsuario.jsp" /><!-- Dropdown - User Information --></li>
					</ul>
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="title_client">Agregar producto</h1>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<%
								Empresa e = request.getSession().getAttribute("empresa") != null
									? (Empresa) request.getSession().getAttribute("empresa")
									: null;
								if (e == null) {
									response.sendRedirect(request.getContextPath() + "/inicio");
								}
							%>

							<form
								action="<%=request.getContextPath()%>/inicio/producto/agregar/validar"
								method="post">
								<div class="user-details">
									<div class="input-box">
										<span class="details"> ID producto </span> <input
											type="number" name="codigo" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Nombre </span> <input name="nombre"
											type="text" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Descripción </span> <input
											name="descripcion" type="text" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Unidad de medida </span> <input
											name="unidadMedida" type="text" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Valor unitario </span> <input
											name="valorUnitario" type="number" mrequired>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> IVA </span> <input name="iva"
											type="number" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> % Descuento </span> <input
											name="descuento" type="number" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Estado </span> <input name="estado"
											type="number" required>
									</div>
								</div>
								<div class="button">
									<button class="button_style">Guardar</button>
								</div>

								<div class="button2">
									<button class="button_style"
										href="<%=request.getContextPath()%>/inicio/producto/ver">Cancelar</button>
								</div>
							</form>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->


				<!-- Scroll to Top Button-->
				<a class="scroll-to-top rounded" href="#page-top"> <i
					class="fas fa-angle-up"> </i>
				</a>
				<!-- Logout Modal-->
				<div aria-hidden="true" aria-labelledby="exampleModalLabel"
					class="modal fade" id="logoutModal" role="dialog" tabindex="-1">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">¿Cerrar
									sesión?</h5>
								<button aria-label="Close" class="close" data-dismiss="modal"
									type="button">
									<span aria-hidden="true"> × </span>
								</button>
							</div>
							<div class="modal-body">Seleccione "Salir" si está listo
								para cerrar su sesión.</div>
							<div class="modal-footer">
								<button class="btn btn-secondary" data-dismiss="modal"
									type="button">Cancelar</button>
								<a class="btn btn-primary" href="login.html"> Salir </a>
							</div>
						</div>
					</div>
				</div>
				<!-- Bootstrap core JavaScript-->
				<jsp:include page="scriptsVistas.jsp" />
</body>
</html>