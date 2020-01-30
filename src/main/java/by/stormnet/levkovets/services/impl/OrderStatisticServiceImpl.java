package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.dto.impl.OrderServiceItemPriceDTO;
import by.stormnet.levkovets.dto.impl.OrderStatisticDTO;
import by.stormnet.levkovets.services.OrderServiceItemPriceService;
import by.stormnet.levkovets.services.OrderStatisticService;
import by.stormnet.levkovets.services.factory.ServiceFactory;

import java.util.List;

public class OrderStatisticServiceImpl implements OrderStatisticService {

    private OrderServiceItemPriceService orderServiceItemPriceService = ServiceFactory.getFactory().getOrderServiceItemPriceService();

    @Override
    public OrderStatisticDTO createOrderStatistic(OrderDTO orderDTO) {

        List<OrderServiceItemPriceDTO> allByOrder = orderServiceItemPriceService.getAllByOrder(orderDTO);
        Double totalPrice = new Double(0);
        for (OrderServiceItemPriceDTO orderServiceItemPriceDTO : allByOrder) {
            totalPrice += orderServiceItemPriceDTO.getTotalPrice();
        }
        OrderStatisticDTO orderStatisticDTO = new OrderStatisticDTO();
        orderStatisticDTO.setOrder(orderDTO);
        orderStatisticDTO.setTotalPrice(totalPrice);
        orderStatisticDTO.setOrderServiceItemPriceDTOList(allByOrder);

        return orderStatisticDTO;
    }
}
