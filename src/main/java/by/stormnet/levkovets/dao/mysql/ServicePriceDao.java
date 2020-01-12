package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.Dao;
import by.stormnet.levkovets.domain.impl.ServicePrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicePriceDao implements Dao<ServicePrice> {


    @Override
    public void save(ServicePrice servicePrice) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.service_price (name, price) VALUES (?, ?)");

            statement.setString(1, servicePrice.getName());
            statement.setDouble(2, servicePrice.getPrice());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(ServicePrice servicePrice) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.service_price SET name = ?, price = ? WHERE id = ?");

            statement.setString(1, servicePrice.getName());
            statement.setDouble(2, servicePrice.getPrice());
            statement.setInt(3, servicePrice.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(ServicePrice servicePrice) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.service_price WHERE id = ?");

            statement.setInt(1, servicePrice.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public ServicePrice loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServicePrice servicePrice = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, price from tire_service_db.service_price where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Double price = set.getDouble("price");
                servicePrice = new ServicePrice();
                servicePrice.setId(objectId);
                servicePrice.setName(name);
                servicePrice.setPrice(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return servicePrice;
    }

    @Override
    public List<ServicePrice> loadAll() {
        List<ServicePrice> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, price from tire_service_db.service_price");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Double price = set.getDouble("price");
                ServicePrice servicePrice = new ServicePrice();
                servicePrice.setId(objectId);
                servicePrice.setName(name);
                servicePrice.setPrice(price);
                list.add(servicePrice);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}