package co.edu.ufps.facturacion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class UsuarioDAO extends Conexion<Usuario> implements GenericDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public boolean logear(Usuario usuario) {
		boolean isLog = true;
		Query query = null;
		try {
			if (usuario.getCorreo() != null && usuario.getContrasena() != null) {
				query = getEm().createNamedQuery(Usuario.class.getSimpleName() + ".login", Usuario.class);
				query.setParameter("correo", usuario.getCorreo());
				query.setParameter("contrasena", usuario.getContrasena());
				query.getSingleResult();
			}
		} catch (NoResultException e) {
			isLog = false;
		}

		return isLog;
	}
	
	public boolean existeCorreo(String correo) {
		Query query = null;
		try {
			if (correo!=null && !correo.isEmpty()) {
				query = getEm().createNamedQuery(Usuario.class.getSimpleName() + ".validarCorreo", Usuario.class);
				query.setParameter("correo", correo);
				query.getSingleResult();
			}
		} catch (NoResultException e) {
			return false;
		}

		return query!=null;
	}
	
	public List<Usuario> listarPorEmpresa(int nit){
		Query consulta= getEm().createNamedQuery(Usuario.class.getSimpleName()+".findByEmpresa", Usuario.class).setParameter("nit", nit);
		List<Usuario> lista = (ArrayList<Usuario>) consulta.getResultList();
		return lista;
	}
}
