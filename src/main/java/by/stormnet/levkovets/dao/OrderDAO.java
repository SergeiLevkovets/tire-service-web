package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Order;

import java.util.Date;
import java.util.List;

public interface OrderDAO {
    Integer save(Order order);

    void update(Order order);

    void deleteById(Integer id);

    Order loadById(Integer id);

    List<Order> loadAll();

    List<Order> loadAllByDates(Date startDate, Date endDate);
}
