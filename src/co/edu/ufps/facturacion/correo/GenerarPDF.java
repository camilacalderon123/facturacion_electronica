package co.edu.ufps.facturacion.correo;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import co.edu.ufps.facturacion.entities.Articulo;
import co.edu.ufps.facturacion.entities.Cliente;
import co.edu.ufps.facturacion.entities.DetalleFactura;
import co.edu.ufps.facturacion.entities.Factura;
import co.edu.ufps.facturacion.entities.Producto;
import co.edu.ufps.facturacion.entities.RangoNumeracion;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GenerarPDF {

	public GenerarPDF() {

	}

	public boolean generarPDF(String nombreArchivo, File img, Cliente cl, Factura fa, List<Articulo> articulos,
			RangoNumeracion rg) throws FileNotFoundException, IOException, InterruptedException {

		FileOutputStream f = new FileOutputStream(nombreArchivo);// nombre pdf

		PdfWriter writer = new PdfWriter(f);
		PdfDocument pdfDoc = new PdfDocument(writer);

		Document document = new Document(pdfDoc, PageSize.A3);
		document.setMargins(50, 30, 20, 30);
		PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
		PdfFont font1 = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

		try {

			System.out.println("a");
			Paragraph hue = new Paragraph("Facturación Electrónica - SolTec").setFont(font1);
			hue.setTextAlignment(TextAlignment.CENTER);
			hue.setFontSize(26f);
			System.out.println("n");
			document.add(hue);
			GenerarQRCode gc = new GenerarQRCode();

			ImageData data = ImageDataFactory.create(img.getAbsolutePath());
			Image imga = new Image(data).setFontSize(28f);
			// Adding paragraphs to document

			document.add(imga.setFixedPosition(30, 1000));

			tablaInformacion(font, font1, document, fa, cl, rg);
			ImageData data1 = ImageDataFactory.create(new File("QR.png").getAbsolutePath());
			document.add(new Image(data1).setFixedPosition(630, 980));
			tablaProductos(font, document, articulos, fa);
			pieDePagina(font, font1, document);
			document.close();

		} catch (Exception ex) {
			Logger.getLogger(GenerarPDF.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

		return new File(nombreArchivo).exists();
	}

	public void abrirPDF(String nombreArchivo) throws IOException {

		int respuesta = JOptionPane.showConfirmDialog(null, "Desea Imprimir PDF", "Seguimiento",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == 0) {
			File f = new File(nombreArchivo);

			Desktop.getDesktop().open(f);
		} else {
			JOptionPane.showMessageDialog(null, "No se abrirá el PDF");
		}
	}
	
	@SuppressWarnings("deprecation")
	private void tablaInformacion(PdfFont font, PdfFont font1, Document document, Factura f, Cliente cl,
			RangoNumeracion rg) {

		llenarDatos(font, font1, document, f, rg);
		String valores[] = { "Razón social/Nombre: ", "NIT: ", "Dirección: ", "Teléfono: ", "País: ", "Email: ",
				"Ciudad: ", "Tipo Persona: " };
		String val[] = { cl.getNombreComercial(), cl.getNumeroDocumento() + "", cl.getDireccion(), cl.getTelefono(),
				cl.getPais(), cl.getCorreo(), cl.getCiudad() + ", " + cl.getDepartamento(), cl.getContribuyente() };

		Table tabla1 = new Table(1);// genera tabla
		tabla1.addHeaderCell("DATOS CLIENTE").setBackgroundColor(Color.LIGHT_GRAY);
		document.add(tabla1);

		tabla1.setWidthPercent(50);
		Table tabla2 = new Table(4);

		for (int j = 0; j < valores.length; j++) {
			tabla2.addCell(valores[j]).setFont(font);
			tabla2.addCell(val[j]).setFont(font1);
		}
		document.add(tabla2.setMarginBottom(20));
		tabla2.setWidthPercent(50);
	}

	@SuppressWarnings("deprecation")
	private void tablaProductos(PdfFont font, Document document, List<Articulo> articulos, Factura fa) {

		Table tabla = new Table(9);// genera tabla
		tabla.addHeaderCell("Ítem").setBackgroundColor(Color.LIGHT_GRAY);// ENCABEZADO DE TABLA COLOR
		tabla.addHeaderCell("Codigo del producto");
		tabla.addHeaderCell("Nombre");
		tabla.addHeaderCell("U/M");
		tabla.addHeaderCell("Valor unitario");
		tabla.addHeaderCell("Cant.");
		tabla.addHeaderCell("% Desc");
		tabla.addHeaderCell("IVA");
		tabla.addHeaderCell("Subtotal");
		document.add(tabla);
		tabla.setWidthPercent(90);
		tabla = new Table(9);
		int i=1;
		for (Articulo a : articulos) {// lista
			Producto p= a.getProducto();
			tabla.addCell(i+"").setFont(font);
			tabla.addCell(p.getCodigo()+"").setFont(font);
			tabla.addCell(p.getNombre()+"").setFont(font);
			tabla.addCell(p.getUnidadMedia()+"").setFont(font);
			tabla.addCell("$"+p.getValorUnitario()+"").setFont(font);
			tabla.addCell(a.getCantidad()+"").setFont(font);
			tabla.addCell(p.getPorcentajeDescuento()+"%").setFont(font);
			tabla.addCell("IVA ("+p.getIva()+"%)").setFont(font);
			tabla.addCell("$"+(p.getValorUnitario()*a.getCantidad())+"").setFont(font);
			i++;
		}
		document.add(tabla.setMarginBottom(20));
		tabla.setWidthPercent(50);
		tablaPrecios(font, document, fa);
	}

	@SuppressWarnings("deprecation")
	private void tablaPrecios(PdfFont font, Document document, Factura f) {

		Table tabla1 = new Table(1);// genera tabla
		tabla1.addHeaderCell("MONEDA (COP) PESOS COLOMBIANOS").setBackgroundColor(Color.LIGHT_GRAY);
		String valores[] = { "Subtotal", "Descuento total", "IVA (19%)", "TOTAL" };
		String val[] = { "$"+f.getValorNeto(), "$"+f.getTotalDescuento(), "$"+f.getTotalIva(), "$"+f.getTotalPagar()};

		document.add(tabla1);
		tabla1.setWidthPercent(50);
		Table tabla2 = new Table(2);

		for (int j = 0; j < valores.length - 1; j++) {
			tabla2.addCell(valores[j]).setFont(font);
			tabla2.addCell(val[j]).setFont(font);
		}
		document.add(tabla2);
		tabla2.setWidthPercent(50);

		tabla2 = new Table(2);
		tabla2.addCell(valores[valores.length - 1]).setFont(font).setBackgroundColor(Color.LIGHT_GRAY);
		tabla2.addCell(val[val.length - 1]).setFont(font);
		
		document.add(tabla2);
		tabla2.setWidthPercent(50);
	}

	

	private void llenarDatos(PdfFont font, PdfFont font1, Document document, Factura fa, RangoNumeracion rg) {
		String resolucion = "Resolución DIAN N° 118764018183277 con Prefijo " + rg.getPrefijo() + " desde "
				+ rg.getNumeroDesde() + " hasta " + rg.getNumeroHasta() + " con vigencia desde "
				+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(rg.getFechaResolucion());
		Paragraph fecha = new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()))
				.setFont(font1);
		Paragraph numFactura = new Paragraph("Factura Electrónica de Venta N° " + rg.getPrefijo() + "-"
				+ fa.getRangoNumeracionBean().getNumeroActual()).setFont(font1);
		Paragraph re = new Paragraph(resolucion).setFont(font);
		re.setFontSize(10f);
		document.add(new Paragraph("\n\n\n\n\n\n"));
		System.out.println(fa.getCufe());
		Paragraph CUFE = new Paragraph("CUFE: " + org.apache.commons.codec.digest.DigestUtils.sha1Hex(fa.getCufe()))
				.setFont(font1);
		document.add(numFactura.setTextAlignment(TextAlignment.CENTER));
		document.add(fecha.setTextAlignment(TextAlignment.CENTER));
		document.add(re.setTextAlignment(TextAlignment.CENTER));
		document.add(CUFE.setTextAlignment(TextAlignment.RIGHT));
	}

	private void pieDePagina(PdfFont font, PdfFont font1, Document document) {
		String resolucion = "A PARTIR DEL 1 DE ENERO DEL 2020 SOMOS BENEFICIARIOS DE LA LEY 1955 DEL 25 DE MAYO DEL 2019 Y SUS D.R. (ZESE) POR LO CUAL"
				+ " ABSTENERSE PRACTICAR RETEFUENTE DE F.C: 05-12-2015\r\n";
		Paragraph constar = new Paragraph(
				"Se hace constar que la firma de una persona distinta del comprador , implica que dicha persona esta autorizada expresamente por el comprador para"
						+ " firmar , confesar la deuda y obligar al comprador\r\n\r\n"
						+ "Esta factura se asimila en todos sus efectos legales a una letra de cambio (Art. 774 del Codigo de Comercio)\r\n"
						+ "Avenida Gran Colombia # 12E - 96 Barrio Colsag\r\n" + "CÚCUTA - NORTE DE SANTANDER\r\n"
						+ "Tel. +57 314 2190138\r\n" + "Correo electronico: facturacionpyme123@gmail.com\r\n" + "")
								.setFont(font);
		Paragraph re = new Paragraph(resolucion).setFont(font);
		Paragraph soltec = new Paragraph("SOLTEC").setFont(font);
		Paragraph firma = new Paragraph("____________________________________\r\n" + "Firma recibido de cliente")
				.setFont(font1);
		re.setFontSize(10f);
		firma.setFontSize(12f);
		soltec.setFontSize(10f);
		firma.setMarginTop(250);

		document.add(re.setTextAlignment(TextAlignment.CENTER));
		document.add(firma.setTextAlignment(TextAlignment.CENTER));
		document.add(constar.setTextAlignment(TextAlignment.CENTER));
		document.add(soltec.setTextAlignment(TextAlignment.RIGHT));
	}

}