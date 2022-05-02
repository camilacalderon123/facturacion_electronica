package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity(name="usuario")
@NamedQueries({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM usuario u"),
	@NamedQuery(name="Usuario.login", query="SELECT u FROM usuario u where u.correo=:correo and u.contrasena=:contrasena "),
	@NamedQuery(name="Usuario.validarCorreo", query="SELECT u FROM usuario u where u.correo=:correo"),
	@NamedQuery(name="Usuario.findByEmpresa", query="SELECT u FROM usuario u WHERE u.empresa.nit=:nit")
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String correo;

	private String apellido;

	private String contrasena;

	private byte estado;

	private String nombre;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	private Empresa empresa;

	//bi-directional many-to-one association to RolUsuario
	@ManyToOne
	@JoinColumn(name="rol_usuario")
	private RolUsuario rolUsuarioBean;

	public Usuario() {
	}

	public Usuario(String correo, String contrasena) {
		this.correo = correo;
		this.contrasena = contrasena;
	}
	
	public Usuario(String correo, String apellido, String contrasena, byte estado, String nombre) {
		super();
		this.correo = correo;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.estado = estado;
		this.nombre = nombre;
	}
	
	public Usuario(String correo, String apellido, String contrasena, byte estado, String nombre, Empresa empresa,
			RolUsuario rolUsuarioBean) {
		super();
		this.correo = correo;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.estado = estado;
		this.nombre = nombre;
		this.empresa = empresa;
		this.rolUsuarioBean = rolUsuarioBean;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public RolUsuario getRolUsuarioBean() {
		return this.rolUsuarioBean;
	}

	public void setRolUsuarioBean(RolUsuario rolUsuarioBean) {
		this.rolUsuarioBean = rolUsuarioBean;
	}

}