package co.edu.ufps.facturacion.entities;

public class Articulo {

	private Producto producto;
	private int cantidad;

	public Articulo() {
		super();
	}

	public Articulo(Producto p, int cantidad) {
		super();
		this.producto = p;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
