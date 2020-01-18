package by.stormnet.levkovets.services;

import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.dto.impl.ServiceItemDto;
import by.stormnet.levkovets.dto.impl.ServiceItemPriceDto;
import by.stormnet.levkovets.dto.impl.TypeDto;

import java.util.List;

public interface ServiceItemPriceService extends DtoService<ServiceItemPriceDto> {
    @Override
    ServiceItemPriceDto getById(Integer id);

    List<ServiceItemPriceDto> getAllServiceItemPriceByType(TypeDto type);

    List<ServiceItemPriceDto> getAllServiceItemPriceByServiceItem(ServiceItemDto serviceItem);

    @Override
    List<ServiceItemPriceDto> getAll();

    @Override
    void delete(ServiceItemPriceDto obj);

    @Override
    void saveOrUpdate(ServiceItemPriceDto serviceItemPriceDto);
}
