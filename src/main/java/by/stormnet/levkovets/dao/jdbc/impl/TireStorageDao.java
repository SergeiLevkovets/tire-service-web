package by.stormnet.levkovets.dao.jdbc.impl;

import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.dao.jdbc.Dao;
import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.domain.impl.TireStorage;
import by.stormnet.levkovets.domain.impl.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TireStorageDao implements Dao<TireStorage> {


    @Override
    public void save(TireStorage tireStorage) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.tire_storage (name, fk_tire_id, date_end, fk_user_id) VALUES (?, ?, ?, ?)");

            statement.setString(1, tireStorage.getName());
            statement.setInt(2, tireStorage.getTire().getId());
            statement.setTimestamp(3, new Timestamp(tireStorage.getDateEnd().getTime()));
            statement.setInt(4, tireStorage.getUser().getId());

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(TireStorage tireStorage) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.tire_storage SET name = ?, fk_tire_id = ?, date_end = ?, fk_user_id = ? WHERE id = ?");

            statement.setString(1, tireStorage.getName());
            statement.setInt(2, tireStorage.getTire().getId());
            statement.setTimestamp(3, new Timestamp(tireStorage.getDateEnd().getTime()));
            statement.setInt(4, tireStorage.getUser().getId());
            statement.setInt(5, tireStorage.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(TireStorage tireStorage) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.tire_storage WHERE id = ?");

            statement.setInt(1, tireStorage.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public TireStorage loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        TireStorage tireStorage = null;
        TireDao tireDao = new TireDao();
        UserDao userDao = new UserDao();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, fk_tire_id, date_end, fk_user_id from tire_service_db.tire_storage where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Tire tire = tireDao.loadById(set.getInt("fk_tire_id"));
                Date dateEnd = set.getTimestamp("date_end");
                User user = userDao.loadById(set.getInt("fk_user_id"));
                tireStorage = new TireStorage();
                tireStorage.setId(objectId);
                tireStorage.setName(name);
                tireStorage.setTire(tire);
                tireStorage.setDateEnd(dateEnd);
                tireStorage.setUser(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return tireStorage;
    }

    @Override
    public List<TireStorage> loadAll() {
        List<TireStorage> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        TireDao tireDao = new TireDao();
        UserDao userDao = new UserDao();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, name, fk_tire_id, date_end, fk_user_id from tire_service_db.tire_storage");
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                String name = set.getString("name");
                Tire tire = tireDao.loadById(set.getInt("fk_tire_id"));
                java.util.Date dateEnd = set.getTimestamp("date_end");
                User user = userDao.loadById(set.getInt("fk_user_id"));
                TireStorage tireStorage = new TireStorage();
                tireStorage.setId(objectId);
                tireStorage.setName(name);
                tireStorage.setTire(tire);
                tireStorage.setDateEnd(dateEnd);
                tireStorage.setUser(user);
                list.add(tireStorage);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}