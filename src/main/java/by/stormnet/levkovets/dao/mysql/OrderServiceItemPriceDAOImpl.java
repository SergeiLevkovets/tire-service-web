package by.stormnet.levkovets.dao.mysql;

import by.stormnet.levkovets.dao.OrderDAO;
import by.stormnet.levkovets.dao.OrderServiceItemPriceDAO;
import by.stormnet.levkovets.dao.ServiceItemPriceDAO;
import by.stormnet.levkovets.dao.db.ConnectionManager;
import by.stormnet.levkovets.domain.impl.Order;
import by.stormnet.levkovets.domain.impl.OrderServiceItemPrice;
import by.stormnet.levkovets.domain.impl.ServiceItemPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class OrderServiceItemPriceDAOImpl implements OrderServiceItemPriceDAO {
    @Override
    public void save(OrderServiceItemPrice obj) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.orders_to_service_item_prices (fk_orders_id, fk_service_item_price_id, count) VALUES (?, ?, ?)");

            statement.setInt(1, obj.getOrder().getId());
            if (obj.getServiceItemPrice().getId() == null) {
                statement.setNull(2, NULL);
            }else {
                statement.setInt(2, obj.getServiceItemPrice().getId());
            }
            if (obj.getCount() == null) {
                statement.setInt(3, 1);
            } else {
                statement.setInt(3, obj.getCount());
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void saveAll(List<OrderServiceItemPrice> list) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            c.setAutoCommit(false);
            statement = c.prepareStatement("INSERT INTO tire_service_db.orders_to_service_item_prices (fk_orders_id, fk_service_item_price_id, count) VALUES (?, ?, ?)");

            final int batchSize = 100;
            int count = 0;

            for (OrderServiceItemPrice obj : list) {

                statement.setInt(1, obj.getOrder().getId());
                if (obj.getServiceItemPrice().getId() == null) {
                    statement.setNull(2, NULL);
                }else {
                    statement.setInt(2, obj.getServiceItemPrice().getId());
                }
                if (obj.getCount() == null) {
                    statement.setInt(3, 1);
                } else {
                    statement.setInt(3, obj.getCount());
                }
                statement.addBatch();

                if (++count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();
            c.commit();
            c.setAutoCommit(true);

        } catch (SQLException e) {
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
            if (obj.getServiceItemPrice().getId() == null) {
                statement.setNull(2, NULL);
            }else {
                statement.setInt(2, obj.getServiceItemPrice().getId());
            }
            if (obj.getCount() == null) {
                statement.setInt(3, 1);
            } else {
                statement.setInt(3, obj.getCount());
            }
            statement.setInt(4, obj.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    public void updateAll(List<OrderServiceItemPrice> list) {

        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();

            statement = c.prepareStatement("INSERT INTO tire_service_db.orders_to_service_item_prices (fk_orders_id, fk_service_item_price_id, count) VALUES (?, ?, ?");

            final int batchSize = 100;
            int count = 0;

            for (OrderServiceItemPrice obj : list) {

                statement.setInt(1, obj.getOrder().getId());
                if (obj.getServiceItemPrice().getId() == null) {
                    statement.setNull(2, NULL);
                }else {
                    statement.setInt(2, obj.getServiceItemPrice().getId());
                }
                if (obj.getCount() == null) {
                    statement.setInt(3, 1);
                } else {
                    statement.setInt(3, obj.getCount());
                }
                statement.setInt(4, obj.getId());
                statement.addBatch();

                if (++count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();

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
            statement = c.prepareStatement("DELETE FROM tire_service_db.orders_to_service_item_prices WHERE id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Some errors occurred during DB access!", e);
        } finally {
            ConnectionManager.getManager().closeDbResources(c, statement);
        }
    }

    @Override
    public void deleteByOrderId(Integer id) {
        Connection c = null;
        PreparedStatement statement = null;

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("DELETE FROM tire_service_db.orders_to_service_item_prices WHERE fk_orders_id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
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
        OrderDAO orderDao = new OrderDAOImpl();
        ServiceItemPriceDAO serviceItemPriceDao = new ServiceItemPriceDAOImpl();

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
                orderServiceItemPrice = new OrderServiceItemPrice();
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
        OrderDAO orderDao = new OrderDAOImpl();
        ServiceItemPriceDAO serviceItemPriceDao = new ServiceItemPriceDAOImpl();

        try {
            c = ConnectionManager.getManager().getConnection();
            statement = c.prepareStatement("select id, fk_orders_id, fk_service_item_price_id, count from tire_service_db.orders_to_service_item_prices");
            set = statement.executeQuery();


            while (set.next()) {
                Integer objectId = set.getInt("id");
                Order order = orderDao.loadById(set.getInt("fk_orders_id"));
                ServiceItemPrice serviceItemPrice = serviceItemPriceDao.loadById(set.getInt("fk_service_item_price_id"));
                Integer count = set.getInt("count");
                OrderServiceItemPrice orderServiceItemPrice = new OrderServiceItemPrice();
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
        OrderDAO orderDao = new OrderDAOImpl();
        ServiceItemPriceDAO serviceItemPriceDao = new ServiceItemPriceDAOImpl();

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
                OrderServiceItemPrice orderServiceItemPrice = new OrderServiceItemPrice();
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
