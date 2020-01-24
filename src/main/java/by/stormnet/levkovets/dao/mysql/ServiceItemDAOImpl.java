package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.ServiceItemDAO;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.ServiceItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceItemDAOImpl implements ServiceItemDAO {


    @Override
    public void save(ServiceItem serviceItem) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.service_items (name, article) VALUES (?, ?)");

            statement.setString(1, serviceItem.getName());
            statement.setString(2, serviceItem.getArticle());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(ServiceItem serviceItem) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.service_items SET name = ?, article = ? WHERE id = ?");

            statement.setString(1, serviceItem.getName());
            statement.setString(2, serviceItem.getArticle());
            statement.setInt(3, serviceItem.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
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
            statement = c.prepareStatement("DELETE FROM tire_service_db.service_items WHERE id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public ServiceItem loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServiceItem serviceItem = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, article from tire_service_db.service_items where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                String article = set.getString("article");
                serviceItem = new ServiceItem();
                serviceItem.setId(objectId);
                serviceItem.setName(name);
                serviceItem.setArticle(article);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return serviceItem;
    }

    @Override
    public List<ServiceItem> loadAll() {
        List<ServiceItem> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, article from tire_service_db.service_items");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                String article = set.getString("article");
                ServiceItem serviceItem = new ServiceItem();
                serviceItem.setId(objectId);
                serviceItem.setName(name);
                serviceItem.setArticle(article);
                list.add(serviceItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

    @Override
    public ServiceItem loadByArticle(String article) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ServiceItem serviceItem = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, article from tire_service_db.service_items where article = ?");
            statement.setString(1, article);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                String art = set.getString("article");
                serviceItem = new ServiceItem();
                serviceItem.setId(objectId);
                serviceItem.setName(name);
                serviceItem.setArticle(art);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return serviceItem;
    }
}