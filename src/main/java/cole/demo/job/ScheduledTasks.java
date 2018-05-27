package cole.demo.job;

import cole.demo.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {
    @Autowired
    private WeatherService weatherService;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate = 5000000)
    //@Scheduled(cron = "0 0 3 * * ?")
    public void requestCityData() {
        log.info("Start query weather data");
        //weatherService.queryWeatherData();
        log.info(" weather data query completely");
    }
}
