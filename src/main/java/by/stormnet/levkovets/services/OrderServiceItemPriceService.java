package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.dto.impl.OrderServiceItemPriceDTO;

import java.util.List;

public interface OrderServiceItemPriceService {
    OrderServiceItemPriceDTO getById(Integer id);

    List<OrderServiceItemPriceDTO> getAll();

    List<OrderServiceItemPriceDTO> getAllByOrder(OrderDTO dto);

    void deleteById(Integer id);

    void deleteByOrderId(Integer id);

    void saveOrUpdate(OrderServiceItemPriceDTO dto);

    void saveOrUpdateAll(List<OrderServiceItemPriceDTO> list);
}
