package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.ServiceItemPriceDao;
import by.stormnet.levkovets.dao.mysql.ServiceItemPriceDaoImpl;
import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.domain.impl.ServiceItemPrice;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.dto.impl.ServiceItemPriceDto;
import by.stormnet.levkovets.services.DtoService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemPriceServiceImpl implements by.stormnet.levkovets.services.ServiceItemPriceService {

    @Override
    public ServiceItemPriceDto getById(Integer id) {
        ServiceItemPriceDao serviceItemPriceDao = new ServiceItemPriceDaoImpl();
        ServiceItemPrice serviceItemPrice = serviceItemPriceDao.loadById(id);
        ServiceItemPriceDto serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
        return serviceItemPriceDto;
    }

    @Override
    public List<ServiceItemPriceDto> getAllServiceItemPriceByType(Type type) {
        ServiceItemPriceDao serviceItemPriceDao = new ServiceItemPriceDaoImpl();
        List<ServiceItemPrice> serviceItemPriceList = serviceItemPriceDao.loadAllByType(type);
        List<ServiceItemPriceDto> serviceItemPriceDtoList = new ArrayList<>();
        for (ServiceItemPrice serviceItemPrice : serviceItemPriceList) {
            ServiceItemPriceDto serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
            serviceItemPriceDtoList.add(serviceItemPriceDto);
        }
        return serviceItemPriceDtoList;
    }

    @Override
    public List<ServiceItemPriceDto> getAllServiceItemPriceByServiceItem(ServiceItem serviceItem) {
        ServiceItemPriceDao serviceItemPriceDao = new ServiceItemPriceDaoImpl();
        List<ServiceItemPrice> serviceItemPriceList = serviceItemPriceDao.loadAllByItem(serviceItem);
        List<ServiceItemPriceDto> serviceItemPriceDtoList = new ArrayList<>();
        for (ServiceItemPrice serviceItemPrice : serviceItemPriceList) {
            ServiceItemPriceDto serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
            serviceItemPriceDtoList.add(serviceItemPriceDto);
        }
        return serviceItemPriceDtoList;
    }

    @Override
    public List<ServiceItemPriceDto> getAll() {
        ServiceItemPriceDao serviceItemPriceDao = new ServiceItemPriceDaoImpl();
        List<ServiceItemPrice> serviceItemPriceList = serviceItemPriceDao.loadAll();
        List<ServiceItemPriceDto> serviceItemPriceDtoList = new ArrayList<>();
        for (ServiceItemPrice serviceItemPrice : serviceItemPriceList) {
            ServiceItemPriceDto serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
            serviceItemPriceDtoList.add(serviceItemPriceDto);
        }
        return serviceItemPriceDtoList;
    }

    @Override
    public void delete(ServiceItemPriceDto obj) {
        ServiceItemPriceDao dao = new ServiceItemPriceDaoImpl();
        dao.delete(EntityDtoConverter.transformToServiceItemPriceEntity(obj));
    }

    @Override
    public void saveOrUpdate(ServiceItemPriceDto serviceItemPriceDto) {
        ServiceItemPriceDao serviceItemPriceDao = new ServiceItemPriceDaoImpl();

        if (serviceItemPriceDto.getId() == null) {
            serviceItemPriceDao.save(EntityDtoConverter.transformToServiceItemPriceEntity(serviceItemPriceDto));
        }else {
            serviceItemPriceDao.update(EntityDtoConverter.transformToServiceItemPriceEntity(serviceItemPriceDto));
        }
    }
}
