package cole.demo.service;

import cole.demo.dao.CityDao;
import cole.demo.model.City;
import cole.demo.model.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityDao cityDao;
    private boolean initData = false;
    private static final String[] supportCities = {"成都", "北京", "上海", "广州", "深圳"};
    private static final Logger log = LoggerFactory.getLogger(CityService.class);

    public Iterable<City> getAllCity() {
        if (!initData) {
            Long count = cityDao.count();
            if (count == 0) {
                log.info("need init city");
                for (String name : supportCities
                        ) {
                    cityDao.save(new City(name));
                }

            }
            initData = true;
        }
        return cityDao.findAll();
    }

    public void saveCity(City city) {
        cityDao.save(city);
    }

    public List<String> findAllCityNames() {
        return cityDao.findAllCityNames();
    }

    public City findByName(String name) {
        return cityDao.findByName(name);
    }

    public List<City> findByDynamicCases(Query query) {
        Integer maxPM = query.getMaxPM();
        String cityName = query.getCityName();
        return cityDao.findAll((root, cQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (cityName != null && !cityName.equals("") && !cityName.trim().equals("")) {
                Path name = root.get("name");
                predicates.add(cb.equal(name, cityName));
            }
            if (maxPM != null && maxPM != 0) {
                Path pm25 = root.join("weather").join("data").get("pm25");
                predicates.add(cb.lt(pm25, maxPM));
            }
            Predicate[] predicateArray = new Predicate[predicates.size()];
            predicates.toArray(predicateArray);
            cQuery.where(predicateArray);
            return null;
        });


    }


}
