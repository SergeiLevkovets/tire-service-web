package by.stormnet.levkovets.dao.jdbc.impl;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.jdbc.Dao;
import by.stormnet.levkovets.domain.impl.ServicePriceSuv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class servicePriceSuvDao implements Dao<ServicePriceSuv> {


    @Override
    public void save(ServicePriceSuv servicePriceSuv) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.service_price_suv (name, price) VALUES (?, ?)");

            statement.setString(1, servicePriceSuv.getName());
            statement.setDouble(2, servicePriceSuv.getPrice());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(ServicePriceSuv servicePriceSuv) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.service_price_suv SET name = ?, price = ? WHERE id = ?");

            statement.setString(1, servicePriceSuv.getName());
            statement.setDouble(2, servicePriceSuv.getPrice());
            statement.setInt(3, servicePriceSuv.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(ServicePriceSuv servicePriceSuv) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.service_price_suv WHERE id = ?");

            statement.setInt(1, servicePriceSuv.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public ServicePriceSuv loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServicePriceSuv servicePriceSuv = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, price from tire_service_db.service_price_suv where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Double price = set.getDouble("price");
                servicePriceSuv = new ServicePriceSuv();
                servicePriceSuv.setId(objectId);
                servicePriceSuv.setName(name);
                servicePriceSuv.setPrice(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return servicePriceSuv;
    }

    @Override
    public List<ServicePriceSuv> loadAll() {
        List<ServicePriceSuv> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, price from tire_service_db.service_price_suv");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Double price = set.getDouble("price");
                ServicePriceSuv servicePriceSuv = new ServicePriceSuv();
                servicePriceSuv.setId(objectId);
                servicePriceSuv.setName(name);
                servicePriceSuv.setPrice(price);
                list.add(servicePriceSuv);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}