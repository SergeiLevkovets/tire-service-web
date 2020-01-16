package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.dto.impl.OrderServiceItemPriceDto;
import by.stormnet.levkovets.services.DtoService;

import java.util.List;

public interface OrderServiceItemPriceService extends DtoService<OrderServiceItemPriceDto> {
    @Override
    OrderServiceItemPriceDto getById(Integer id);

    @Override
    List<OrderServiceItemPriceDto> getAll();

    List<OrderServiceItemPriceDto> getAllByOrder(OrderDto dto);

    @Override
    void delete(OrderServiceItemPriceDto obj);

    void deleteByOrder(OrderDto obj);

    @Override
    void saveOrUpdate(OrderServiceItemPriceDto dto);
}
