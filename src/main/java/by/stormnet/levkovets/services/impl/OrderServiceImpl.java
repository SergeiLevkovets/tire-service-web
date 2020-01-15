package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.OrderDao;
import by.stormnet.levkovets.domain.impl.Order;
import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements DtoService<OrderDto> {
    @Override
    public OrderDto getById(Integer id) {
        OrderDao orderDao = new OrderDao();
        OrderDto orderDto = EntityDtoConverter.transformToOrderDto(orderDao.loadById(id));
        return orderDto;
    }

    @Override
    public List<OrderDto> getAll() {
        OrderDao orderDao = new OrderDao();
        List<OrderDto> list = new ArrayList<>();

        for (Order order : orderDao.loadAll()) {
            OrderDto orderDto = EntityDtoConverter.transformToOrderDto(order);
            list.add(orderDto);
        }
        return list;
    }

    @Override
    public void saveOrUpdate(OrderDto orderDto) {
        OrderDao orderDao = new OrderDao();
        if (orderDto.getId() == null){
            orderDao.save(EntityDtoConverter.transformToOrderEntity(orderDto));
        }else {
            orderDao.update(EntityDtoConverter.transformToOrderEntity(orderDto));
        }
    }
}
