package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the documentos database table.
 * 
 */
@Entity
@Table(name="documentos")
@NamedQuery(name="Documento.findAll", query="SELECT d FROM Documento d")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_documentos")
	private int idDocumentos;

	@Column(name="archivo_pdf")
	private String archivoPdf;

	@Lob
	private String descripcion;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	private Factura factura;

	public Documento() {
	}

	public int getIdDocumentos() {
		return this.idDocumentos;
	}

	public void setIdDocumentos(int idDocumentos) {
		this.idDocumentos = idDocumentos;
	}

	public String getArchivoPdf() {
		return this.archivoPdf;
	}

	public void setArchivoPdf(String archivoPdf) {
		this.archivoPdf = archivoPdf;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

}