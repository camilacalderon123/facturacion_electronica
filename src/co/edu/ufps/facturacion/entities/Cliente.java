package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_documento")
	private int numeroDocumento;

	private String ciudad;

	private String contribuyente;

	private String correo;

	private String departamento;

	private String direccion;

	private byte estado;

	private String nombre;

	@Column(name="nombre_comercial")
	private String nombreComercial;

	private String pais;

	@Column(name="regimen_contable")
	private String regimenContable;

	private String telefono;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tipo_documento")
	private TipoDocumento tipoDocumentoBean;

	//bi-directional many-to-one association to ClienteEmpresa
	@OneToMany(mappedBy="cliente")
	private List<ClienteEmpresa> clienteEmpresas;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="cliente")
	private List<Factura> facturas;

	public Cliente() {
	}

	public Cliente(int numeroDocumento, String ciudad, String contribuyente, String correo, String departamento,
			String direccion, byte estado, String nombre, String nombreComercial, String pais, String regimenContable,
			String telefono, TipoDocumento tipoDocumentoBean) {
		super();
		this.numeroDocumento = numeroDocumento;
		this.ciudad = ciudad;
		this.contribuyente = contribuyente;
		this.correo = correo;
		this.departamento = departamento;
		this.direccion = direccion;
		this.estado = estado;
		this.nombre = nombre;
		this.nombreComercial = nombreComercial;
		this.pais = pais;
		this.regimenContable = regimenContable;
		this.telefono = telefono;
		this.tipoDocumentoBean = tipoDocumentoBean;
		this.facturas = new ArrayList<>();
	}

	public int getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getContribuyente() {
		return this.contribuyente;
	}

	public void setContribuyente(String contribuyente) {
		this.contribuyente = contribuyente;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public String getNombreComercial() {
		return this.nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRegimenContable() {
		return this.regimenContable;
	}

	public void setRegimenContable(String regimenContable) {
		this.regimenContable = regimenContable;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TipoDocumento getTipoDocumentoBean() {
		return this.tipoDocumentoBean;
	}

	public void setTipoDocumentoBean(TipoDocumento tipoDocumentoBean) {
		this.tipoDocumentoBean = tipoDocumentoBean;
	}

	public List<ClienteEmpresa> getClienteEmpresas() {
		return this.clienteEmpresas;
	}

	public void setClienteEmpresas(List<ClienteEmpresa> clienteEmpresas) {
		this.clienteEmpresas = clienteEmpresas;
	}

	public ClienteEmpresa addClienteEmpresa(ClienteEmpresa clienteEmpresa) {
		getClienteEmpresas().add(clienteEmpresa);
		clienteEmpresa.setCliente(this);

		return clienteEmpresa;
	}

	public ClienteEmpresa removeClienteEmpresa(ClienteEmpresa clienteEmpresa) {
		getClienteEmpresas().remove(clienteEmpresa);
		clienteEmpresa.setCliente(null);

		return clienteEmpresa;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setCliente(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setCliente(null);

		return factura;
	}

}