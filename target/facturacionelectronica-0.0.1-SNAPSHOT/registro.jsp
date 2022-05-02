<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
  	<title>Registarse</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">

	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h1 class="heading-section">
					<span style="color:#0077b6">Factu</span>
					<span style="color:#00b4d8">Pyme</span>
				    </h1>
				</div>
			</div>
			<div class="row justify-content-center">
			  <div class="col-md-12 col-lg-10">
			    <div class="wrap d-md-flex">
					  <div class="img" style="background-image: url(<%=request.getContextPath()%>/assets/images/is.jpg);">
			       </div>
				 <div class="login-wrap p-4 p-md-5">
			        <div class="d-flex">
			      	   <div class="w-100">
			      		  <h3 class="mb-4"><b>Registrate</b></h3>
			      	   </div>
			      	</div>
					<form action="<%=request.getContextPath()%>/registro/validar" method = "post" class="signin-form">
		              <div class="form-group mb-3">
		            	<label class="label" for="password">Nombre</label>
		                <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
		             </div>
		              <div class="form-group mb-3">
		            	<label class="label" for="password">Apellido</label>
		                <input type="text" name="apellido" class="form-control" placeholder="Apellido" required>
		             </div>		             
			      	  <div class="form-group mb-3">
			      		<label class="label" for="name">Correo electrónico</label>
			      		<input type="text" name="correo" class="form-control" placeholder="Correo electrónico" required>
			      	  </div>
		              <div class="form-group mb-3">
		            	<label class="label" for="password">Contraseña</label>
		                <input type="password" name="pass" class="form-control" placeholder="Contraseña" required>
		             </div>
		             <div class="form-group mb-3">
		             	<label class="label" for="password">Rol</label>
						<select name="rol" class="form-control">
						   <option value="1">Administrador</option> 
						   <option value="2">Contador</option> 
						   <option value="3">Vendedor</option>
						</select>
		             </div>		      
		             <div class="form-group">
		            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Registrarse</button>
		             </div>
		          </form>
		          <p class="text-center">¿Ya te encuentras registrado? <a href="<%=request.getContextPath()%>/login">Ingresa</a></p>
		        </div>
		      </div>
			 </div>
		   </div>
		</div>
	</section>

	<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/assets/js/popper.js"></script>
  <script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/assets/js/main.js"></script>

	</body>
</html>

