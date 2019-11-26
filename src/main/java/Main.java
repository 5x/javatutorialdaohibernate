import brewery.persistence.DAOFactory;
import brewery.persistence.dao.IBusinessFactory;
import brewery.persistence.dao.ICity;
import brewery.persistence.entities.BusinessFactory;
import brewery.persistence.entities.City;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        DAOFactory mySqlFactory = DAOFactory.getDAOFactory(DAOFactory.AvailableImplementations.HIBERNATE);
        ICity cityDAO = mySqlFactory.getCityDAO();

        // Create
        City paris = new City("Paris");
        cityDAO.create(paris);

        // Find all
        List<City> cities = cityDAO.findAll();
        for (City city : cities) {
            System.out.println(city.getName());
        }

        Integer firstEntryId = cities.get(0).getId();

        // Get by id
        City otherParis = cityDAO.read(firstEntryId);
        System.out.println(otherParis);

        // Update
        otherParis.setName("PARIS");
        cityDAO.update(otherParis);

        // Delete
        cityDAO.delete(paris.getId());

        // Find all
        cities = cityDAO.findAll();
        for (City city : cities) {
            System.out.println(city.getName());
        }

        IBusinessFactory businessFactoryDAO = mySqlFactory.getBusinessFactoryDAO();
        List<BusinessFactory> businessFactories = businessFactoryDAO.findAll();
        for (BusinessFactory obj : businessFactories) {
            System.out.println(obj);
        }

        mySqlFactory.closeSession();
    }
}
