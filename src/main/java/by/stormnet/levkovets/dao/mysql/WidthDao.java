package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.Dao;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.domain.impl.Width;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WidthDao implements Dao<Width> {


    @Override
    public void save(Width width) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.widths (width) VALUES (?)");

            statement.setString(1, width.getWidth());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(Width width) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.widths SET width = ? WHERE id = ?");

            statement.setString(1, width.getWidth());
            statement.setInt(2, width.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(Width width) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.widths WHERE id = ?");

            statement.setInt(1, width.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Width loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Width width = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, width from tire_service_db.widths where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String widthField = set.getString("width");
                width = new Width();
                width.setId(objectId);
                width.setWidth(widthField);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return width;
    }

    @Override
    public List<Width> loadAll() {
        List<Width> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, width from tire_service_db.widths");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String widthField = set.getString("width");
                Width width = new Width();
                width.setId(objectId);
                width.setWidth(widthField);
                list.add(width);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

    public Width loadBySize(String size) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Width width = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, width from tire_service_db.widths where width = ?");
            statement.setString(1, size);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String widthField = set.getString("width");
                width = new Width();
                width.setId(objectId);
                width.setWidth(widthField);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return width;
    }
}