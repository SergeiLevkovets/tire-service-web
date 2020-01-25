package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.ServiceItemDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.dto.impl.ServiceItemDTO;
import by.stormnet.levkovets.dto.impl.TypeDTO;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class ServiceItemServiceImpl implements by.stormnet.levkovets.services.ServiceItemService {

    @Override
    public ServiceItemDTO getById(Integer id) {
        ServiceItemDAO serviceItemDao = DAOFactory.getFactory().getServiceItemDAO();
        ServiceItem serviceItem = serviceItemDao.loadById(id);
        ServiceItemDTO serviceItemDto = EntityDtoConverter.transformToServiceItemDto(serviceItem);
        return serviceItemDto;
    }

    @Override
    public ServiceItemDTO getByName(String name) {
        ServiceItemDAO serviceItemDao = DAOFactory.getFactory().getServiceItemDAO();
        ServiceItem serviceItem = serviceItemDao.loadByArticle(name);
        ServiceItemDTO serviceItemDto = null;
        if (serviceItem != null) {
            serviceItemDto = EntityDtoConverter.transformToServiceItemDto(serviceItem);
        }
        return serviceItemDto;
    }

    @Override
    public List<ServiceItemDTO> getAll() {
        ServiceItemDAO serviceItemDao = DAOFactory.getFactory().getServiceItemDAO();
        List<ServiceItem> serviceItemList = serviceItemDao.loadAll();
        List<ServiceItemDTO> serviceItemDtoList = new ArrayList<>();
        for (ServiceItem serviceItem : serviceItemList) {
            ServiceItemDTO serviceItemDto = EntityDtoConverter.transformToServiceItemDto(serviceItem);
            serviceItemDtoList.add(serviceItemDto);
        }
        return serviceItemDtoList;
    }

    @Override
    public List<ServiceItemDTO> getAllByTypeInServiceItemPrice(TypeDTO type) {
        ServiceItemDAO serviceItemDao = DAOFactory.getFactory().getServiceItemDAO();
        List<ServiceItem> serviceItemList = serviceItemDao.loadAllByTypeInServiceItemPrice(EntityDtoConverter.transformToTypeEntity(type));
        List<ServiceItemDTO> serviceItemDtoList = new ArrayList<>();
        for (ServiceItem serviceItem : serviceItemList) {
            ServiceItemDTO serviceItemDto = EntityDtoConverter.transformToServiceItemDto(serviceItem);
            serviceItemDtoList.add(serviceItemDto);
        }
        return serviceItemDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        ServiceItemDAO dao = DAOFactory.getFactory().getServiceItemDAO();
        dao.deleteById(id);
    }

    @Override
    public void saveOrUpdate(ServiceItemDTO serviceItemDto) {
        ServiceItemDAO serviceItemDao = DAOFactory.getFactory().getServiceItemDAO();

        if (serviceItemDto.getId() == null) {
            serviceItemDao.save(EntityDtoConverter.transformToServiceItemEntity(serviceItemDto));
        }else {
            serviceItemDao.update(EntityDtoConverter.transformToServiceItemEntity(serviceItemDto));
        }
    }
}
