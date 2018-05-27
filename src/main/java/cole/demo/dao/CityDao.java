package cole.demo.dao;

import cole.demo.model.City;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface CityDao extends JpaRepository<City, Long> {
    City findByName(String name);
    @Query( nativeQuery=true,value="select name from city")
    List<String> findAllCityNames();
    List<City> findAll(Specification mySpec);

}
