package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.ServiceItemPriceDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.ServiceItemPrice;
import by.stormnet.levkovets.dto.impl.ServiceItemDTO;
import by.stormnet.levkovets.dto.impl.ServiceItemPriceDTO;
import by.stormnet.levkovets.dto.impl.TypeDTO;
import by.stormnet.levkovets.services.ServiceItemPriceService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemPriceServiceImpl implements ServiceItemPriceService {

    @Override
    public ServiceItemPriceDTO getById(Integer id) {
        ServiceItemPriceDAO serviceItemPriceDao = DAOFactory.getFactory().getServiceItemPriceDAO();
        ServiceItemPrice serviceItemPrice = serviceItemPriceDao.loadById(id);
        ServiceItemPriceDTO serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
        return serviceItemPriceDto;
    }

    @Override
    public List<ServiceItemPriceDTO> getAllByType(TypeDTO type) {
        ServiceItemPriceDAO serviceItemPriceDao = DAOFactory.getFactory().getServiceItemPriceDAO();
        List<ServiceItemPrice> serviceItemPriceList = serviceItemPriceDao.loadAllByType(EntityDtoConverter.transformToTypeEntity(type));
        List<ServiceItemPriceDTO> serviceItemPriceDtoList = new ArrayList<>();
        for (ServiceItemPrice serviceItemPrice : serviceItemPriceList) {
            ServiceItemPriceDTO serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
            serviceItemPriceDtoList.add(serviceItemPriceDto);
        }
        return serviceItemPriceDtoList;
    }

    @Override
    public List<ServiceItemPriceDTO> getAllServiceItemPriceByServiceItem(ServiceItemDTO serviceItem) {
        ServiceItemPriceDAO serviceItemPriceDao = DAOFactory.getFactory().getServiceItemPriceDAO();
        List<ServiceItemPrice> serviceItemPriceList = serviceItemPriceDao.loadAllByItem(EntityDtoConverter.transformToServiceItemEntity(serviceItem));
        List<ServiceItemPriceDTO> serviceItemPriceDtoList = new ArrayList<>();
        for (ServiceItemPrice serviceItemPrice : serviceItemPriceList) {
            ServiceItemPriceDTO serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
            serviceItemPriceDtoList.add(serviceItemPriceDto);
        }
        return serviceItemPriceDtoList;
    }

    @Override
    public List<ServiceItemPriceDTO> getAll() {
        ServiceItemPriceDAO serviceItemPriceDao = DAOFactory.getFactory().getServiceItemPriceDAO();
        List<ServiceItemPrice> serviceItemPriceList = serviceItemPriceDao.loadAll();
        List<ServiceItemPriceDTO> serviceItemPriceDtoList = new ArrayList<>();
        for (ServiceItemPrice serviceItemPrice : serviceItemPriceList) {
            ServiceItemPriceDTO serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
            serviceItemPriceDtoList.add(serviceItemPriceDto);
        }
        return serviceItemPriceDtoList;
    }

    @Override
    public void delete(ServiceItemPriceDTO obj) {
        ServiceItemPriceDAO dao = DAOFactory.getFactory().getServiceItemPriceDAO();
        dao.delete(EntityDtoConverter.transformToServiceItemPriceEntity(obj));
    }

    @Override
    public void saveOrUpdate(ServiceItemPriceDTO serviceItemPriceDto) {
        ServiceItemPriceDAO serviceItemPriceDao = DAOFactory.getFactory().getServiceItemPriceDAO();

        if (serviceItemPriceDto.getId() == null) {
            serviceItemPriceDao.save(EntityDtoConverter.transformToServiceItemPriceEntity(serviceItemPriceDto));
        } else {
            serviceItemPriceDao.update(EntityDtoConverter.transformToServiceItemPriceEntity(serviceItemPriceDto));
        }
    }
}
