package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO getById(Integer id);

    List<OrderDTO> getAll();

    void deleteById(Integer id);

    Integer saveOrUpdate(OrderDTO orderDto);
}
