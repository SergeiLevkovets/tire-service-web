package by.stormnet.levkovets.services.impl;

import by.stormnet.levkovets.dao.OrderServiceItemPriceDAO;
import by.stormnet.levkovets.dao.factory.DAOFactory;
import by.stormnet.levkovets.domain.impl.OrderServiceItemPrice;
import by.stormnet.levkovets.dto.impl.OrderDTO;
import by.stormnet.levkovets.dto.impl.OrderServiceItemPriceDTO;
import by.stormnet.levkovets.dto.impl.ServiceItemPriceDTO;
import by.stormnet.levkovets.services.OrderServiceItemPriceService;
import by.stormnet.levkovets.services.converters.EntityDtoConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void deleteById(Integer id) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        dao.deleteById(id);
    }

    @Override
    public void deleteByOrderId(Integer id) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        dao.deleteByOrderId(id);
    }

    @Override
    public void saveOrUpdate(OrderServiceItemPriceDTO dto) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        if (dto.getId() == null) {
            dao.save(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
        } else {
            dao.update(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
        }
    }

    @Override
    public void saveOrUpdateAll(List<OrderServiceItemPriceDTO> list) {
        OrderServiceItemPriceDAO dao = DAOFactory.getFactory().getOrderServiceItemPriceDAO();
        List<OrderServiceItemPrice> saveEntityList = new ArrayList<>();
        List<OrderServiceItemPrice> updateEntityList = new ArrayList<>();
        for (OrderServiceItemPriceDTO dto : list) {
            if (dto.getId() == null) {
                saveEntityList.add(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
            } else {
                updateEntityList.add(EntityDtoConverter.transformToOrderServiceItemPriceEntity(dto));
            }
        }
        if (!saveEntityList.isEmpty()){
            dao.saveAll(saveEntityList);
        }
        if (!updateEntityList.isEmpty()){
            dao.updateAll(updateEntityList);
        }
    }

    @Override
    public List<OrderServiceItemPriceDTO> createOrderToServiceItemPrices(OrderDTO orderDto, Map<ServiceItemPriceDTO, Integer> serviceItemPricesAndCount) {

        List<OrderServiceItemPriceDTO> list = new ArrayList<>();

        for (ServiceItemPriceDTO serviceItemPriceDTO : serviceItemPricesAndCount.keySet()) {

            Integer count = serviceItemPricesAndCount.get(serviceItemPriceDTO);
            Double totalPrice = serviceItemPriceDTO.getPrice() * count;

            OrderServiceItemPriceDTO orderServiceItemPriceDTO = new OrderServiceItemPriceDTO();
            orderServiceItemPriceDTO.setOrder(orderDto);
            orderServiceItemPriceDTO.setServiceItemPrice(serviceItemPriceDTO);
            orderServiceItemPriceDTO.setCount(count);
            orderServiceItemPriceDTO.setTotalPrice(totalPrice);

            list.add(orderServiceItemPriceDTO);

        }

        saveOrUpdateAll(list);

        return list;
    }


}
