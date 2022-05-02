package co.edu.ufps.facturacion.controller;

import java.io.File;
import java.io.IOException;
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

import co.edu.ufps.facturacion.entities.*;
import co.edu.ufps.facturacion.correo.EnviarCorreo;
import co.edu.ufps.facturacion.dao.*;

/**
 * Servlet implementation class PruebaFactura
 */
@WebServlet("/PruebaFactura")
public class PruebaFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ClienteDAO cDAO;
	private RangoNumeracionDAO rgDAO;
	private ProductoDAO pDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PruebaFactura() {
        super();
        cDAO = new ClienteDAO();
        rgDAO = new RangoNumeracionDAO();
        pDAO = new ProductoDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EnviarCorreo ec = new EnviarCorreo();
		File f= new File(System.getProperty("user.dir")+"/soltec.png");
		File ff = new File(System.getProperty("user.dir")+"/recibo.pdf");
		List<Articulo> articulos = capturarValores(request,response);
		Cliente cl = cDAO.find(Integer.parseInt(request.getParameter("numeroDocumento")));
		Empresa em = request.getSession().getAttribute("empresa") != null? (Empresa) request.getSession().getAttribute("empresa") : null;
		RangoNumeracion rg = rgDAO.find(Integer.parseInt(request.getParameter("rg")));
		//aumentar rango cuando se emita factura (numeroActual)
		
		Date fecha=new Date();
		double descuento = Double.parseDouble(request.getParameter("tDescuento"));
		double subtotal = Double.parseDouble(request.getParameter("subtotal"));
		double total = Double.parseDouble(request.getParameter("total"));
		double totalIva = Double.parseDouble(request.getParameter("iva"));
		Factura fa = new Factura((byte) 1, fecha,obtenerFecha(fecha), em.getNombreRepresentante(), descuento, subtotal, total,totalIva, cl, em, rg);
		fa.generarCufe();
		//insertar detalle factura

		ec.enviarCorreo(ff.toString(), f, cl, fa, articulos, rg);
		

	}

	protected List<Articulo> capturarValores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String [] cod = request.getParameterValues("codigo");
		String [] can = request.getParameterValues("cantidad");
		List<Articulo> articulos = new ArrayList<>();
		for(int i=0;i<cod.length;i++) {
			articulos.add(new Articulo(pDAO.find(Integer.parseInt(cod[i])), Integer.parseInt(can[i])));
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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
