package jp.co.ctc_g.springbatch_sample.file_dropping_launching;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.to;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"/jp/co/ctc_g/springbatch_sample/file_dropping_launching/file-dropping-launching-job.xml","/jp/co/ctc_g/springbatch_sample/file_dropping_launching/integration-file.xml"
})
public class FileDroppingLaunchingJobTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Job job;
	
	@Autowired
	private JobExplorer jobExplorer;
	
	private static Server db;
	
	@BeforeClass public static void init() throws Exception {
		EmbeddedFtpServer.start();
		File input = new File(StartClient.CLIENT_INPUT_DIRECTORY);
		if(input.exists()) {
			FileUtils.cleanDirectory(new File(StartClient.CLIENT_INPUT_DIRECTORY));
		}
		db = Server.createTcpServer().start();
	}
	
	@AfterClass public static void close() {
		EmbeddedFtpServer.stop();
		db.stop();
	}
	
	@Before public void setUp() throws Exception {
		jdbcTemplate.update("delete from contact");
	}
	
	@Test public void fileDroppingLaunching() throws Exception {
	    System.out.println("now count : [" + jdbcTemplate.queryForInt("select count(1) from contact") + "]");
		assertEquals(0, jobExplorer.getJobInstances(job.getName(), 0, Integer.MAX_VALUE).size());
		//copyFileToFtpHome();
		await().untilCall(to(jdbcTemplate).queryForInt("select count(1) from contact"),equalTo(5));
		assertEquals(1, jobExplorer.getJobInstances(job.getName(), 0, Integer.MAX_VALUE).size());
		System.out.println("now count : [" + jdbcTemplate.queryForInt("select count(1) from contact") + "]");
	}

	private void copyFileToFtpHome() throws IOException {
		FileUtils.copyFileToDirectory(new File("./src/main/resources/jp/co/ctc_g/springbatch_sample/file_dropping_launching/contacts.txt"), new File(EmbeddedFtpServer.FTP_HOME_DIRECTORY));
	}
	
}
