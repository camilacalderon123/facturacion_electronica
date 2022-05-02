package co.edu.ufps.facturacion.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.correo.EnviarCorreo;
import co.edu.ufps.facturacion.dao.ClienteDAO;
import co.edu.ufps.facturacion.dao.DetalleFacturaDAO;
import co.edu.ufps.facturacion.dao.EmpresaDAO;
import co.edu.ufps.facturacion.dao.FacturaDAO;
import co.edu.ufps.facturacion.dao.ProductoDAO;
import co.edu.ufps.facturacion.dao.RangoNumeracionDAO;
import co.edu.ufps.facturacion.entities.Articulo;
import co.edu.ufps.facturacion.entities.Cliente;
import co.edu.ufps.facturacion.entities.DetalleFactura;
import co.edu.ufps.facturacion.entities.Empresa;
import co.edu.ufps.facturacion.entities.Factura;
import co.edu.ufps.facturacion.entities.RangoNumeracion;

/**
 * Servlet implementation class FacturaController
 */
@WebServlet({ "/inicio/factura/ver", //
		"/inicio/factura/agregar",
		"/inicio/factura/emitir",
		"/inicio/factura/rango",

		"/inicio/factura/agregar/validar",
		"/inicio/factura/rango/validar",
		"/inicio/factura/eliminar/validar",
})
public class FacturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FacturaDAO fDAO;
	private DetalleFacturaDAO dfDAO;
	private EmpresaDAO eDAO;
	private ClienteDAO cDAO;
	private RangoNumeracionDAO rgDAO;
	private ProductoDAO pDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FacturaController() {
		super();
		fDAO = new FacturaDAO();
		dfDAO = new DetalleFacturaDAO();
		eDAO = new EmpresaDAO();
		cDAO = new ClienteDAO();
		rgDAO = new RangoNumeracionDAO();
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

		path = path.replace("/inicio/factura/", "");
		Factura fa = null;
		if (path.contains("/validar")) {
			verFacturaCRUD(request, response, path);
			response.sendRedirect(request.getContextPath() + "/inicio/factura/ver");
			return;
		} else {
			switch (path) {
			case "ver":
				request.setAttribute("facturas", fDAO.list());// VER SI EST¡ VACÌA EN LA VISTA <%IF(CLIENTES!=NULL)%>
				request.getRequestDispatcher("/Dashboard/verFacturas.jsp").include(request, response);
				break;
			case "agregar":
				request.getRequestDispatcher("/Dashboard/emitirFactura.jsp").include(request, response);
				break;
			case "emitir":
				Cliente cl = cDAO.find(Integer.parseInt(request.getParameter("cliente")));
				request.setAttribute("cliente", cl);
				request.getRequestDispatcher("/Dashboard/EFClienteProducto.jsp").include(request, response);
				break;
			case "rango":
				request.getRequestDispatcher("/Dashboard/agregarRangosNumeracion.jsp").include(request, response);
				break;
			/*
			 * case "editar": fa = fDAO.find(request.getParameter("CUFE")); if (fa != null)
			 * { request.setAttribute("factura", fa);
			 * request.getRequestDispatcher("Dashboard/editarFactura.jsp").forward(request,
			 * response); } else {
			 * request.getRequestDispatcher("/inicio/factura/ver").forward(request,
			 * response); } break;
			 */
			default:
				request.getRequestDispatcher("/inicio").forward(request, response);
				break;
			}
		}
	}

	protected void verFacturaCRUD(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/validar", "");

		switch (path) {
		case "agregar":
			emitirFactura(request, response);
			break;
		case "rango":
			agregarRango(request, response);
			break;
		case "eliminar":
			eliminarFactura(request, response);
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}
	
	protected void emitirFactura(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EnviarCorreo ec = new EnviarCorreo();
		File f= new File(System.getProperty("user.dir")+"/soltec.png");
		File ff = new File(System.getProperty("user.dir")+"/recibo.pdf");
		
		Cliente cl = cDAO.find(Integer.parseInt(request.getParameter("numeroDocumento")));
		Empresa em = request.getSession().getAttribute("empresa") != null? (Empresa) request.getSession().getAttribute("empresa") : null;
		RangoNumeracion rg = rgDAO.find(Integer.parseInt(request.getParameter("rg")));
		
		
		Date fecha=new Date();
		double descuento = Double.parseDouble(request.getParameter("tDescuento"));
		double subtotal = Double.parseDouble(request.getParameter("subtotal"));
		double total = Double.parseDouble(request.getParameter("total"));
		double totalIva = Double.parseDouble(request.getParameter("iva"));
		Factura fa = new Factura((byte) 1, fecha,obtenerFecha(fecha), em.getNombreRepresentante(), descuento, subtotal, total,totalIva, cl, em, rg);
		fa.generarCufe();

		List<Articulo> articulos = capturarValores(request,response, fa);
		ec.enviarCorreo(ff.toString(), f, cl, fa, articulos, rg);
		
		fDAO.insert(fa);
		rg.setNumeroActual(rg.getNumeroActual()+1);
		rgDAO.update(rg);
	}

	protected List<Articulo> capturarValores(HttpServletRequest request, HttpServletResponse response, Factura fa) throws ServletException, IOException {

		String [] cod = request.getParameterValues("codigo");
		String [] can = request.getParameterValues("cantidad");
		List<Articulo> articulos = new ArrayList<>();
		for(int i=0;i<cod.length;i++) {
			Articulo a = new Articulo(pDAO.find(Integer.parseInt(cod[i])), Integer.parseInt(can[i]));
			DetalleFactura df =new DetalleFactura(a.getCantidad());
			fa.addDetalleFactura(df);
			a.getProducto().addDetalleFactura(df);
			dfDAO.insert(df);
			pDAO.update(a.getProducto());
			articulos.add(a);
		}
		return articulos.isEmpty()?null:articulos;
	}

	private Date obtenerFecha(Date fecha) {
		SimpleDateFormat dia = new SimpleDateFormat("dd");
	    SimpleDateFormat mes = new SimpleDateFormat("MM");
	    SimpleDateFormat anio = new SimpleDateFormat("YYYY");
	    
		return new GregorianCalendar(Integer.parseInt(anio.format(fecha)),
				Integer.parseInt(mes.format(fecha)),
				Integer.parseInt(dia.format(fecha))).getTime();
	}
	
	protected void agregarRango(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Empresa e = request.getSession().getAttribute("empresa") == null ? null
				: (Empresa) request.getSession().getAttribute("empresa");
		RangoNumeracion rg = rgDAO.findLast(e.getNit());
		if(rg==null || rg.getNumeroActual()<rg.getNumeroHasta()) {
			try {
				String prefijo =request.getParameter("prefijo");
				int desde = Integer.parseInt(request.getParameter("desde"));
				int hasta = Integer.parseInt(request.getParameter("hasta"));
				int actual = Integer.parseInt(request.getParameter("actual"));
				int numeroResolucion = Integer.parseInt(request.getParameter("numeroResolucion"));
				String fecha = request.getParameter("fecha");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fechaResolucion = null;
				fechaResolucion = sdf.parse(fecha);
				
				RangoNumeracion r =new RangoNumeracion(fechaResolucion, actual, desde, 
						hasta, numeroResolucion, prefijo);
				e.addRangoNumeracion(r);
				rgDAO.insert(r);
				eDAO.update(e);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	protected void eliminarFactura(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Factura f= fDAO.find(request.getParameter("cufe"));
		f.setEstado((byte)0);
		fDAO.update(f);
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
