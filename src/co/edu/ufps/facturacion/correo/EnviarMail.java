package co.edu.ufps.facturacion.correo;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class EnviarMail {

	public boolean SendMail(Correo c) {

		/*
		 * Properties props = new Properties(); props.put("mail.smtp.host",
		 * "smtp.gmail.com"); props.setProperty("mail.smtp.starttls.enable", "true");
		 * props.setProperty("mail.smtp.port", "587");
		 * props.setProperty("mail.smtp.auth", "true"); Authenticator auth = new
		 * Authenticator() { public PasswordAuthentication getPasswordAuthentication() {
		 * return new PasswordAuthentication(c.getUsuarioCorreo(), c.getContrasena()); }
		 * };
		 * 
		 * Session s = Session.getDefaultInstance(props, auth); BodyPart texto = new
		 * MimeBodyPart(); texto.setText(c.getMensaje()); BodyPart adjunto = new
		 * MimeBodyPart();
		 * 
		 * if (!c.getRutaArchivo().equals("") && !c.getRutaArchivo1().equals("")) {
		 * adjunto.setDataHandler(new DataHandler(new
		 * FileDataSource(c.getRutaArchivo())));
		 * adjunto.setFileName(c.getNombreArchivo()); } MimeMultipart m = new
		 * MimeMultipart(); m.addBodyPart(texto);
		 * System.out.println("dd "+c.getRutaArchivo()); System.out.println("m"+m); if
		 * (!c.getRutaArchivo().equals("")) { m.addBodyPart(adjunto); }
		 * System.out.println(c.getAdjunto());
		 * 
		 * MimeMessage mensaje = new MimeMessage(s); System.out.println(s);
		 * System.out.println(mensaje); mensaje.setFrom(new
		 * InternetAddress(c.getUsuarioCorreo())); InternetAddress[] toAddresses = { new
		 * InternetAddress(c.getDestino()) };
		 * mensaje.setRecipients(Message.RecipientType.TO, toAddresses);
		 * mensaje.setSubject(c.getAdjunto()); mensaje.setContent(m);
		 * Transport.send(mensaje);
		 * 
		 * return true;
		 */
		try {
			String Host = "smtp.gmail.com";
			String Password = c.getContrasena();
			String from = c.getUsuarioCorreo();
			String toAddress = c.getDestino();
			String filename = c.getRutaArchivo();
			// Get system properties
			Properties props = System.getProperties();
			props.put("mail.smtp.Host", Host);
			props.put("mail.smtps.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getInstance(props, null);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, toAddress);
			message.setSubject(c.getAdjunto());

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(c.getMensaje());

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			Transport tr = session.getTransport("smtps");
			tr.connect(Host, from, Password);
			tr.sendMessage(message, message.getAllRecipients());
			System.out.println("Mail Sent Successfully");
			tr.close();

		} catch (SendFailedException sfe) {

			System.out.println(sfe);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * private void convertirAXML(String nombreArchivo) { System.out.println("bud");
	 * System.out.println("nomrbe archivo " +nombreArchivo); Document document = new
	 * Document(nombreArchivo); System.out.println(document.getFileName());
	 * document.save("recibo.xml", SaveFormat.MobiXml);
	 * System.out.println("bueno acá"); document.close(); }
	 */
	public void enviar(String destino, String mensaje) {

		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString().split("-")[0];
		System.out.println("a");
		String usuarioCorreo = "facturacionpyme123@gmail.com";// correo
		String aux = "pyme12345";// contraseña
		String nombreArchivo = "recibo.txt";
		// String mensaje = "Deseamos hacerle llegar los datos del animal";
		String adjunto = "FACTURACIÓN ELECTRÓNICA";
		File destinoArchivo = new File(nombreArchivo);
		String rutaArchivo = String.valueOf(destinoArchivo);

		Correo c = new Correo(usuarioCorreo, nombreArchivo, aux, rutaArchivo, rutaArchivo, destino, mensaje, adjunto);

		if (this.SendMail(c)) {
			JOptionPane.showMessageDialog(null, "Mensaje Enviado");
		} else {
			JOptionPane.showMessageDialog(null, "Mensaje no Enviado");
		}
	}
}