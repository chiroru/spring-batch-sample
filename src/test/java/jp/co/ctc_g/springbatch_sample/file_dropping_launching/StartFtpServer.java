package jp.co.ctc_g.springbatch_sample.file_dropping_launching;

/**
 * @author acogoluegnes
 *
 */
public class StartFtpServer {

	public static void main(String [] args) throws Exception {
		EmbeddedFtpServer.start();
		System.out.println("FTP started, press Enter to stop it...");
		System.in.read();
		EmbeddedFtpServer.stop();
		System.out.println("FTP stopped.");
	}
	
}
