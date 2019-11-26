package brewery.persistence.dao;

import brewery.persistence.entities.BeerStyle;
import brewery.persistence.entities.FactoryUnit;


public interface IFactoryUnit extends IBaseDAO<Integer, FactoryUnit> {
    Integer getBeerSortProductionVolume(FactoryUnit factoryUnit, BeerStyle beerStyle);
}
