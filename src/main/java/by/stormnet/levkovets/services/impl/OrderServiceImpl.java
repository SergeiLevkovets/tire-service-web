package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.OrderDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.Order;
import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements by.stormnet.levkovets.services.OrderService {
    @Override
    public OrderDTO getById(Integer id) {
        OrderDAO orderDao = DAOFactory.getFactory().getOrderDAO();
        OrderDTO orderDto = EntityDtoConverter.transformToOrderDto(orderDao.loadById(id));
        return orderDto;
    }

    @Override
    public List<OrderDTO> getAll() {
        OrderDAO orderDao = DAOFactory.getFactory().getOrderDAO();
        List<OrderDTO> list = new ArrayList<>();

        for (Order order : orderDao.loadAll()) {
            OrderDTO orderDto = EntityDtoConverter.transformToOrderDto(order);
            list.add(orderDto);
        }
        return list;
    }

    @Override
    public void delete(OrderDTO obj) {
        OrderDAO dao = DAOFactory.getFactory().getOrderDAO();
        dao.delete(EntityDtoConverter.transformToOrderEntity(obj));
    }

    @Override
    public void saveOrUpdate(OrderDTO orderDto) {
        OrderDAO orderDao = DAOFactory.getFactory().getOrderDAO();
        if (orderDto.getId() == null){
            orderDao.save(EntityDtoConverter.transformToOrderEntity(orderDto));
        }else {
            orderDao.update(EntityDtoConverter.transformToOrderEntity(orderDto));
        }
    }
}
