<%@page import="co.edu.ufps.facturacion.entities.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Productos</title>

<jsp:include page="cssVistas.jsp" />
</head>

<body id="page-top">
<%
								Empresa e = request.getSession().getAttribute("empresa") != null
									? (Empresa) request.getSession().getAttribute("empresa")
									: null;
								if (e != null) {
									response.sendRedirect(request.getContextPath() + "/inicio");
								}
							%>
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="navMenu.jsp" />
		<!-- End of Sidebar ---------------------------------------------------------------------->

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
								placeholder="Search for..." aria-label="Search"
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
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>

						<!-- Nav Item - Alerts -->

						<!-- Dropdown - Alerts -->

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"> 
						<jsp:include page="imgUsuario.jsp" /><!-- Dropdown - User Information -->
						</li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">


					<!-- Begin Page Content -->
					<div class="container-fluid">

						<!-- Page Heading -->
						<h1 class="title_client">Productos</h1>
						<!-- DataTales Example -->
						<div class="card shadow mb-4">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%"
										cellspacing="0">
										<thead>
											<tr>
												<th>ID producto</th>
												<th>Nombre</th>
												<th>Descripci�n</th>
												<th>U/M</th>
												<th>Valor unitario</th>
												<th>IVA</th>
												<th>Editar</th>
												<th>Eliminar</th>
											</tr>
										</thead>
										<tbody>

											<%
												List<Producto> productos = request.getAttribute("productos") == null ? null
													: (List<Producto>) request.getAttribute("productos");

											if (productos != null) {
												for (Producto p : productos) {
													if (p.getEstado() == 1) {
											%>
											<tr>
												<td><%=p.getCodigo()%></td>
												<td><%=p.getNombre()%></td>
												<td><%=p.getDescripcion()%></td>
												<td><%=p.getUnidadMedia()%></td>
												<td><%=p.getValorUnitario()%></td>
												<td><%=p.getIva()%>%</td>


												<td>
													<a class="btn boton-accion" href="<%=request.getContextPath()%>/inicio/producto/editar?codigo=<%=p.getCodigo()%>">
														<i class="fas fa-pencil-alt"></i>
													</a>
												</td>
												<td>
													<a class="btn boton-accion" href="<%=request.getContextPath()%>/inicio/producto/eliminar/validar?codigo=<%=p.getCodigo()%>">
														<i class="far fa-trash-alt"></i>
													</a>
												</td>
											</tr>
											<%
												}
											}
											}
											%>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>

					<!-- Scroll to Top Button-->
					<a class="scroll-to-top rounded" href="#page-top"> <i
						class="fas fa-angle-up"></i>
					</a>

					<!-- Logout Modal-->
					<div class="modal fade" id="logoutModal" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Ready to
										Leave?</h5>
									<button class="close" type="button" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">�</span>
									</button>
								</div>
								<div class="modal-body">Select "Logout" below if you are
									ready to end your current session.</div>
								<div class="modal-footer">
									<button class="btn btn-secondary" type="button"
										data-dismiss="modal">Cancel</button>
									<a class="btn btn-primary" href="login.html">Logout</a>
								</div>
							</div>
						</div>
					</div>

					<jsp:include page="scriptsVistas.jsp" />
</body>

</html>