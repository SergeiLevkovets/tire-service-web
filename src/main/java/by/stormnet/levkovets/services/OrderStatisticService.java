package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.dto.impl.OrderStatisticDTO;

public interface OrderStatisticService {

    OrderStatisticDTO createOrderStatistic(OrderDTO orderDTO);
}
