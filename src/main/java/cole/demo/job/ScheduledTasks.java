package cole.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importJob;

    @Scheduled(fixedRate = 5000000)
    //@Scheduled(cron = "0 0 3 * * ?")
    public void requestCityData() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("Start query weather data");
        String dateParam = new Date().toString();
        JobParameters param =
                new JobParametersBuilder().addString("date", dateParam).toJobParameters();

        log.info("start date is " + dateParam);
        JobExecution execution = jobLauncher.run(importJob, param);
        //weatherService.queryWeatherData();
        log.info(" weather data query completely");
    }
}
