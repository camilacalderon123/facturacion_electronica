package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.EmpresaDAO;
import co.edu.ufps.facturacion.dao.RolUsuarioDAO;
import co.edu.ufps.facturacion.dao.UsuarioDAO;
import co.edu.ufps.facturacion.entities.Empresa;
import co.edu.ufps.facturacion.entities.RolUsuario;
import co.edu.ufps.facturacion.entities.Usuario;

/**
 * Servlet implementation class AdministradorController
 */
@WebServlet({ "/login/validar", "/registro/validar", "/logout" })
public class AdministradorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO uDAO;
	private EmpresaDAO eDAO;
	private RolUsuarioDAO rDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministradorController() {
		super();
		uDAO = new UsuarioDAO();
		eDAO = new EmpresaDAO();
		rDAO = new RolUsuarioDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (!path.contains("logout") && request.getSession().getAttribute("usuario") != null) {
			request.getRequestDispatcher("/inicio").forward(request, response);
			return;
		}
		System.out.println(path);
		switch (path) {
		case "/login/validar":
			logear(request, response);
			break;
		case "/registro/validar":
			registrarUsuario(request, response);
			break;
		case "/logout":
			logout(request, response);
			break;
		default:
			break;
		}
	}

	protected void logear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String correo = request.getParameter("correo");
		Usuario usuario = new Usuario(correo, request.getParameter("pass"));
		Empresa e = request.getSession().getAttribute("empresa") == null ? null
				: (Empresa) request.getSession().getAttribute("empresa");
		if (usuario != null && uDAO.logear(usuario)) {
			request.getSession().setAttribute("usuario", uDAO.find(correo));
			if (e != null) {
				request.getSession().setAttribute("empresa",e);
			}
			request.getRequestDispatcher("/inicio").forward(request, response);
			return;
		} else {
			request.setAttribute("mensaje", "No existe el usuario");
			request.getRequestDispatcher("/login").forward(request, response);
		}

	}

	protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String correo = request.getParameter("correo");

		if (uDAO.find(correo) == null) {
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String pass = request.getParameter("pass");
			String rol = request.getParameter("rol");

			RolUsuario ru = rDAO.find(verRol(rol));
			uDAO.insert(new Usuario(correo, apellido, pass, (byte) 1, nombre, null, ru));
			request.getRequestDispatcher("/login").forward(request, response);
		} else {
			request.setAttribute("mensaje", "Ya existe un usuario con este correo");
			request.getRequestDispatcher("/login").forward(request, response);
		}
	}

	private int verRol(String rol) {
		switch (rol) {
		case "Administrador":
			return 1;
		case "Contador":
			return 2;
		case "Vendedor":
			return 3;
		default:
			return 1;
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario us = request.getSession().getAttribute("usuario") == null ? null
				: (Usuario) request.getSession().getAttribute("usuario");
		Empresa e = request.getSession().getAttribute("empresa") == null ? null
				: (Empresa) request.getSession().getAttribute("empresa");
		if (us != null) {
			request.getSession().removeAttribute("usuario");
			if (e != null) {
				request.getSession().removeAttribute("empresa");
			}
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
