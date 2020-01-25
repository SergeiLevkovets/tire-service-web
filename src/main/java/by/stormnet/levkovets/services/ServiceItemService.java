package by.stormnet.levkovets.services;

import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.dto.impl.ServiceItemDTO;
import by.stormnet.levkovets.dto.impl.TypeDTO;

import java.util.List;

public interface ServiceItemService {
    ServiceItemDTO getById(Integer id);

    ServiceItemDTO getByName(String name);

    List<ServiceItemDTO> getAll();

    List<ServiceItemDTO> getAllByTypeInServiceItemPrice(TypeDTO type);

    void deleteById(Integer id);

    void saveOrUpdate(ServiceItemDTO serviceItemDto);
}
