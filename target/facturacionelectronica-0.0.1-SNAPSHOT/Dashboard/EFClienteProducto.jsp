<%@page import="co.edu.ufps.facturacion.entities.*"%>
<%@page import="co.edu.ufps.facturacion.dao.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
    <title>Facturas</title>
    
    <script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jQuery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	var subtotal = 0;
	var valor = 0;
	
		
		$('#form-validar').on('keyup keypress', function(e) {
		  var keyCode = e.keyCode || e.which;
		  if (keyCode === 13) { 
		    e.preventDefault();
		    return false;
		  }
		});
	
		$(document).on('keyup mouseup', '#cantidad', function() {                                                                                                                     
			calcular();
		});
	
		$('#calcular').click(function(){
			calcular();
		});
		
		$("#tablaprueba").on("click", "#eliminarFila", function(){
			$(this).parents("tr").remove();
			calcular();
		});
		
		$('#productos').change(function() {
			agregarFila($('#productos'));
			console.log($('#codigo'));
			calcular();
		});
		
		
		function agregarFila(combo){
			if(combo.val()=='0'){
				return;
			}
			var valores = combo.val().split(";");
			
			let verificar = verificarCodigo(valores[0]);
						
			if(!verificar){
			//$('.table-responsive').prepend("<input type='hidden' id='codigo' name='codigo' value='"+valores[0]+"'>");
			$('#fila').prepend(" <tr><td>"+valores[0]+"</td> <td>"+valores[1]+"</td> <td>"+valores[2]+"</td> <td>"+valores[3]+"</td> <td>"+valores[4]+"</td> <td>"+valores[5]+"</td>"+
					"<td><input type='number' id='cantidad' name='cantidad' value='1' min='1'><input type='hidden' id='codigo' name='codigo' value='"+valores[0]+"'></td>"+
                    "<td><button type='button' class='btn' id='eliminarFila'> <i class='fas fa-minus-circle'></i>"+
                    "</button></td></tr>");
			}else{
				aumentarCantidad(valores[0]);
			}
			$("#productos").prop('selectedIndex', 0); 
			
			//$("#productos option[value='"+combo.val()+"']").remove();
		
		}
		
		function verificarCodigo(id){
			let v = false;
			$("#fila tr").each(function(){
				   let cod =$(this).find('td').eq(0).text();

				  if(cod==id){
					  v=true;
				  }
			});
			return v;
			
		}
		
		function aumentarCantidad(id){
			$("#fila tr").each(function(){
				   let cod =$(this).find('td').eq(0).text();
				  
				  if(cod==id){
					  let cant = $(this).find('#cantidad').val();
					  cant =parseInt(cant,10);
					  $(this).find('#cantidad').val(parseInt(1+cant));
				  }
			});
		}
		
		function calcular(){
			let subtotal = 0;
			let totalDescuento = 0;
			let ivaTotal = 0;
			let total = 0;
			$("#fila tr").each(function(){
			   let valorUn =parseInt($(this).find('td').eq(3).text(),10);
			   let desc =parseInt($(this).find('td').eq(4).text(),10);
			   let iva =parseInt($(this).find('td').eq(5).text(),10);
			   let cant = parseInt($(this).find('#cantidad').val(),10);
			   
			   subtotal+= (valorUn*cant);
			   totalDescuento+= (((valorUn*cant)*desc)/100);
			   ivaTotal += (((valorUn*cant)*iva)/100);
		});
			total=(subtotal-totalDescuento)+ivaTotal;
			
			llenarCampos(subtotal,totalDescuento,ivaTotal,total);
		}
		
		function llenarCampos(subtotal,totalDesc,iva,total){
			$('#subtotal').val(subtotal);
			$('#tDescuento').val(totalDesc);
			$('#iva').val(iva);
			$('#total').val(total);
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
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">


                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Buscar"
                                aria-label="Search" aria-describedby="basic-addon2">
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
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Buscar" aria-label="Buscar" aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>



                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow"> 
						<jsp:include page="imgUsuario.jsp" /><!-- Dropdown - User Information -->
						</li>

                    </ul>

                </nav>
                <!-- End of Topbar -->


    
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <h1 class="title_client">Emitir factura</h1>
                        <!-- DataTales Example -->
                        <div class="card shadow mb-4">
                            <div class="card-body">
                                <form action="<%=request.getContextPath()%>/inicio/factura/agregar/validar" method="post" id="form-validar">
                                <% Empresa e = request.getSession().getAttribute("empresa") != null
										? (Empresa) request.getSession().getAttribute("empresa") : null;
										Cliente cl = (Cliente)request.getAttribute("cliente");
								
									if(cl!=null && e!=null){
                                 		RangoNumeracion rg = new RangoNumeracionDAO().findLast(e.getNit());
                                
                                 %>
                                    <!--Inicio campos del cliente-->
                                    <div class="input-box-numeracion">
                                        <span class="span_details">No.</span>
                                        <input class="input_numeracion" type="number" name="rg" value="<%=rg.getIdNumeracion()%>" readonly>
                                    </div>

                                    <div class="input-box">
                                        <span class="details">Número de documento</span>
                                        <input style="margin-left: 13px;" type="number" name="numeroDocumento" value="<%=cl.getNumeroDocumento()%>" readonly>
                                    </div>
                                    <div class="input-box">
                                        <span class="details">Nombre</span>
                                        <input style="margin-left: 13px;" type="text" name="nombre" value="<%=cl.getNombre()%>" readonly>
                                    </div>

                                    <div class="input-box">
                                        <span class="details">Teléfono</span>
                                        <input style="margin-left: 10px;" type="text" name="telefono" value="<%=cl.getTelefono()%>" readonly>
                                    </div>

                                    <div class="input-box">
                                        <span class="details">Correo</span>
                                        <input style="margin-left: 24px;" type="text" name="correo" value="<%=cl.getCorreo()%>" readonly>
                                    </div>

                                    <div class="input-box">
                                        <span class="details">Ciudad</span>
                                        <input style="margin-left: 23px;" type="text" name="ciudad" value="<%=cl.getCiudad()%>" readonly>
                                    </div>
                                    <!--Fin campos del cliente-->
                                    <hr>
                                    <!--Inicio tabla de agregar los productos-->
                                     
                                      
                                     <div class="input-box">
                                       <select class='select_opciones' id="productos">
                                       <option value='0'>Seleccione producto</option>
										<% 

										List<Producto> productos=new ProductoDAO().listarPorEmpresa(e.getNit());
                                            
                                            for(Producto p:productos){
                                     	%>
										<option value="<%=p.getCodigo()%>;<%=p.getNombre()%>;<%=p.getUnidadMedia()%>;<%=p.getValorUnitario()%>;<%=p.getPorcentajeDescuento()%>;<%=p.getIva()%>;<%=productos.size()%>"><%=p.getNombre()%></option>
										<%
                                            }
                                     	%>
									   </select>
                                    </div>
                                                
                                    <div class="table-responsive">
                                        <table #dataTable class="table table-bordered" id="tablaprueba" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Código</th>
                                                    <th>Nombre</th>
                                                    <th>U/M</th>
                                                    <th>Vr. Unit.</th>
                                                    <th>% Dcto.</th>
                                                    <th>IVA</th>
                                                    <th>Cantidad</th>
                                                    <th>Eliminar</th>

                                            </thead>
                                            <tbody id="fila">
                                           
                                           
                                            </tbody>
                                        </table>
                                        <!-- <div class="form-group">
                                            <button type="button" class="btn btn-edit" id="agregarFila">
                                                <i class="fas fa-plus"></i>
                                            </button>
                                        </div> -->
                                    </div>
                                    
                                    <div class="input-box">
									<span class="details">Subtotal $</span> <input
										style="margin-left: 13px;" type="number" id="subtotal" name="subtotal" readonly>
								</div>
								<div class="input-box">
									<span class="details">Total descuento $</span> <input
										style="margin-left: 13px;" type="number" id="tDescuento" name="tDescuento" readonly>
								</div>
								<div class="input-box">
									<span class="details">IVA(19%) $</span> <input
										style="margin-left: 13px;" type="number" id="iva" name="iva" readonly>
								</div>
								<hr>
								<div class="input-box">
									<span class="details">Total $</span><input
										style="margin-left: 13px;" type="number" id="total" name="total"readonly>
								</div>
								<div class="input-box">
                                        <input style="margin-left: 23px;background-color: #00B4D8; color:white" type="button" value="Saldar" id='calcular'>
                                 </div>
									<%}else{
                                    	 response.sendRedirect(request.getContextPath() + "/inicio");
                                     } %>
                                        
                                    <!--Fin tabla de agregar los productos-->
                                    <div class="button2">
                                        <button type="submit" class="button_style">Siguiente</button>
                                    </div>
                                    <div class="button2">
                                       
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

		<!-- /.container-fluid -->
    

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">¿Cerrar sesión?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Seleccione "Salir" si está listo para cerrar su sesión.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
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