package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.dto.impl.OrderServiceItemPriceDTO;

import java.util.List;

public interface OrderServiceItemPriceService {
    OrderServiceItemPriceDTO getById(Integer id);

    List<OrderServiceItemPriceDTO> getAll();

    List<OrderServiceItemPriceDTO> getAllByOrder(OrderDTO dto);

    void delete(OrderServiceItemPriceDTO obj);

    void deleteByOrder(OrderDTO obj);

    void saveOrUpdate(OrderServiceItemPriceDTO dto);
}
