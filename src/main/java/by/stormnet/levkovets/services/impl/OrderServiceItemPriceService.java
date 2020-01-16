package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.OrderDao;
import by.stormnet.levkovets.dao.mysql.OrderServiceItemPriceDao;
import by.stormnet.levkovets.domain.impl.OrderServiceItemPrice;
import by.stormnet.levkovets.domain.impl.User;
import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.dto.impl.OrderServiceItemPriceDto;
import by.stormnet.levkovets.dto.impl.UserDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceItemPriceService implements DtoService<OrderServiceItemPriceDto> {
    @Override
    public OrderServiceItemPriceDto getById(Integer id) {
        OrderServiceItemPriceDao dao = new OrderServiceItemPriceDao();

        OrderServiceItemPriceDto dto = EntityDtoConverter.transformToOrderServiceItemPriceDto(dao.loadById(id));

        return dto;
    }

    @Override
    public List<OrderServiceItemPriceDto> getAll() {
        OrderServiceItemPriceDao dao = new OrderServiceItemPriceDao();
        List<OrderServiceItemPriceDto> list = new ArrayList<>();
        for (OrderServiceItemPrice entity : dao.loadAll()) {
            list.add(EntityDtoConverter.transformToOrderServiceItemPriceDto(entity));
        }
        return list;
    }

    public List<OrderServiceItemPriceDto> getAllByOrder(OrderDto dto) {
        OrderServiceItemPriceDao dao = new OrderServiceItemPriceDao();
        List<OrderServiceItemPriceDto> list = new ArrayList<>();
        for (OrderServiceItemPrice entity : dao.loadAllByOrder(EntityDtoConverter.transformToOrderEntity(dto))) {
            list.add(EntityDtoConverter.transformToOrderServiceItemPriceDto(entity));
        }
        return list;
    }

    @Override
    public void delete(OrderServiceItemPriceDto obj) {
        OrderServiceItemPriceDao dao = new OrderServiceItemPriceDao();
        dao.delete(EntityDtoConverter.transformToOrderServiceItemPriceEntity(obj));
    }

    public void deleteByOrder(OrderDto obj) {
        OrderServiceItemPriceDao dao = new OrderServiceItemPriceDao();
        dao.deleteByOrder(EntityDtoConverter.transformToOrderEntity(obj));
    }

    @Override
    public void saveOrUpdate(OrderServiceItemPriceDto dto) {
        OrderServiceItemPriceDao dao = new OrderServiceItemPriceDao();
        if (dto.getId() == null) {
            dao.save(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
        }else {
            dao.update(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
        }
    }


}
