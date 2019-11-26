package brewery.persistence.dao;

import brewery.persistence.entities.BeerStyle;
import brewery.persistence.entities.BusinessFactory;


public interface IBusinessFactory extends IBaseDAO<BusinessFactory, Integer> {
    Integer getBeerSortProductionVolume(BusinessFactory businessFactory, BeerStyle beerStyle);
}
