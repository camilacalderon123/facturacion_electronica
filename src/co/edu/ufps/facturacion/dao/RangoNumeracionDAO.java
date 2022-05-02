package co.edu.ufps.facturacion.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class RangoNumeracionDAO extends Conexion<RangoNumeracion> implements GenericDAO<RangoNumeracion>{

	public RangoNumeracionDAO() {
		super(RangoNumeracion.class);
	}
	
	public RangoNumeracion findLast(int nit) {
		Query query = null;
		RangoNumeracion r=null;
		try {
				query = getEm().createNamedQuery(RangoNumeracion.class.getSimpleName() + ".findLast", RangoNumeracion.class).setParameter("nit", nit);
				r=(RangoNumeracion)query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

		return r;
	}
}
