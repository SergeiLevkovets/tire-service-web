package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.Dao;
import by.stormnet.levkovets.domain.impl.Valve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ValveDao implements Dao<Valve> {


    @Override
    public void save(Valve valve) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.materials_valve (name, count, price) VALUES (?, ?, ?)");

            statement.setString(1, valve.getName());
            statement.setInt(2, valve.getCount());
            statement.setDouble(3, valve.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(Valve valve) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.materials_valve SET name = ?, count = ?, price = ? WHERE id = ?");

            statement.setString(1, valve.getName());
            statement.setInt(2, valve.getCount());
            statement.setDouble(3, valve.getPrice());
            statement.setInt(4, valve.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(Valve valve) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.materials_valve WHERE id = ?");

            statement.setInt(1, valve.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Valve loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Valve valve = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, count, price from tire_service_db.materials_valve where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Integer count = set.getInt("count");
                Double price = set.getDouble("price");
                valve = new Valve();
                valve.setId(objectId);
                valve.setName(name);
                valve.setCount(count);
                valve.setPrice(price);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return valve;
    }

    @Override
    public List<Valve> loadAll() {
        List<Valve> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, count, price from tire_service_db.materials_valve");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Integer count = set.getInt("count");
                Double price = set.getDouble("price");
                Valve valve = new Valve();
                valve.setId(objectId);
                valve.setName(name);
                valve.setCount(count);
                valve.setPrice(price);
                list.add(valve);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}