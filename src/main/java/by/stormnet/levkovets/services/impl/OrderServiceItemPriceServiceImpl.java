package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.OrderServiceItemPriceDaoImpl;
import by.stormnet.levkovets.domain.impl.OrderServiceItemPrice;
import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.dto.impl.OrderServiceItemPriceDto;
import by.stormnet.levkovets.services.OrderServiceItemPriceService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceItemPriceServiceImpl implements OrderServiceItemPriceService {
    @Override
    public OrderServiceItemPriceDto getById(Integer id) {
        OrderServiceItemPriceDaoImpl dao = new OrderServiceItemPriceDaoImpl();

        OrderServiceItemPriceDto dto = EntityDtoConverter.transformToOrderServiceItemPriceDto(dao.loadById(id));

        return dto;
    }

    @Override
    public List<OrderServiceItemPriceDto> getAll() {
        OrderServiceItemPriceDaoImpl dao = new OrderServiceItemPriceDaoImpl();
        List<OrderServiceItemPriceDto> list = new ArrayList<>();
        for (OrderServiceItemPrice entity : dao.loadAll()) {
            list.add(EntityDtoConverter.transformToOrderServiceItemPriceDto(entity));
        }
        return list;
    }

    @Override
    public List<OrderServiceItemPriceDto> getAllByOrder(OrderDto dto) {
        OrderServiceItemPriceDaoImpl dao = new OrderServiceItemPriceDaoImpl();
        List<OrderServiceItemPriceDto> list = new ArrayList<>();
        for (OrderServiceItemPrice entity : dao.loadAllByOrder(EntityDtoConverter.transformToOrderEntity(dto))) {
            list.add(EntityDtoConverter.transformToOrderServiceItemPriceDto(entity));
        }
        return list;
    }

    @Override
    public void delete(OrderServiceItemPriceDto obj) {
        OrderServiceItemPriceDaoImpl dao = new OrderServiceItemPriceDaoImpl();
        dao.delete(EntityDtoConverter.transformToOrderServiceItemPriceEntity(obj));
    }

    @Override
    public void deleteByOrder(OrderDto obj) {
        OrderServiceItemPriceDaoImpl dao = new OrderServiceItemPriceDaoImpl();
        dao.deleteByOrder(EntityDtoConverter.transformToOrderEntity(obj));
    }

    @Override
    public void saveOrUpdate(OrderServiceItemPriceDto dto) {
        OrderServiceItemPriceDaoImpl dao = new OrderServiceItemPriceDaoImpl();
        if (dto.getId() == null) {
            dao.save(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
        }else {
            dao.update(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
        }
    }


}
