package jp.co.ctc_g.springbatch_sample.hello_world_job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jp/co/ctc_g/springbatch_sample/hello_world_job/hello-world-job.xml")
public class HelloWorldJobTest {

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Test public void helloWorld() throws Exception {
		JobExecution execution = jobLauncher.run(job, new JobParameters());
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		// TODO 06 check the returned JobExecution
		// it should have an ExitStatus.COMPLETED exit status
		
		// TODO 07 run the test!
	}
	
}
