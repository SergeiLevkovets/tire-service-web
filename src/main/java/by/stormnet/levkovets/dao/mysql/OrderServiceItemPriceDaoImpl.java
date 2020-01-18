package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.OrderDao;
import by.stormnet.levkovets.dao.OrderServiceItemPriceDao;
import by.stormnet.levkovets.dao.ServiceItemPriceDao;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceItemPriceDaoImpl implements OrderServiceItemPriceDao {
    @Override
    public void save(OrderServiceItemPrice obj) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.orders_to_service_item_prices (fk_orders_id, fk_service_item_price_id, count) VALUES (?, ?, ?");

            statement.setInt(1, obj.getOrder().getId());
            statement.setInt(2, obj.getServiceItemPrice().getId());
            if (obj.getCount() == null){
                statement.setInt(3, 1);
            }else {
                statement.setInt(3, obj.getCount());
            }

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void update(OrderServiceItemPrice obj) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("UPDATE tire_service_db.orders_to_service_item_prices SET fk_orders_id = ?, fk_service_item_price_id = ?, count = ? WHERE id = ?");

            statement.setInt(1, obj.getOrder().getId());
            statement.setInt(2, obj.getServiceItemPrice().getId());
            if (obj.getCount() == null){
                statement.setInt(3, 1);
            }else {
                statement.setInt(3, obj.getCount());
            }
            statement.setInt(4, obj.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void delete(OrderServiceItemPrice obj) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.orders_to_service_item_prices WHERE id = ?");

            statement.setInt(1, obj.getId());

            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void deleteByOrder(Order obj) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.orders_to_service_item_prices WHERE fk_orders_id = ?");
            statement.setInt(1, obj.getId());
            statement.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }



    @Override
    public OrderServiceItemPrice loadById(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        OrderServiceItemPrice orderServiceItemPrice = null;
        OrderDao orderDao = new OrderDaoImpl();
        ServiceItemPriceDao serviceItemPriceDao = new ServiceItemPriceDaoImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_orders_id, fk_service_item_price_id, count from tire_service_db.orders_to_service_item_prices where id = ?");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                Integer objectId = set.getInt("id");
                Order order = orderDao.loadById(set.getInt("fk_orders_id"));
                ServiceItemPrice serviceItemPrice = serviceItemPriceDao.loadById(set.getInt("fk_service_item_price_id"));
                Integer count = set.getInt("count");
                orderServiceItemPrice =new OrderServiceItemPrice();
                orderServiceItemPrice.setId(objectId);
                orderServiceItemPrice.setOrder(order);
                orderServiceItemPrice.setServiceItemPrice(serviceItemPrice);
                orderServiceItemPrice.setCount(count);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return orderServiceItemPrice;
    }

    @Override
    public List<OrderServiceItemPrice> loadAll() {
        List<OrderServiceItemPrice> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        OrderDao orderDao = new OrderDaoImpl();
        ServiceItemPriceDao serviceItemPriceDao = new ServiceItemPriceDaoImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_orders_id, fk_service_item_price_id, count from tire_service_db.orders_to_service_item_prices");
            set = statement.executeQuery();


            while (set.next()) {
                Integer objectId = set.getInt("id");
                Order order = orderDao.loadById(set.getInt("fk_orders_id"));
                ServiceItemPrice serviceItemPrice = serviceItemPriceDao.loadById(set.getInt("fk_service_item_price_id"));
                Integer count = set.getInt("count");
                OrderServiceItemPrice orderServiceItemPrice =new OrderServiceItemPrice();
                orderServiceItemPrice.setId(objectId);
                orderServiceItemPrice.setOrder(order);
                orderServiceItemPrice.setServiceItemPrice(serviceItemPrice);
                orderServiceItemPrice.setCount(count);
                list.add(orderServiceItemPrice);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }

    @Override
    public List<OrderServiceItemPrice> loadAllByOrder(Order obj) {
        List<OrderServiceItemPrice> list = new ArrayList<>();

        Connection c = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        OrderDao orderDao = new OrderDaoImpl();
        ServiceItemPriceDao serviceItemPriceDao = new ServiceItemPriceDaoImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_orders_id, fk_service_item_price_id, count from tire_service_db.orders_to_service_item_prices where fk_orders_id = ?");
            statement.setInt(1, obj.getId());
            set = statement.executeQuery();


            while (set.next()) {
                Integer objectId = set.getInt("id");
                Order order = orderDao.loadById(set.getInt("fk_orders_id"));
                ServiceItemPrice serviceItemPrice = serviceItemPriceDao.loadById(set.getInt("fk_service_item_price_id"));
                Integer count = set.getInt("count");
                OrderServiceItemPrice orderServiceItemPrice =new OrderServiceItemPrice();
                orderServiceItemPrice.setId(objectId);
                orderServiceItemPrice.setOrder(order);
                orderServiceItemPrice.setServiceItemPrice(serviceItemPrice);
                orderServiceItemPrice.setCount(count);
                list.add(orderServiceItemPrice);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement, set);
        }
        return list;
    }
}
