package cole.demo.job;

import cole.demo.model.City;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

public class ItemProcessor extends ValidatingItemProcessor<City> {
    @Override
    public City process(City city) throws ValidationException {
        super.process(city); //1需执行super.proces：（item）才会调用自定义校验器。
        if (city.getWeather().getStatus()!=200) { //2对数据做简单的处理，若民族为汉族，则数据转换成01，其余转换成02。
            System.out.println("消息获取出错"+city.getName());
        }
        return null;
    }
}
