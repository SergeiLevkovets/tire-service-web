package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.dto.impl.TireDTO;
import by.stormnet.levkovets.dto.impl.TypeDTO;
import by.stormnet.levkovets.dto.impl.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {
    OrderDTO getById(Integer id);

    List<OrderDTO> getAll();

    void deleteById(Integer id);

    Integer saveOrUpdate(OrderDTO orderDto);

    OrderDTO createOrder(UserDTO userDTO, TireDTO tireDTO, TypeDTO typeDTO);
}
