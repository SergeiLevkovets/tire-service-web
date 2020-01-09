package by.stormnet.levkovets.dao.jdbc.impl;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.jdbc.Dao;
import by.stormnet.levkovets.domain.impl.ServicePriceTruck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class servicePriceTruckDao implements Dao<ServicePriceTruck> {


    @Override
    public void save(ServicePriceTruck servicePriceTruck) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.service_price_truck (name, price) VALUES (?, ?)");

            statement.setString(1, servicePriceTruck.getName());
            statement.setDouble(2, servicePriceTruck.getPrice());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(ServicePriceTruck servicePriceTruck) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.service_price_truck SET name = ?, price = ? WHERE id = ?");

            statement.setString(1, servicePriceTruck.getName());
            statement.setDouble(2, servicePriceTruck.getPrice());
            statement.setInt(3, servicePriceTruck.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(ServicePriceTruck servicePriceTruck) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.service_price_truck WHERE id = ?");

            statement.setInt(1, servicePriceTruck.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public ServicePriceTruck loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServicePriceTruck servicePriceTruck = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, price from tire_service_db.service_price_truck where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Double price = set.getDouble("price");
                servicePriceTruck = new ServicePriceTruck();
                servicePriceTruck.setId(objectId);
                servicePriceTruck.setName(name);
                servicePriceTruck.setPrice(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return servicePriceTruck;
    }

    @Override
    public List<ServicePriceTruck> loadAll() {
        List<ServicePriceTruck> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, price from tire_service_db.service_price_truck");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Double price = set.getDouble("price");
                ServicePriceTruck servicePriceTruck = new ServicePriceTruck();
                servicePriceTruck.setId(objectId);
                servicePriceTruck.setName(name);
                servicePriceTruck.setPrice(price);
                list.add(servicePriceTruck);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}