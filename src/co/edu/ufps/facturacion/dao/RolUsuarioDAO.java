package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class RolUsuarioDAO extends Conexion<RolUsuario> implements GenericDAO<RolUsuario>{

	public RolUsuarioDAO() {
		super(RolUsuario.class);
	}
}
