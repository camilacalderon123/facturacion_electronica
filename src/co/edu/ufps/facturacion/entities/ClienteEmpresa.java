package co.edu.ufps.facturacion.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente_empresa database table.
 * 
 */
@Entity(name="cliente_empresa")
@Table(name="cliente_empresa")
@NamedQuery(name="ClienteEmpresa.findAll", query="SELECT c FROM cliente_empresa c")
public class ClienteEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	private Empresa empresa;

	public ClienteEmpresa() {
	}

	public ClienteEmpresa(Cliente cliente, Empresa empresa) {
		super();
		this.cliente = cliente;
		this.empresa = empresa;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

}