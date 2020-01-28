package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.OrderDAO;
import by.stormnet.levkovets.dao.TypeDAO;
import by.stormnet.levkovets.dao.UserDAO;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Order;
import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.domain.impl.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public Integer save(Order order) {

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Integer id = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.orders (fk_user_id, fk_tire_id, fk_type_id) VALUES (?, ?, ?)");

            statement.setInt(1, order.getUser().getId());
            statement.setInt(2, order.getTire().getId());
            statement.setInt(3, order.getType().getId());

            statement.executeUpdate();

            set = statement.executeQuery("SELECT LAST_INSERT_ID()");

            while (set.next()) {
                id = set.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return id;
    }

    @Override
    public void update(Order order) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.orders SET fk_user_id = ?, fk_tire_id = ?, fk_type_id = ?, date = ? WHERE id = ?");

            statement.setInt(1, order.getUser().getId());
            statement.setInt(2, order.getTire().getId());
            statement.setInt(3, order.getType().getId());
            statement.setTimestamp(4, new Timestamp(order.getDate().getTime()));
            statement.setInt(5, order.getId());

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
            statement = c.prepareStatement("DELETE FROM tire_service_db.orders WHERE id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public Order loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Order order = null;
        UserDAO userDao = new UserDAOImpl();
        TireDAOImpl tireDaoImpl = new TireDAOImpl();
        TypeDAO typeDao = new TypeDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_user_id, fk_tire_id, fk_type_id, `date` from tire_service_db.orders where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                User user = userDao.loadById(set.getInt("fk_user_id"));
                Tire tire = tireDaoImpl.loadById(set.getInt("fk_tire_id"));
                Type type = typeDao.loadById(set.getInt("fk_type_id"));
                java.util.Date date = set.getTimestamp("date");
                order = new Order();
                order.setId(objectId);
                order.setUser(user);
                order.setTire(tire);
                order.setType(type);
                order.setDate(date);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return order;
    }

    @Override
    public List<Order> loadAll() {
        List<Order> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        UserDAO userDao = new UserDAOImpl();
        TireDAOImpl tireDaoImpl = new TireDAOImpl();
        TypeDAO typeDao = new TypeDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_user_id, fk_tire_id, fk_type_id, `date` from tire_service_db.orders");
            set = statement.executeQuery();


            while (set.next()) {
                Integer objectId = set.getInt("id");
                User user = userDao.loadById(set.getInt("fk_user_id"));
                Tire tire = tireDaoImpl.loadById(set.getInt("fk_tire_id"));
                Type type = typeDao.loadById(set.getInt("fk_type_id"));
                java.util.Date date = set.getTimestamp("date");
                Order order = new Order();
                order.setId(objectId);
                order.setUser(user);
                order.setTire(tire);
                order.setType(type);
                order.setDate(date);
                list.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

    @Override
    public List<Order> loadAllByDates(Date startDate, Date endDate) {
        List<Order> list = new ArrayList<>();
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        UserDAO userDao = new UserDAOImpl();
        TireDAOImpl tireDaoImpl = new TireDAOImpl();
        TypeDAO typeDao = new TypeDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement(
                    "select id, fk_user_id, fk_tire_id, fk_type_id, `date` from tire_service_db.orders where `date` between ? end ?");
            statement.setTimestamp(1, new Timestamp(startDate.getTime()));
            statement.setTimestamp(2, new Timestamp(endDate.getTime()));
            set = statement.executeQuery();


            while (set.next()) {
                Integer objectId = set.getInt("id");
                User user = userDao.loadById(set.getInt("fk_user_id"));
                Tire tire = tireDaoImpl.loadById(set.getInt("fk_tire_id"));
                Type type = typeDao.loadById(set.getInt("fk_type_id"));
                java.util.Date date = set.getTimestamp("date");
                Order order = new Order();
                order.setId(objectId);
                order.setUser(user);
                order.setTire(tire);
                order.setType(type);
                order.setDate(date);
                list.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

}