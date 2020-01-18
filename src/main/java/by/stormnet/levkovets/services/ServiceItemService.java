package by.stormnet.levkovets.services;

import by.stormnet.levkovets.dto.impl.ServiceItemDto;

import java.util.List;

public interface ServiceItemService extends DtoService<ServiceItemDto> {
    @Override
    ServiceItemDto getById(Integer id);

    ServiceItemDto getByName(String name);

    @Override
    List<ServiceItemDto> getAll();

    @Override
    void delete(ServiceItemDto obj);

    @Override
    void saveOrUpdate(ServiceItemDto serviceItemDto);
}
