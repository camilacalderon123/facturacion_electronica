package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.entities.Cliente;
import co.edu.ufps.facturacion.entities.RangoNumeracion;

public class d {

	public static void main(String[] args) {
		RangoNumeracionDAO rDAO =new RangoNumeracionDAO();
		
		RangoNumeracion r=rDAO.findLast(26542);
		System.out.println(r!=null?r.getIdNumeracion():"a");
	}
}
