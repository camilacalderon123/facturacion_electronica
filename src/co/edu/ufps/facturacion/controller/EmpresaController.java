package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.EmpresaDAO;
import co.edu.ufps.facturacion.dao.TipoDocumentoDAO;
import co.edu.ufps.facturacion.entities.Cliente;
import co.edu.ufps.facturacion.entities.Empresa;
import co.edu.ufps.facturacion.entities.TipoDocumento;
import co.edu.ufps.facturacion.entities.Usuario;

/**
 * Servlet implementation class EmpresaController
 */
@WebServlet({ "/inicio/empresa/agregar", "/inicio/empresa/editar",

		"/inicio/empresa/agregar/validar", "/inicio/empresa/editar/validar" })
public class EmpresaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EmpresaDAO eDAO;
	private TipoDocumentoDAO tDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpresaController() {
		super();
		eDAO = new EmpresaDAO();
		tDAO = new TipoDocumentoDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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

		path = path.replace("/inicio/empresa/", "");
		Empresa e = null;

		if (path.contains("validar")) {
			verEmpresaCRUD(request, response, path);
			response.sendRedirect(request.getContextPath() + "/inicio");
		} else {
			switch (path) {
			case "agregar":
				request.getRequestDispatcher("/Dashboard/agregarDatosFiscalesEmpresa.jsp").include(request, response);
				break;
			case "editar":
				e = request.getSession().getAttribute("empresa")!=null?(Empresa)request.getSession().getAttribute("empresa"):null;
				if (e != null) {//VALIDAR EN LA VISTA
					request.setAttribute("em", e);
					request.getRequestDispatcher("/Dashboard/editarDatosFiscalesEmpresa.jsp").include(request, response);
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

	protected void verEmpresaCRUD(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/validar", "");

		switch (path) {
		case "agregar":
			agregarEmpresa(request, response);
			break;
		case "editar":
			editarEmpresa(request, response);
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}

	protected void agregarEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String n =String.valueOf(request.getParameter("nit"));
		System.out.println(n);
		int nit = Integer.parseInt(n);

		if (eDAO.find(nit) == null) {
			Usuario us = (Usuario)request.getSession().getAttribute("usuario");
			
			String numeroDocumento = request.getParameter("documento");
			String razonSocial = request.getParameter("razonSocial");
			String direccion = request.getParameter("direccion");
			String departamento = request.getParameter("departamento");
			String municipio = request.getParameter("municipio");
			String correo = request.getParameter("correo");
			String telefono = request.getParameter("telefono");
			String nombreRepresentante = request.getParameter("nombreRepresentante");

			TipoDocumento tipo = tDAO.find(Integer.parseInt(request.getParameter("tipoDocumento")));

			Empresa e = new Empresa(nit, correo, departamento, direccion, "", "", municipio, nombreRepresentante,
					numeroDocumento, razonSocial, telefono, tipo);
			e.addUsuario(us);
			eDAO.insert(e);
			request.getSession().setAttribute("empresa", e);
		} else {
			request.setAttribute("errorAgregarEmpresa", "Ya existe una empresa con nit: " + nit);
		}
	}
	
	protected void editarEmpresa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String n =request.getParameter("nit");
		System.out.println(n);
		int nit = Integer.parseInt(n);
		Empresa e =eDAO.find(nit);
		
		if (e != null) {
			e.setNumeroDocumento(request.getParameter("documento"));
			e.setRazonSocial(request.getParameter("razonSocial"));
			e.setDireccion(request.getParameter("direccion"));
			e.setDepartamento(request.getParameter("departamento"));
			e.setMunicipio(request.getParameter("municipio"));
			e.setCorreoEmpresa(request.getParameter("correo"));
			e.setTelefono(request.getParameter("telefono"));
			e.setNombreRepresentante(request.getParameter("nombreRepresentante"));

			TipoDocumento tipo = tDAO.find(Integer.parseInt(request.getParameter("tipoDocumento")));
			e.setTipoDocumentoBean(tipo);
			
			eDAO.update(e);

		} else {
			request.setAttribute("errorEditarEmpresa", "No existe la empresa");
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
