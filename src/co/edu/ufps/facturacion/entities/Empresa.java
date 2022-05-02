package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity(name="empresa")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int nit;

	@Column(name="correo_empresa")
	private String correoEmpresa;

	private String departamento;

	private String direccion;

	private String documento;

	@Lob
	@Column(name="logo_empresa")
	private String logoEmpresa;

	private String municipio;

	@Column(name="nombre_representante")
	private String nombreRepresentante;

	@Column(name="numero_documento")
	private String numeroDocumento;

	@Column(name="razon_social")
	private String razonSocial;

	private String telefono;

	//bi-directional many-to-one association to ClienteEmpresa
	@OneToMany(mappedBy="empresa")
	private List<ClienteEmpresa> clienteEmpresas;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tipo_documento")
	private TipoDocumento tipoDocumentoBean;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="empresa")
	private List<Factura> facturas;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="empresa")
	private List<Producto> productos;

	//bi-directional many-to-one association to RangoNumeracion
	@OneToMany(mappedBy="empresa")
	private List<RangoNumeracion> rangoNumeracions;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="empresa")
	private List<Usuario> usuarios;

	public Empresa() {
		this.facturas = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		this.clienteEmpresas = new ArrayList<>();
		this.rangoNumeracions = new ArrayList<>();
		this.productos = new ArrayList<>();
	}

	public Empresa(int nit, String correoEmpresa, String departamento, String direccion, String documento,
			String logoEmpresa, String municipio, String nombreRepresentante, String numeroDocumento,
			String razonSocial, String telefono, TipoDocumento tipoDocumentoBean) {
		super();
		this.nit = nit;
		this.correoEmpresa = correoEmpresa;
		this.departamento = departamento;
		this.direccion = direccion;
		this.documento = documento;
		this.logoEmpresa = logoEmpresa;
		this.municipio = municipio;
		this.nombreRepresentante = nombreRepresentante;
		this.numeroDocumento = numeroDocumento;
		this.razonSocial = razonSocial;
		this.telefono = telefono;
		this.tipoDocumentoBean = tipoDocumentoBean;
		this.facturas = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		this.clienteEmpresas = new ArrayList<>();
		this.rangoNumeracions = new ArrayList<>();
		this.productos = new ArrayList<>();
	}

	public int getNit() {
		return this.nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getCorreoEmpresa() {
		return this.correoEmpresa;
	}

	public void setCorreoEmpresa(String correoEmpresa) {
		this.correoEmpresa = correoEmpresa;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getLogoEmpresa() {
		return this.logoEmpresa;
	}

	public void setLogoEmpresa(String logoEmpresa) {
		this.logoEmpresa = logoEmpresa;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNombreRepresentante() {
		return this.nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<ClienteEmpresa> getClienteEmpresas() {
		return this.clienteEmpresas;
	}

	public void setClienteEmpresas(List<ClienteEmpresa> clienteEmpresas) {
		this.clienteEmpresas = clienteEmpresas;
	}

	public ClienteEmpresa addClienteEmpresa(ClienteEmpresa clienteEmpresa) {
		getClienteEmpresas().add(clienteEmpresa);
		clienteEmpresa.setEmpresa(this);

		return clienteEmpresa;
	}

	public ClienteEmpresa removeClienteEmpresa(ClienteEmpresa clienteEmpresa) {
		getClienteEmpresas().remove(clienteEmpresa);
		clienteEmpresa.setEmpresa(null);

		return clienteEmpresa;
	}

	public TipoDocumento getTipoDocumentoBean() {
		return this.tipoDocumentoBean;
	}

	public void setTipoDocumentoBean(TipoDocumento tipoDocumentoBean) {
		this.tipoDocumentoBean = tipoDocumentoBean;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setEmpresa(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setEmpresa(null);

		return factura;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setEmpresa(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setEmpresa(null);

		return producto;
	}

	public List<RangoNumeracion> getRangoNumeracions() {
		return this.rangoNumeracions;
	}

	public void setRangoNumeracions(List<RangoNumeracion> rangoNumeracions) {
		this.rangoNumeracions = rangoNumeracions;
	}

	public RangoNumeracion addRangoNumeracion(RangoNumeracion rangoNumeracion) {
		getRangoNumeracions().add(rangoNumeracion);
		rangoNumeracion.setEmpresa(this);

		return rangoNumeracion;
	}

	public RangoNumeracion removeRangoNumeracion(RangoNumeracion rangoNumeracion) {
		getRangoNumeracions().remove(rangoNumeracion);
		rangoNumeracion.setEmpresa(null);

		return rangoNumeracion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEmpresa(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEmpresa(null);

		return usuario;
	}

}