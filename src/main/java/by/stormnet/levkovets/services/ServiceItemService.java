package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.ServiceItemDTO;

import java.util.List;

public interface ServiceItemService {
    ServiceItemDTO getById(Integer id);

    ServiceItemDTO getByName(String name);

    List<ServiceItemDTO> getAll();

    void deleteById(Integer id);

    void saveOrUpdate(ServiceItemDTO serviceItemDto);
}
