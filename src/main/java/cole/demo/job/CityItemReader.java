package cole.demo.job;

import cole.demo.model.City;
import cole.demo.model.Weather;
import cole.demo.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;

public class CityItemReader implements ItemReader<City> {

    private CityService cityService;
    private RestTemplate rest;
    Iterator<City> cities;

    private static final Logger log = LoggerFactory.getLogger(CityItemReader.class);

    public CityItemReader() {
    }

    public CityItemReader(CityService cityService, RestTemplate rest) {
        this.cityService = cityService;
        this.rest = rest;
    }

    public CityService getCityService() {
        return cityService;
    }

    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public RestTemplate getRest() {
        return rest;
    }

    public void setRest(RestTemplate rest) {
        this.rest = rest;
    }

    @Override
    public City read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (cities == null) {
            cities = cityService.getAllCity().iterator();
        }
        if (cities.hasNext()) {
            City city = cities.next();
            String name = city.getName();
            log.info("Query weather data for {0} ", name);
            String url = "https://www.sojson.com/open/api/weather/json.shtml?city=" + name;
            Weather weather = rest.getForObject(url, Weather.class);
            city.setWeather(weather);
            log.info(weather.toString());
            try {
                //API doesn't allow to request frequently.
                Thread.currentThread().sleep(8000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return city;
        } else {
            cities = cityService.getAllCity().iterator();
            return null;
        }

    }
}
