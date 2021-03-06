package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.HeightDAO;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Height;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeightDAOImpl implements HeightDAO {


    @Override
    public void save(Height height) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.heights (height) VALUES (?)");

            statement.setString(1, height.getHeight());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(Height height) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.heights SET height = ? WHERE id = ?");

            statement.setString(1, height.getHeight());
            statement.setInt(2, height.getId());

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
            statement = c.prepareStatement("DELETE FROM tire_service_db.heights WHERE id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Height loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Height height = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, height from tire_service_db.heights where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String heightField = set.getString("height");
                height = new Height();
                height.setId(objectId);
                height.setHeight(heightField);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return height;
    }

    @Override
    public List<Height> loadAll() {
        List<Height> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, height from tire_service_db.heights");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String heightField = set.getString("height");
                Height height = new Height();
                height.setId(objectId);
                height.setHeight(heightField);
                list.add(height);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

    @Override
    public Height loadBySize(String size) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Height height = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, height from tire_service_db.heights where height = ?");
            statement.setString(1, size);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String heightField = set.getString("height");
                height = new Height();
                height.setId(objectId);
                height.setHeight(heightField);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return height;
    }
}