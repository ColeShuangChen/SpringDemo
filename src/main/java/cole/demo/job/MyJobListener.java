package cole.demo.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        //Job开始前
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        //Job完成后
    }
}
