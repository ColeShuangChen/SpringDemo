package cole.demo.dao;

import cole.demo.model.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface  WeatherDao  extends CrudRepository<Weather, Long> {
}
