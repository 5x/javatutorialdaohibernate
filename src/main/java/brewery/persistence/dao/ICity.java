package brewery.persistence.dao;

import brewery.persistence.entities.BusinessFactory;
import brewery.persistence.entities.City;

import java.util.List;


public interface ICity extends IBaseDAO<City, Integer> {
    List<BusinessFactory> getAllBusinessFactories(City city);
    List<City> filteredSearch(Integer id, String name, String order);
}
