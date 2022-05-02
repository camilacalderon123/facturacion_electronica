package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.EmpresaDAO;
import co.edu.ufps.facturacion.dao.ProductoDAO;
import co.edu.ufps.facturacion.entities.Empresa;
import co.edu.ufps.facturacion.entities.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet({ "/inicio/producto/ver", // ver productos - la lista
		"/inicio/producto/agregar", // vista agregar
		"/inicio/producto/editar", // vista editar producto

		"/inicio/producto/agregar/validar", // agregar producto
		"/inicio/producto/eliminar/validar", // eliminar producto
		"/inicio/producto/editar/validar"// editar producto
})
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductoDAO pDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoController() {
		super();
		pDAO = new ProductoDAO();
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

		path = path.replace("/inicio/producto/", "");
		Producto p = null;

		if (path.contains("/validar")) {
			verProductoCRUD(request, response, path);
			response.sendRedirect(request.getContextPath() + "/inicio/producto/ver");
			return;
		} else {
			switch (path) {
			case "ver":
				request.setAttribute("productos", pDAO.list());
				request.getRequestDispatcher("/Dashboard/verProductos.jsp").include(request, response);
				break;
			case "agregar":
				request.getRequestDispatcher("/Dashboard/agregarProducto.jsp").include(request, response);
				break;
			case "editar":
				p = pDAO.find(Integer.parseInt(request.getParameter("codigo")));
				if (p != null) {
					request.setAttribute("producto", p);
					request.getRequestDispatcher("/Dashboard/editarProducto.jsp").include(request, response);
				}
				break;
			default:
				request.getRequestDispatcher("/inicio").forward(request, response);
				break;
			}
		}
	}

	protected void verProductoCRUD(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/validar", "");

		switch (path) {
		case "agregar":
			agregarProducto(request, response);
			break;
		case "eliminar":
			eliminarProducto(request, response);
			break;
		case "editar":
			editarProducto(request, response);
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}

	protected void agregarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Empresa e = request.getSession().getAttribute("empresa") == null ? null
				: (Empresa) request.getSession().getAttribute("empresa");
		
		if (pDAO.find(codigo) == null) {
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			String unidadMedida = request.getParameter("unidadMedida");
			double valorUnitario = Double.parseDouble(request.getParameter("valorUnitario"));
			double iva = Double.parseDouble(request.getParameter("iva"));
			double porcentajeDescuento = Double.parseDouble(request.getParameter("descuento"));
			byte estado = Byte.parseByte(request.getParameter("estado"));

			Producto p = new Producto(codigo, descripcion, estado, iva, nombre, porcentajeDescuento, unidadMedida,
					valorUnitario);
			e.addProducto(p);
			pDAO.insert(p);
			new EmpresaDAO().update(e);
			
		} else {
			request.setAttribute("mensaje", "Ya existe el producto con el ID: " + codigo);
		}
	}

	protected void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Producto p = pDAO.find(codigo);

		if (p != null) {
			p.setEstado((byte) 0);
			pDAO.update(p);
		} else {
			request.setAttribute("mensaje", "No existe el producto con el ID: " + codigo);
		}
	}

	protected void editarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Producto p = pDAO.find(codigo);
		if (p != null) {
			p.setNombre(request.getParameter("nombre"));
			p.setDescripcion(request.getParameter("descripcion"));
			p.setUnidadMedia(request.getParameter("unidadMedida"));
			p.setValorUnitario(Double.parseDouble(request.getParameter("valorUnitario")));
			p.setIva(Double.parseDouble(request.getParameter("iva")));
			p.setPorcentajeDescuento(Double.parseDouble(request.getParameter("descuento")));

			pDAO.update(p);
		} else {
			request.setAttribute("mensaje", "No existe el producto con el ID: " + codigo);
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
