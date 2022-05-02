package co.edu.ufps.facturacion.correo;

import java.io.File;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class GenerarQRCode {
	
	public GenerarQRCode() throws Exception {
		QRCode qrCode = new QRCode();
		qrCode.setData("https://divisist2.ufps.edu.co/");
		qrCode.setHeight(200);
		qrCode.setSize(200);
		qrCode.setFormato("png");
		File f =new File("QR.png");
		qrCode.setPath(f.getAbsolutePath());
		BitMatrix matrix= new MultiFormatWriter().encode(qrCode.getData(),BarcodeFormat.QR_CODE,qrCode.getHeight(),qrCode.getSize());
		//Formato de la imagen y la ruta
		MatrixToImageWriter.writeToPath(matrix, qrCode.getFormato(), Paths.get(qrCode.getPath()));
	}
}


