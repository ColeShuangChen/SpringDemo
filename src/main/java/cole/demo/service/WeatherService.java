package cole.demo.service;

import cole.demo.model.City;
import cole.demo.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate rest;
    @Autowired
    private CityService cityService;
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);


    public void queryWeatherData()

    {
        Iterable<City> cities = cityService.getAllCity();
        for (City city : cities) {
            String name = city.getName();
            log.info("Query weather data for {0} ", name);
            String url = "https://www.sojson.com/open/api/weather/json.shtml?city=" + name;
            Weather weather = rest.getForObject(url, Weather.class);
            city.setWeather(weather);
            log.info(weather.toString());
            cityService.saveCity(city);
            try {
                //API doesn't allow to request frequently.
                Thread.currentThread().sleep(8000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("weather data load completely");
    }
}
