package emailtest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

// use any SOAP service. This one was generated using
//   wsimport -d bin -s src -keep -extension http://www.webservicex.net/stockquote.asmx?wsdl
import net.webservicex.StockQuote;
import net.webservicex.StockQuoteSoap;

public class Emailtest {

	public static void stockQuoteTest() {
		StockQuote stockService = new StockQuote();
		StockQuoteSoap s = stockService.getStockQuoteSoap12();
		// We don't actually need to call SOAP to demonstrate the problem
		// System.out.println("quote is "+s.getQuote("GOOG"));
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
			
			stockQuoteTest();
			
			System.out.println("After call to SOAP:");
			testMime();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
