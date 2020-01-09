package by.stormnet.levkovets.dao.jdbc.impl;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.jdbc.Dao;
import by.stormnet.levkovets.domain.impl.Tire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TireDao implements Dao<Tire> {


    @Override
    public void save(Tire tire) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.tires (width, height, diameter, date) VALUES (?, ?, ?, ?)");

            statement.setInt(1, tire.getWidth());
            statement.setInt(2, tire.getHeight());
            statement.setInt(3, tire.getDiameter());
            statement.setTimestamp(4, new Timestamp(tire.getDate().getTime()));

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(Tire tire) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.tires SET width = ?, height = ?, diameter = ?, date = ? WHERE id = ?");

            statement.setInt(1, tire.getWidth());
            statement.setInt(2, tire.getHeight());
            statement.setInt(3, tire.getDiameter());
            statement.setTimestamp(4, new Timestamp(tire.getDate().getTime()));
            statement.setInt(5, tire.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(Tire tire) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.tires WHERE id = ?");

            statement.setInt(1, tire.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Tire loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Tire tire = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, width, height, diameter, `date` from tire_service_db.tires where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                Integer width = set.getInt("width");
                Integer height = set.getInt("height");
                Integer diameter = set.getInt("diameter");
                java.util.Date date = set.getTimestamp("date");
                tire = new Tire();
                tire.setId(objectId);
                tire.setWidth(width);
                tire.setHeight(height);
                tire.setDiameter(diameter);
                tire.setDate(date);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return tire;
    }

    @Override
    public List<Tire> loadAll() {
        List<Tire> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, width, height, diameter, `date` from tire_service_db.tires");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                Integer width = set.getInt("width");
                Integer height = set.getInt("height");
                Integer diameter = set.getInt("diameter");
                java.util.Date date = set.getTimestamp("date");
                Tire tire = new Tire();
                tire.setId(objectId);
                tire.setWidth(width);
                tire.setHeight(height);
                tire.setDiameter(diameter);
                tire.setDate(date);
                list.add(tire);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}