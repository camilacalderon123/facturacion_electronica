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
								placeholder="Search for..." type="text">
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
											placeholder="Buscar" type="text">
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
						<li class="nav-item dropdown no-arrow"> 
						<jsp:include page="imgUsuario.jsp" /><!-- Dropdown - User Information -->
						</li>
					</ul>
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<h1 class="title_client">Editar producto</h1>
					<!-- Page Heading -->
					<div class="card shadow mb-4">
						<div class="container">
							<form action="<%=request.getContextPath()%>/inicio/producto/editar/validar"
								method="post">
								<div class="user-details">
								
								<%Producto p = request.getAttribute("producto")==null?null: (Producto)request.getAttribute("producto");
								
								if(p!=null){
									
								%>
									<div class="input-box">
										<span class="details"> ID producto </span> <input type="hidden"
											name="codigo" value="<%=p.getCodigo()%>">
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Nombre </span> <input type="text"
											name="nombre"value="<%=p.getNombre()%>" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Descripción </span> <input type="text"
											name="descripcion" value="<%=p.getDescripcion()%>" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Unidad de medida </span> <input
											type="text" name="unidadMedida" value="<%=p.getUnidadMedia()%>" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Valor unitario </span> <input
											type="text" name="valorUnitario" value="<%=p.getValorUnitario()%>" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> IVA </span> <input type="text"
											name="iva" value="<%=p.getIva()%>" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> % Descuento </span> <input
											type="number" name="descuento" value="<%=p.getPorcentajeDescuento()%>" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Estado </span> <input type="number"
											name="estado" value="<%=p.getEstado()%>"required>
									</div>
								</div>
								<%}else{
                                	response.sendRedirect(request.getContextPath() + "/inicio/producto/ver");
                                }%>
								<div class="button">
									<button class="button_style">Actualizar</button>
								</div>
								<div class="button2">
									<button class="button_style">Cancelar</button>
								</div>

							</form>
						</div>

						<!-- End of Main Content -->
						<!-- Footer -->
						<footer class="sticky-footer bg-white">
							<div class="container my-auto">
								<div class="copyright text-center my-auto">
									<span> Copyright © Your Website 2020 </span>
								</div>
							</div>
						</footer>
						<!-- End of Footer -->
					</div>
					<!-- End of Content Wrapper -->
				</div>
				<!-- End of Page Wrapper -->
				<!-- Scroll to Top Button-->
				<a class="scroll-to-top rounded" href="#page-top"> <i
					class="fas fa-angle-up"> </i>
				</a>
				<!-- Logout Modal-->
				<!-- Logout Modal-->
				<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">¿Cerrar sesión?</h5>
								<button class="close" type="button" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
							</div>
							<div class="modal-body">Seleccione "Salir" si está listo para cerrar su sesión.</div>
							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<a class="btn btn-primary">Salir</a>
							</div>
						</div>
					</div>
				</div>

				<!-- Bootstrap core JavaScript-->
				<jsp:include page="scriptsVistas.jsp" />
			</div>
		</div>
</body>
</html>