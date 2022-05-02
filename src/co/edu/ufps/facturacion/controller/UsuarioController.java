package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.RolUsuarioDAO;
import co.edu.ufps.facturacion.dao.UsuarioDAO;
import co.edu.ufps.facturacion.entities.Cliente;
import co.edu.ufps.facturacion.entities.Empresa;
import co.edu.ufps.facturacion.entities.RolUsuario;
import co.edu.ufps.facturacion.entities.TipoDocumento;
import co.edu.ufps.facturacion.entities.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet({"/inicio/usuario/ver",
			"/inicio/usuario/agregar",
			"/inicio/usuario/editar",
			
			"/inicio/usuario/agregar/validar",
			"/inicio/usuario/editar/validar",
			"/inicio/usuario/eliminar/validar"
})
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO uDAO;
	private RolUsuarioDAO rDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        uDAO = new UsuarioDAO();
        rDAO = new RolUsuarioDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (request.getSession().getAttribute("usuario") == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
		verInicio(request, response, path);
	}

    protected void verInicio(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/inicio/usuario/", "");
		Usuario us = null;

		if (path.contains("validar")) {
			verUsuarioCRUD(request, response, path);
			response.sendRedirect(request.getContextPath() + "/inicio/usuario/ver");
		} else {
			switch (path) {
			case "ver":
				us = (Usuario)request.getSession().getAttribute("usuario");
		    	Empresa e =us.getEmpresa();
				request.setAttribute("usuarios", e!=null?uDAO.listarPorEmpresa(e.getNit()):null);
				request.getRequestDispatcher("/Dashboard/verUsuarios.jsp").include(request, response);
				break;
			case "agregar":
				request.getRequestDispatcher("/Dashboard/agregarUsuario.jsp").include(request, response);
				break;
			case "editar":
				us = uDAO.find(request.getParameter("correo"));
				if (us != null) {
					request.setAttribute("usuario", us);
					request.getRequestDispatcher("/Dashboard/editarUsuario.jsp").include(request, response);
				} else {
					request.getRequestDispatcher("/inicio").forward(request, response);
				}
				break;
			default:
				request.getRequestDispatcher("/inicio").forward(request, response);
				break;
			}
		}

	}
    
    protected void verUsuarioCRUD(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/validar", "");

		switch (path) {
		case "agregar":
			agregarUsuario(request, response);
			break;
		case "eliminar":
			eliminarUsuario(request, response);
			break;
		case "editar":
			editarUsuario(request,response);
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}
    
    protected void agregarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

    	String correo = request.getParameter("correo");
    	Usuario us = (Usuario)request.getSession().getAttribute("usuario");
    	Empresa e =us.getEmpresa();
    	
		if(uDAO.find(correo)==null) {
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String pass = request.getParameter("pass");
			//String rol = request.getParameter("rol");
			
			RolUsuario ru = rDAO.find(Integer.parseInt(request.getParameter("rol")));
			Usuario u =new Usuario(correo, apellido, pass, (byte)1, nombre,null,ru);
			e.addUsuario(u);
			uDAO.insert(u);
		}else {
			request.setAttribute("errorAgregarUsuario", "Ya existe un cliente con correo: " + correo);
		}
	}
    
    private int verRol(String rol) {
		switch(rol) {
		case "Administrador":return 1;
		case "Contador":return 2;
		case "Vendedor":return 3;
		default:return 1;
		}
	}
    
    protected void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String correo = request.getParameter("correo");
		Usuario us = uDAO.find(correo);

		if (us != null) {
			us.setEstado((byte) 0);
			uDAO.update(us);
		} else {
			request.setAttribute("errorEliminarUsuario", "No existe el usuario con correo: " + correo);
		}
	}
    
    protected void editarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

    	String correo = request.getParameter("correo");
    	Usuario us = uDAO.find(correo);
		if(us!=null) {
			us.setNombre(request.getParameter("nombre"));
			us.setApellido(request.getParameter("apellido"));

			RolUsuario ru = rDAO.find(Integer.parseInt(request.getParameter("rol")));
			us.setRolUsuarioBean(ru);
			uDAO.update(us);
		}else {
			request.setAttribute("errorEditarUsuario", "No existe el usuario con correo: " + correo);
		}
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
