package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Order;
import by.stormnet.levkovets.domain.impl.OrderServiceItemPrice;

import java.util.List;

public interface OrderServiceItemPriceDAO {

    void save(OrderServiceItemPrice obj);

    void saveAll(List<OrderServiceItemPrice> list);

    void update(OrderServiceItemPrice obj);

    void updateAll(List<OrderServiceItemPrice> list);

    void deleteById(Integer id);

    void deleteByOrderId(Integer id);

    OrderServiceItemPrice loadById(Integer id);

    List<OrderServiceItemPrice> loadAll();

    List<OrderServiceItemPrice> loadAllByOrder(Order obj);
}
