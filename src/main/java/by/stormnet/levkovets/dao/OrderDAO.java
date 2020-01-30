package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Order;
import by.stormnet.levkovets.domain.impl.User;

import java.util.Date;
import java.util.List;

public interface OrderDAO {

    Integer save(Order order);

    void update(Order order);

    void deleteById(Integer id);

    Order loadById(Integer id);

    List<Order> loadAll();

    List<Order> loadAllByDatesAndUser(Date startDate, Date endDate, User user);

    List<Order> loadAllByDates(Date startDate, Date endDate);
}
