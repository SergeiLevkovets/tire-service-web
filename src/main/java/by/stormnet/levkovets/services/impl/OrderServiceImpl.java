package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.OrderDao;
import by.stormnet.levkovets.dao.mysql.OrderDaoImpl;
import by.stormnet.levkovets.domain.impl.Order;
import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements by.stormnet.levkovets.services.OrderService {
    @Override
    public OrderDto getById(Integer id) {
        OrderDao orderDao = new OrderDaoImpl();
        OrderDto orderDto = EntityDtoConverter.transformToOrderDto(orderDao.loadById(id));
        return orderDto;
    }

    @Override
    public List<OrderDto> getAll() {
        OrderDao orderDao = new OrderDaoImpl();
        List<OrderDto> list = new ArrayList<>();

        for (Order order : orderDao.loadAll()) {
            OrderDto orderDto = EntityDtoConverter.transformToOrderDto(order);
            list.add(orderDto);
        }
        return list;
    }

    @Override
    public void delete(OrderDto obj) {
        OrderDao dao = new OrderDaoImpl();
        dao.delete(EntityDtoConverter.transformToOrderEntity(obj));
    }

    @Override
    public void saveOrUpdate(OrderDto orderDto) {
        OrderDao orderDao = new OrderDaoImpl();
        if (orderDto.getId() == null){
            orderDao.save(EntityDtoConverter.transformToOrderEntity(orderDto));
        }else {
            orderDao.update(EntityDtoConverter.transformToOrderEntity(orderDto));
        }
    }
}
