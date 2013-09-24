package jp.co.ctc_g.springbatch_sample.dynamic_job_parameters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jp/co/ctc_g/springbatch_sample/dynamic_job_parameters/dynamic-job-parameters-job.xml")
public class DynamicJobParametersJobTest {

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
	}
	
	@Test public void dynamicJobParameters() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
			.addString("input.file", "file:./src/main/resources/jp/co/ctc_g/springbatch_sample/dynamic_job_parameters/contacts-01.txt")
			.toJobParameters();
		JobExecution execution = jobLauncher.run(job, jobParameters);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(7,jdbcTemplate.queryForInt("select count(1) from contact"));
		
		jobParameters = new JobParametersBuilder()
			.addString("input.file", "file:./src/main/resources/jp/co/ctc_g/springbatch_sample/dynamic_job_parameters/contacts-02.txt")
			.toJobParameters();
		execution = jobLauncher.run(job, jobParameters);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(13,jdbcTemplate.queryForInt("select count(1) from contact"));
	}
	
}
