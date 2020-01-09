package by.stormnet.levkovets.dao.jdbc.impl;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.jdbc.Dao;
import by.stormnet.levkovets.domain.impl.Patch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatchDao implements Dao<Patch> {


    @Override
    public void save(Patch patch) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.materials_patch (name, count, price) VALUES (?, ?, ?)");

            statement.setString(1, patch.getName());
            statement.setInt(2, patch.getCount());
            statement.setDouble(3, patch.getPrice());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(Patch patch) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.materials_patch SET name = ?, count = ?, price = ? WHERE id = ?");

            statement.setString(1, patch.getName());
            statement.setInt(2, patch.getCount());
            statement.setDouble(3, patch.getPrice());
            statement.setInt(4, patch.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(Patch patch) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.materials_patch WHERE id = ?");

            statement.setInt(1, patch.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Patch loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Patch patch = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, count, price from tire_service_db.materials_patch where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Integer count = set.getInt("count");
                Double price = set.getDouble("price");
                patch = new Patch();
                patch.setId(objectId);
                patch.setName(name);
                patch.setCount(count);
                patch.setPrice(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return patch;
    }

    @Override
    public List<Patch> loadAll() {
        List<Patch> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, count, price from tire_service_db.materials_patch");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Integer count = set.getInt("count");
                Double price = set.getDouble("price");
                Patch patch = new Patch();
                patch.setId(objectId);
                patch.setName(name);
                patch.setCount(count);
                patch.setPrice(price);
                list.add(patch);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}