package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.Dao;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Diameter;
import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.domain.impl.ServiceItemPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceItemPriceDao implements Dao<ServiceItemPrice> {


    @Override
    public void save(ServiceItemPrice serviceItemPrice) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.service_item_prices (fk_service_item_id, fk_diameter_id, price) VALUES (?, ?, ?)");

            statement.setInt(1, serviceItemPrice.getServiceItem().getId());
            statement.setInt(2, serviceItemPrice.getDiameter().getId());
            statement.setDouble(3, serviceItemPrice.getPrice());


            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(ServiceItemPrice serviceItemPrice) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.service_item_prices SET fk_service_item_id = ?, fk_diameter_id = ?, price = ? WHERE id = ?");

            statement.setInt(1, serviceItemPrice.getServiceItem().getId());
            statement.setInt(2, serviceItemPrice.getDiameter().getId());
            statement.setDouble(3, serviceItemPrice.getPrice());
            statement.setInt(4, serviceItemPrice.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(ServiceItemPrice serviceItemPrice) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.service_item_prices WHERE id = ?");

            statement.setInt(1, serviceItemPrice.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public ServiceItemPrice loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServiceItemPrice serviceItemPrice = null;
        ServiceItemDao serviceItemDao = new ServiceItemDao();
        DiameterDao diameterDao = new DiameterDao();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_service_item_id, fk_diameter_id, price from tire_service_db.service_item_prices where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                ServiceItem serviceItem = serviceItemDao.loadById(set.getInt("fk_service_item_id"));
                Diameter diameter = diameterDao.loadById(set.getInt("fk_diameter_id"));
                Double price = set.getDouble("price");
                serviceItemPrice = new ServiceItemPrice();
                serviceItemPrice.setId(objectId);
                serviceItemPrice.setServiceItem(serviceItem);
                serviceItemPrice.setDiameter(diameter);
                serviceItemPrice.setPrice(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return serviceItemPrice;
    }

    @Override
    public List<ServiceItemPrice> loadAll() {
        List<ServiceItemPrice> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServiceItemDao serviceItemDao = new ServiceItemDao();
        DiameterDao diameterDao = new DiameterDao();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_service_item_id, fk_diameter_id, price from tire_service_db.service_item_prices");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                ServiceItem serviceItem = serviceItemDao.loadById(set.getInt("fk_service_item_id"));
                Diameter diameter = diameterDao.loadById(set.getInt("fk_diameter_id"));
                Double price = set.getDouble("price");
                ServiceItemPrice serviceItemPrice = new ServiceItemPrice();
                serviceItemPrice.setId(objectId);
                serviceItemPrice.setServiceItem(serviceItem);
                serviceItemPrice.setDiameter(diameter);
                serviceItemPrice.setPrice(price);
                list.add(serviceItemPrice);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}