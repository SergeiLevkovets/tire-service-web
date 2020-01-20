package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.OrderServiceItemPriceDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.OrderServiceItemPrice;
import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.dto.impl.OrderServiceItemPriceDTO;
import by.stormnet.levkovets.services.OrderServiceItemPriceService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceItemPriceServiceImpl implements OrderServiceItemPriceService {
    @Override
    public OrderServiceItemPriceDTO getById(Integer id) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();

        OrderServiceItemPriceDTO dto = EntityDtoConverter.transformToOrderServiceItemPriceDto(dao.loadById(id));

        return dto;
    }

    @Override
    public List<OrderServiceItemPriceDTO> getAll() {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        List<OrderServiceItemPriceDTO> list = new ArrayList<>();
        for (OrderServiceItemPrice entity : dao.loadAll()) {
            list.add(EntityDtoConverter.transformToOrderServiceItemPriceDto(entity));
        }
        return list;
    }

    @Override
    public List<OrderServiceItemPriceDTO> getAllByOrder(OrderDTO dto) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        List<OrderServiceItemPriceDTO> list = new ArrayList<>();
        for (OrderServiceItemPrice entity : dao.loadAllByOrder(EntityDtoConverter.transformToOrderEntity(dto))) {
            list.add(EntityDtoConverter.transformToOrderServiceItemPriceDto(entity));
        }
        return list;
    }

    @Override
    public void delete(OrderServiceItemPriceDTO obj) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        dao.delete(EntityDtoConverter.transformToOrderServiceItemPriceEntity(obj));
    }

    @Override
    public void deleteByOrder(OrderDTO obj) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        dao.deleteByOrder(EntityDtoConverter.transformToOrderEntity(obj));
    }

    @Override
    public void saveOrUpdate(OrderServiceItemPriceDTO dto) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        if (dto.getId() == null) {
            dao.save(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
        }else {
            dao.update(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
        }
    }


}
