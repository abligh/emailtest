package emailtest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

import javax.xml.namespace.QName;

import com.tigerlily.client.Cluster;
import com.tigerlily.client.ClusterService;

public class Emailtest {

	public static void startClusterService() {

		Cluster clusterClient = null;
		URL url = ClassLoader.getSystemClassLoader().getResource(
				"ClusterService.wsdl");
		
		ClusterService tl = new ClusterService(url, new QName(
				"http://service.tl.com/", "ClusterService"));

		clusterClient = tl.getClusterPort();
	}

	public static void testMime() throws MessagingException {
		String msg = "\u0287x\u01DD\u0287 u\u028Dop \u01DDp\u1D09sdn";
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("" + msg, "utf-8");
		messageBodyPart.setHeader("Content-Type", "text/plain; charset=\"utf-8\"");
		messageBodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			messageBodyPart.writeTo(os);
			String aString = new String(os.toByteArray(),"UTF-8");
			System.out.println(aString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	public static void main(String args[]) {
		try {
			System.out.println("Before call to SOAP:");
			testMime();
			System.out.println("After call to SOAP:");
			startClusterService();
			testMime();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
