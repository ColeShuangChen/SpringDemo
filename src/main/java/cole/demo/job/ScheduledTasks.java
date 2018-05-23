package cole.demo.job;

import cole.demo.dao.WeatherDao;
import cole.demo.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Value("${location.supportCities}")
    private List<String> supportCities;
    @Autowired
    private RestTemplate rest;
    @Autowired
    private WeatherDao weatherDao;
    @Scheduled(fixedRate = 5000000)
    //@Scheduled(cron = "0 0 3 * * ?")
    public void requestCityData() {
        log.info("The time is now {}, start to get city data", dateFormat.format(new Date()));
        String url="https://www.sojson.com/open/api/weather/json.shtml?city=北京";
        Weather weather= rest.getForObject(url, Weather.class);
        log.info(weather.toString());
        weatherDao.save(weather);
    }
}
