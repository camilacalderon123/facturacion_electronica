<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Custom fonts for this template -->
<link href="<%=request.getContextPath()%>/Dashboard/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Dashboard/img/soltec.png" />
    <link href="<%=request.getContextPath()%>/Dashboard/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Dashboard/css/emitir-factura.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Dashboard/css/ver-clientes.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Dashboard/css/inicio.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Dashboard/css/registrar-empresa.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Dashboard/css/factura.css">

    <!-- Custom styles for this page -->
    <link href="<%=request.getContextPath()%>/Dashboard/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<title>Total Factura</title>
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


				<!-- Content fluid -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="title_client">Emitir factura</h1>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<form action="#">
								<!--Inicio tabla de agregar los productos-->
								<div class="table-responsive">
									<table #dataTable class="table table-bordered" id="tablaprueba"
										width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>Código</th>
												<th>Nombre</th>
												<th>U/M</th>
												<th>Vr. Unit.</th>
												<th>% Dcto.</th>
												<th>IVA</th>
										</thead>
										<tbody>
											<tr>
												<td><select class="select_opciones">
														<option disabled selected>Buscar...</option>
														<option>A50</option>
												</select></td>
												<td>AA</td>
												<td>AA</td>
												<td>AA</td>
												<td>AA</td>
												<td>AA</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--Fin tabla de agregar los productos-->
								<div class="input-box">
									<span class="details">Subtotal</span> <input
										style="margin-left: 13px;" type="text" disabled>
								</div>
								<div class="input-box">
									<span class="details">Total descuento</span> <input
										style="margin-left: 13px;" type="text" disabled>
								</div>
								<div class="input-box">
									<span class="details">Valor neto</span> <input
										style="margin-left: 13px;" type="text" disabled>
								</div>
								<hr>
								<div class="input-box">
									<span class="details">Total</span> <input
										style="margin-left: 13px;" type="text" disabled>
								</div>
							</form>
						</div>
					</div>

				</div>

				<!-- /.container-fluid -->


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
								<h5 class="modal-title" id="exampleModalLabel">¿Cerrar
									sesión?</h5>
								<button class="close" type="button" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
							</div>
							<div class="modal-body">Seleccione "Salir" si está listo
								para cerrar su sesión.</div>
							<div class="modal-footer">
								<button class="btn btn-secondary" type="button"
									data-dismiss="modal">Cancelar</button>
								<a class="btn btn-primary" href="login.html">Salir</a>
							</div>
						</div>
					</div>
				</div>

				<!--Tabala del emitir-->
 <script src="<%=request.getContextPath()%>/Dashboard/js/tabla.js"></script>
    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/Dashboard/vendor/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/Dashboard/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/Dashboard/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/Dashboard/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="<%=request.getContextPath()%>/Dashboard/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="<%=request.getContextPath()%>/Dashboard/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="<%=request.getContextPath()%>/Dashboard/js/demo/datatables-demo.js"></script>
</body>
</html>