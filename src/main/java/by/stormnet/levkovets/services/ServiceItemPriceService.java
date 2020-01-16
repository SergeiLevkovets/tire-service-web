package by.stormnet.levkovets.services;

import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.dto.impl.ServiceItemPriceDto;

import java.util.List;

public interface ServiceItemPriceService extends DtoService<ServiceItemPriceDto> {
    @Override
    ServiceItemPriceDto getById(Integer id);

    List<ServiceItemPriceDto> getAllServiceItemPriceByType(Type type);

    List<ServiceItemPriceDto> getAllServiceItemPriceByServiceItem(ServiceItem serviceItem);

    @Override
    List<ServiceItemPriceDto> getAll();

    @Override
    void delete(ServiceItemPriceDto obj);

    @Override
    void saveOrUpdate(ServiceItemPriceDto serviceItemPriceDto);
}
