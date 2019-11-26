package brewery.persistence.mysql;

import brewery.persistence.dao.IBusinessFactory;
import brewery.persistence.entities.BeerStyle;
import brewery.persistence.entities.BusinessFactory;
import brewery.persistence.entities.City;
import dao.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BusinessFactoryDAO extends AbstractDAO implements IBusinessFactory {

    public BusinessFactoryDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<BusinessFactory> findAll() {
        try {
            Statement statement = this.getConnection().createStatement();
            String sql = "SELECT f.factory_id, f.title, c.city_id, c.name" +
                    " FROM factory f JOIN city c ON f.city = c.city_id;";
            ResultSet rs = statement.executeQuery(sql);
            List<BusinessFactory> entries = new ArrayList<>();
            while (rs.next()) {
                Integer cityId = rs.getInt("c.city_id");
                String cityName = rs.getString("c.name");
                City city = new City(cityId, cityName);
                Integer factoryId = rs.getInt("f.factory_id");
                String title = rs.getString("f.title");
                entries.add(new BusinessFactory(factoryId, city, title));
            }

            rs.close();

            return entries;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public BusinessFactory read(Integer id) {
        return null;
    }

    @Override
    public void update(BusinessFactory entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Integer create(BusinessFactory entity) {
        return null;
    }

    @Override
    public Integer getBeerSortProductionVolume(BusinessFactory businessFactory, BeerStyle beerStyle) {
        Objects.requireNonNull(businessFactory);
        Objects.requireNonNull(beerStyle);

        return null;
    }
}