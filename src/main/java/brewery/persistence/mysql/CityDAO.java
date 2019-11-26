package brewery.persistence.mysql;


import brewery.persistence.dao.ICity;
import brewery.persistence.entities.BusinessFactory;
import brewery.persistence.entities.City;
import dao.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CityDAO extends AbstractDAO implements ICity {

    public CityDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<City> filteredSearch(Integer id, String name, String order) {
        return null;
    }

    @Override
    public List<City> findAll() {
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM city;");
            List<City> entries = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                Integer cityId = rs.getInt("city_id");
                entries.add(new City(cityId, name));
            }

            rs.close();

            return entries;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public Integer create(City entity) {
        Integer generatedIdKey;

        try {
            PreparedStatement statement = this.getConnection().prepareStatement(
                    "INSERT INTO city (`name`) VALUES (?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, entity.getName());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating entry failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedIdKey = generatedKeys.getInt(1);
                    entity.setId(generatedIdKey);
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }

            statement.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return generatedIdKey;
    }

    @Override
    public City read(Integer id) {
        try {
            String sql = "SELECT name FROM city WHERE city_id=? LIMIT 1;";
            PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                preparedStatement.close();

                return new City(id, name);
            } else {
                throw new IllegalArgumentException("No such entry with given id in DB.");
            }

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void update(City entity) {
        Integer entityId = entity.getId();
        if (Objects.isNull(entityId)) {
            create(entity);
            return;
        }

        try {
            String sql = "UPDATE city SET name=? WHERE city_id=? LIMIT 1;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating entry failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String sql = "DELETE FROM city WHERE city_id=? LIMIT 1;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    @Override
    public List<BusinessFactory> getAllBusinessFactories(City city) {
        return null;
    }
}
