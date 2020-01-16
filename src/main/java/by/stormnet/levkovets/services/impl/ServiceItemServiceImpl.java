package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.mysql.OrderDao;
import by.stormnet.levkovets.dao.mysql.ServiceItemDao;
import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.dto.impl.OrderDto;
import by.stormnet.levkovets.dto.impl.ServiceItemDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemServiceImpl implements DtoService<ServiceItemDto> {

    @Override
    public ServiceItemDto getById(Integer id) {
        ServiceItemDao serviceItemDao = new ServiceItemDao();
        ServiceItem serviceItem = serviceItemDao.loadById(id);
        ServiceItemDto serviceItemDto = EntityDtoConverter.transformToServiceItemDto(serviceItem);
        return serviceItemDto;
    }

    public ServiceItemDto getServiceItemByName(String name) {
        ServiceItemDao serviceItemDao = new ServiceItemDao();
        ServiceItem serviceItem = serviceItemDao.loadByName(name);
        ServiceItemDto serviceItemDto = EntityDtoConverter.transformToServiceItemDto(serviceItem);
        return serviceItemDto;
    }

    @Override
    public List<ServiceItemDto> getAll() {
        ServiceItemDao serviceItemDao = new ServiceItemDao();
        List<ServiceItem> serviceItemList = serviceItemDao.loadAll();
        List<ServiceItemDto> serviceItemDtoList = new ArrayList<>();
        for (ServiceItem serviceItem : serviceItemList) {
            ServiceItemDto serviceItemDto = EntityDtoConverter.transformToServiceItemDto(serviceItem);
            serviceItemDtoList.add(serviceItemDto);
        }
        return serviceItemDtoList;
    }

    @Override
    public void delete(ServiceItemDto obj) {
        ServiceItemDao dao = new ServiceItemDao();
        dao.delete(EntityDtoConverter.transformToServiceItemEntity(obj));
    }

    @Override
    public void saveOrUpdate(ServiceItemDto serviceItemDto) {
        ServiceItemDao serviceItemDao = new ServiceItemDao();

        if (serviceItemDto.getId() == null) {
            serviceItemDao.save(EntityDtoConverter.transformToServiceItemEntity(serviceItemDto));
        }else {
            serviceItemDao.update(EntityDtoConverter.transformToServiceItemEntity(serviceItemDto));
        }
    }
}
