<%@page import="co.edu.ufps.facturacion.entities.*"%>
<%@page import="java.util.*"%>

<%
	Usuario us = request.getSession().getAttribute("usuario") == null ? null
		: (Usuario) request.getSession().getAttribute("usuario");
%>

<a class="nav-link dropdown-toggle" href="#" id="userDropdown"
	role="button" data-toggle="dropdown" aria-haspopup="true"
	aria-expanded="false"> <span
	class="mr-2 d-none d-lg-inline text-gray-600 small"><%=us != null ? us.getNombre() : "Usuario"%></span>
	<img class="img-profile rounded-circle"
	src="<%=request.getContextPath()%>/Dashboard/img/logoufps.png">
</a>
<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
	aria-labelledby="userDropdown">

	<a class="dropdown-item" href="<%=request.getContextPath()%>/logout" > <i
		class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Cerrar
		sesión
	</a>
</div>