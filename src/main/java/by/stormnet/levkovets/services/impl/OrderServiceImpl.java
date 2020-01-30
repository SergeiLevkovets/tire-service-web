package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.OrderDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.Order;
import by.stormnet.levkovets.dto.impl.*;
import by.stormnet.levkovets.services.*;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;
import by.stormnet.levkovets.services.factory.ServiceFactory;
import by.stormnet.levkovets.utils.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<OrderDTO> getAllByDates(Date startDate, Date endDate) {
        OrderDAO orderDao = DAOFactory.getFactory().getOrderDAO();
        List<OrderDTO> list = new ArrayList<>();

        for (Order order : orderDao.loadAllByDates(startDate, endDate)) {
            OrderDTO orderDto = EntityDtoConverter.transformToOrderDto(order);
            list.add(orderDto);
        }
        return list;
    }

    @Override
    public List<OrderDTO> getAllByDatesAndUser(Date startDate, Date endDate, UserDTO userDTO) {
        OrderDAO orderDao = DAOFactory.getFactory().getOrderDAO();
        List<OrderDTO> list = new ArrayList<>();

        for (Order order : orderDao.loadAllByDatesAndUser(startDate, endDate, EntityDtoConverter.transformToUserEntity(userDTO))) {
            OrderDTO orderDto = EntityDtoConverter.transformToOrderDto(order);
            list.add(orderDto);
        }
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        OrderDAO dao = DAOFactory.getFactory().getOrderDAO();
        dao.deleteById(id);
    }

    @Override
    public Integer saveOrUpdate(OrderDTO orderDto) {
        OrderDAO orderDao = DAOFactory.getFactory().getOrderDAO();
        Integer id = orderDto.getId();
        if (id == null){
             id = orderDao.save(EntityDtoConverter.transformToOrderEntity(orderDto));
        }else {
            orderDao.update(EntityDtoConverter.transformToOrderEntity(orderDto));
        }
        return id;
    }

    @Override
    public OrderDTO createOrder(UserDTO userDTO,TireDTO tireDTO, TypeDTO typeDTO) {

        OrderDTO orderDto = new OrderDTO();

        orderDto.setUser(userDTO);
        orderDto.setTire(tireDTO);
        orderDto.setType(typeDTO);


        OrderService orderService = ServiceFactory.getFactory().getOrderService();
        Integer orderId = orderService.saveOrUpdate(orderDto);
        orderDto.setId(orderId);

        return orderDto;

    }

}
