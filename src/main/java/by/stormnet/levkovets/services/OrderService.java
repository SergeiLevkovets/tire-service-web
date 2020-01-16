package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.OrderDto;

import java.util.List;

public interface OrderService extends DtoService<OrderDto> {
    @Override
    OrderDto getById(Integer id);

    @Override
    List<OrderDto> getAll();

    @Override
    void delete(OrderDto obj);

    @Override
    void saveOrUpdate(OrderDto orderDto);
}
