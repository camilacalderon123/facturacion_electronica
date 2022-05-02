package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cufe;

	private byte estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_expedicion")
	private Date fechaExpedicion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;

	@Lob
	private String firma;

	@Column(name = "total_descuento")
	private double totalDescuento;

	@Column(name = "total_iva")
	private double totalIva;

	@Column(name = "total_pagar")
	private double totalPagar;

	@Column(name = "valor_neto")
	private double valorNeto;

	// bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy = "factura")
	private List<DetalleFactura> detalleFacturas;

	// bi-directional many-to-one association to Documento
	@OneToMany(mappedBy = "factura")
	private List<Documento> documentos;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	// bi-directional many-to-one association to Empresa
	@ManyToOne
	private Empresa empresa;

	// bi-directional many-to-one association to RangoNumeracion
	@ManyToOne
	@JoinColumn(name = "rango_numeracion")
	private RangoNumeracion rangoNumeracionBean;

	// bi-directional many-to-one association to Nota
	@OneToMany(mappedBy = "factura")
	private List<Nota> notas;

	public Factura() {
		this.notas = new ArrayList<>();
		this.detalleFacturas = new ArrayList<>();
		this.documentos = new ArrayList<>();
	}

	public Factura(byte estado, Date fechaExpedicion, Date fechaVencimiento, String firma, double totalDescuento,
			double valorNeto, double totalPagar, double totalIva, Cliente cliente, Empresa empresa,
			RangoNumeracion rangoNumeracionBean) {
		super();
		this.estado = estado;
		this.fechaExpedicion = fechaExpedicion;
		this.fechaVencimiento = fechaVencimiento;
		this.firma = firma;
		this.totalDescuento = totalDescuento;
		this.totalPagar = totalPagar;
		this.valorNeto = valorNeto;// total a pagar
		this.totalIva = totalIva;
		this.cliente = cliente;
		this.empresa = empresa;
		this.rangoNumeracionBean = rangoNumeracionBean;
		this.notas = new ArrayList<>();
		this.detalleFacturas = new ArrayList<>();
		this.documentos = new ArrayList<>();
	}

	public void generarCufe() {
		String cufe = "";

		cufe += ("" + this.getRangoNumeracionBean().getNumeroActual() // NumFactura
				+ this.convertirFecha(this.getFechaExpedicion()) //
				+ this.getValorNeto() + this.getTotalDescuento() + this.getEmpresa().getNit() // NIT factura);
				+ this.getCliente().getNumeroDocumento() + this.getCliente().getContribuyente()
				+ this.getRangoNumeracionBean().getNumeroResolucion());

		this.setCufe(org.apache.commons.codec.digest.DigestUtils.sha1Hex(cufe));
	}

	public String convertirFecha(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("YYYYmmddHHMMss");
		return formatter.format(fecha);
	}

	public String getCufe() {
		return this.cufe;
	}

	public void setCufe(String cufe) {
		this.cufe = cufe;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public Date getFechaExpedicion() {
		return this.fechaExpedicion;
	}

	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFirma() {
		return this.firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public double getTotalDescuento() {
		return this.totalDescuento;
	}

	public void setTotalDescuento(double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public double getTotalIva() {
		return this.totalIva;
	}

	public void setTotalIva(double totalIva) {
		this.totalIva = totalIva;
	}

	public double getTotalPagar() {
		return this.totalPagar;
	}

	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public double getValorNeto() {
		return this.valorNeto;
	}

	public void setValorNeto(double valorNeto) {
		this.valorNeto = valorNeto;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setFactura(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setFactura(null);

		return detalleFactura;
	}

	public List<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public Documento addDocumento(Documento documento) {
		getDocumentos().add(documento);
		documento.setFactura(this);

		return documento;
	}

	public Documento removeDocumento(Documento documento) {
		getDocumentos().remove(documento);
		documento.setFactura(null);

		return documento;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public RangoNumeracion getRangoNumeracionBean() {
		return this.rangoNumeracionBean;
	}

	public void setRangoNumeracionBean(RangoNumeracion rangoNumeracionBean) {
		this.rangoNumeracionBean = rangoNumeracionBean;
	}

	public List<Nota> getNotas() {
		return this.notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}

	public Nota addNota(Nota nota) {
		getNotas().add(nota);
		nota.setFactura(this);

		return nota;
	}

	public Nota removeNota(Nota nota) {
		getNotas().remove(nota);
		nota.setFactura(null);

		return nota;
	}

}