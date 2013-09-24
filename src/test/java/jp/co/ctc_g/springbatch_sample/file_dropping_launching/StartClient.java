package jp.co.ctc_g.springbatch_sample.file_dropping_launching;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.h2.tools.Server;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClient {
	
	public static final String CLIENT_INPUT_DIRECTORY = "./src/test/resources/jp/co/ctc_g/springbatch_sample/file_dropping_launching/input";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Server.createTcpServer().start();
		File input = new File(CLIENT_INPUT_DIRECTORY);
		if(input.exists()) {
			FileUtils.cleanDirectory(new File(CLIENT_INPUT_DIRECTORY));
		}
		new ClassPathXmlApplicationContext("/jp/co/ctc_g/springbatch_sample/file_dropping_launching/file-dropping-launching-job.xml","/jp/co/ctc_g/springbatch_sample/file_dropping_launching/integration-file.xml");
		System.out.println("Client started.");
	}

}
