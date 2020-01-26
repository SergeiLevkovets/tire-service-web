package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.DiameterDAO;
import by.stormnet.levkovets.dao.ServiceItemDAO;
import by.stormnet.levkovets.dao.ServiceItemPriceDAO;
import by.stormnet.levkovets.dao.TypeDAO;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Diameter;
import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.domain.impl.ServiceItemPrice;
import by.stormnet.levkovets.domain.impl.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class ServiceItemPriceDAOImpl implements ServiceItemPriceDAO {


    @Override
    public void save(ServiceItemPrice serviceItemPrice) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.service_item_prices (fk_service_item_id, fk_type_id, fk_diameter_id, price) VALUES (?, ?, ?, ?)");

            statement.setInt(1, serviceItemPrice.getServiceItem().getId());
            statement.setInt(2, serviceItemPrice.getType().getId());
            if (serviceItemPrice.getDiameter() == null) {
                statement.setNull(3, NULL);
            }else {
                statement.setInt(3, serviceItemPrice.getDiameter().getId());
            }
            statement.setDouble(4, serviceItemPrice.getPrice());


            statement.executeUpdate();
        } catch (SQLException e) {
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
            statement = c.prepareStatement("UPDATE tire_service_db.service_item_prices SET fk_service_item_id = ?, fk_type_id = ?, fk_diameter_id = ?, price = ? WHERE id = ?");

            statement.setInt(1, serviceItemPrice.getServiceItem().getId());
            statement.setInt(2, serviceItemPrice.getType().getId());
            if (serviceItemPrice.getDiameter() == null) {
                statement.setNull(3, NULL);
            }else {
                statement.setInt(3, serviceItemPrice.getDiameter().getId());
            }
            statement.setDouble(4, serviceItemPrice.getPrice());
            statement.setInt(5, serviceItemPrice.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.service_item_prices WHERE id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
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
        ServiceItemDAO serviceItemDao = new ServiceItemDAOImpl();
        TypeDAO typeDao = new TypeDAOImpl();
        DiameterDAO diameterDao = new DiameterDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_service_item_id, fk_type_id, fk_diameter_id, price from tire_service_db.service_item_prices where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                ServiceItem serviceItem = serviceItemDao.loadById(set.getInt("fk_service_item_id"));
                Type type = typeDao.loadById(set.getInt("fk_type_id"));
                Diameter diameter = diameterDao.loadById(set.getInt("fk_diameter_id"));
                Double price = set.getDouble("price");
                serviceItemPrice = new ServiceItemPrice();
                serviceItemPrice.setId(objectId);
                serviceItemPrice.setServiceItem(serviceItem);
                serviceItemPrice.setType(type);
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

        String query = "select id, fk_service_item_id, fk_type_id, fk_diameter_id, price from tire_service_db.service_item_prices ORDER BY id";
        return loadAllItem(null, query);

    }

    @Override
    public List<ServiceItemPrice> loadAllByItem(ServiceItem item) {
        Integer id = item.getId();
        String query = "select id, fk_service_item_id, fk_type_id, fk_diameter_id, price from tire_service_db.service_item_prices where fk_service_item_id = ? ORDER BY fk_type_id";
        return loadAllItem(id, query);

    }

    @Override
    public List<ServiceItemPrice> loadAllByType(Type type) {
        Integer id = type.getId();
        String query = "select id, fk_service_item_id, fk_type_id, fk_diameter_id, price from tire_service_db.service_item_prices where fk_type_id = ? ORDER BY fk_service_item_id";
        return loadAllItem(id, query);

    }

    @Override
    public List<ServiceItemPrice> loadAllUniqueByType(Type type) {

        int id = type.getId();
        String query = "select distinct id, fk_service_item_id, fk_type_id, fk_diameter_id, price " +
                "from tire_service_db.service_item_prices " +
                "where fk_type_id = ? " +
                "group by fk_service_item_id";

        return loadAllItem(id, query);
    }

    private List<ServiceItemPrice> loadAllItem(Integer id, String query) {
        List<ServiceItemPrice> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServiceItemDAO serviceItemDao = new ServiceItemDAOImpl();
        TypeDAO typeDao = new TypeDAOImpl();
        DiameterDAO diameterDao = new DiameterDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement(query);
            if (id != null) {
                statement.setInt(1, id);
            }
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                ServiceItem serviceItem = serviceItemDao.loadById(set.getInt("fk_service_item_id"));
                Type type = typeDao.loadById(set.getInt("fk_type_id"));
                Diameter diameter = diameterDao.loadById(set.getInt("fk_diameter_id"));
                Double price = set.getDouble("price");
                ServiceItemPrice serviceItemPrice = new ServiceItemPrice();
                serviceItemPrice.setId(objectId);
                serviceItemPrice.setServiceItem(serviceItem);
                serviceItemPrice.setType(type);
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