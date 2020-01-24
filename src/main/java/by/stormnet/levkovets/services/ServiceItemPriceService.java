package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.ServiceItemDTO;
import by.stormnet.levkovets.dto.impl.ServiceItemPriceDTO;
import by.stormnet.levkovets.dto.impl.TypeDTO;

import java.util.List;

public interface ServiceItemPriceService {
    ServiceItemPriceDTO getById(Integer id);

    List<ServiceItemPriceDTO> getAllByType(TypeDTO type);

    List<ServiceItemPriceDTO> getAllUniqueByType(TypeDTO type);

    List<ServiceItemPriceDTO> getAllServiceItemPriceByServiceItem(ServiceItemDTO serviceItem);

    List<ServiceItemPriceDTO> getAll();

    void deleteById(Integer id);

    void saveOrUpdate(ServiceItemPriceDTO serviceItemPriceDto);
}
