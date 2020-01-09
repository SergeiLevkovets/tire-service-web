package by.stormnet.levkovets.dao.jdbc.impl;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.jdbc.Dao;
import by.stormnet.levkovets.domain.impl.ServicePriceCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicePriceCarDao implements Dao<ServicePriceCar> {


    @Override
    public void save(ServicePriceCar servicePriceCar) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.service_price_car (name, price) VALUES (?, ?)");

            statement.setString(1, servicePriceCar.getName());
            statement.setDouble(2, servicePriceCar.getPrice());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(ServicePriceCar servicePriceCar) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.service_price_car SET name = ?, price = ? WHERE id = ?");

            statement.setString(1, servicePriceCar.getName());
            statement.setDouble(2, servicePriceCar.getPrice());
            statement.setInt(3, servicePriceCar.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(ServicePriceCar servicePriceCar) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.service_price_car WHERE id = ?");

            statement.setInt(1, servicePriceCar.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public ServicePriceCar loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServicePriceCar servicePriceCar = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, price from tire_service_db.service_price_car where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Double price = set.getDouble("price");
                servicePriceCar = new ServicePriceCar();
                servicePriceCar.setId(objectId);
                servicePriceCar.setName(name);
                servicePriceCar.setPrice(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return servicePriceCar;
    }

    @Override
    public List<ServicePriceCar> loadAll() {
        List<ServicePriceCar> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, price from tire_service_db.service_price_car");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Double price = set.getDouble("price");
                ServicePriceCar servicePriceCar = new ServicePriceCar();
                servicePriceCar.setId(objectId);
                servicePriceCar.setName(name);
                servicePriceCar.setPrice(price);
                list.add(servicePriceCar);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}