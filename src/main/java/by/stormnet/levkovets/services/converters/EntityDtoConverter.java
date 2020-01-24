package by.stormnet.levkovets.services.converters;

import by.stormnet.levkovets.domain.impl.*;
import by.stormnet.levkovets.dto.impl.*;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoConverter {

    public EntityDtoConverter() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static OrderServiceItemPriceDTO transformToOrderServiceItemPriceDto(OrderServiceItemPrice orderServiceItemPrice){
        OrderServiceItemPriceDTO orderServiceItemPriceDto = new OrderServiceItemPriceDTO();
        orderServiceItemPriceDto.setId(orderServiceItemPrice.getId());
        orderServiceItemPriceDto.setOrder(transformToOrderDto(orderServiceItemPrice.getOrder()));
        orderServiceItemPriceDto.setServiceItemPrice(transformToServiceItemPriceDto(orderServiceItemPrice.getServiceItemPrice()));
        orderServiceItemPriceDto.setCount(orderServiceItemPrice.getCount());
        return orderServiceItemPriceDto;
    }

    public static OrderServiceItemPrice transformToOrderServiceItemPriceEntity(OrderServiceItemPriceDTO orderServiceItemPriceDto){
        OrderServiceItemPrice orderServiceItemPrice = new OrderServiceItemPrice();
        orderServiceItemPrice.setId(orderServiceItemPriceDto.getId());
        orderServiceItemPrice.setOrder(transformToOrderEntity(orderServiceItemPriceDto.getOrder()));
        orderServiceItemPrice.setServiceItemPrice(transformToServiceItemPriceEntity(orderServiceItemPriceDto.getServiceItemPrice()));
        if (orderServiceItemPriceDto.getCount() == null){
            orderServiceItemPrice.setCount(1);
        }else {
            orderServiceItemPrice.setCount(orderServiceItemPriceDto.getCount());
        }
        return orderServiceItemPrice;
    }

    public static OrderDTO transformToOrderDto(Order order){
        OrderDTO orderDto = new OrderDTO();
        orderDto.setId(order.getId());
        orderDto.setUser(transformToUserDto(order.getUser()));
        orderDto.setTire(transformToTireDto(order.getTire()));
        orderDto.setType(transformToTypeDto(order.getType()));
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setDate(order.getDate());
        return orderDto;
    }

    public static Order transformToOrderEntity(OrderDTO orderDto){
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setUser(transformToUserEntity(orderDto.getUser()));
        order.setTire(transformToTireEntity(orderDto.getTire()));
        order.setType(transformToTypeEntity(orderDto.getType()));
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setDate(orderDto.getDate());
        return order;
    }

    public static TireDTO transformToTireDto(Tire tire){
        TireDTO tireDto = new TireDTO();
        tireDto.setId(tire.getId());
        tireDto.setWidth(transformToWidthDto(tire.getWidth()));
        tireDto.setHeight(transformToHeightDto(tire.getHeight()));
        tireDto.setDiameter(transformToDiameterDto(tire.getDiameter()));
        tireDto.setDate(tire.getDate());
        return tireDto;
    }

    public static Tire transformToTireEntity(TireDTO tireDto){
        Tire tire = new Tire();
        tire.setId(tireDto.getId());
        tire.setWidth(transformToWidthEntity(tireDto.getWidth()));
        tire.setHeight(transformToHeightEntity(tireDto.getHeight()));
        tire.setDiameter(transformToDiameterEntity(tireDto.getDiameter()));
        tire.setDate(tireDto.getDate());
        return tire;
    }

    public static DiameterDTO transformToDiameterDto(Diameter diameter){
        DiameterDTO diameterDto = new DiameterDTO();
        diameterDto.setId(diameter.getId());
        diameterDto.setDiameter(diameter.getDiameter());
        return diameterDto;
    }

    public static Diameter transformToDiameterEntity(DiameterDTO diameterDto){
        Diameter diameter = new Diameter();
        diameter.setId(diameterDto.getId());
        diameter.setDiameter(diameterDto.getDiameter());
        return diameter;
    }

    public static HeightDTO transformToHeightDto(Height height){
        HeightDTO heightDto = new HeightDTO();
        heightDto.setId(height.getId());
        heightDto.setHeight(height.getHeight());
        return heightDto;
    }

    public static Height transformToHeightEntity(HeightDTO heightDto){
        Height height = new Height();
        height.setId(heightDto.getId());
        height.setHeight(heightDto.getHeight());
        return height;
    }

    public static WidthDTO transformToWidthDto(Width width){
        WidthDTO widthDto = new WidthDTO();
        widthDto.setId(width.getId());
        widthDto.setWidth(width.getWidth());
        return widthDto;
    }

    public static Width transformToWidthEntity(WidthDTO widthDto){
        Width width = new Width();
        width.setId(widthDto.getId());
        width.setWidth(widthDto.getWidth());
        return width;
    }

    public static ServiceItemDTO transformToServiceItemDto(ServiceItem serviceItem){
        ServiceItemDTO serviceItemDto = new ServiceItemDTO();
        serviceItemDto.setId(serviceItem.getId());
        serviceItemDto.setName(serviceItem.getName());
        serviceItemDto.setArticle(serviceItem.getArticle());
        return serviceItemDto;
    }

    public static ServiceItem transformToServiceItemEntity(ServiceItemDTO serviceItemDto){
        ServiceItem ServiceItem = new ServiceItem();
        ServiceItem.setId(serviceItemDto.getId());
        ServiceItem.setName(serviceItemDto.getName());
        ServiceItem.setArticle(serviceItemDto.getArticle());
        return ServiceItem;
    }

    public static ServiceItemPriceDTO transformToServiceItemPriceDto(ServiceItemPrice serviceItem){
        ServiceItemPriceDTO serviceItemPriceDto = new ServiceItemPriceDTO();
        serviceItemPriceDto.setId(serviceItem.getId());
        if (serviceItem.getDiameter() != null) {
            serviceItemPriceDto.setDiameter(transformToDiameterDto(serviceItem.getDiameter()));
        }
        serviceItemPriceDto.setServiceItem(transformToServiceItemDto(serviceItem.getServiceItem()));
        serviceItemPriceDto.setType(transformToTypeDto(serviceItem.getType()));
        serviceItemPriceDto.setPrice(serviceItem.getPrice());
        return serviceItemPriceDto;
    }

    public static List<ServiceItemPriceDTO> transformAllToServiceItemPriceDto(List<ServiceItemPrice> serviceItemPriceList){
        List<ServiceItemPriceDTO> serviceItemPriceDtoList = new ArrayList<>();
        for (ServiceItemPrice serviceItemPrice : serviceItemPriceList) {
            ServiceItemPriceDTO serviceItemPriceDto = EntityDtoConverter.transformToServiceItemPriceDto(serviceItemPrice);
            serviceItemPriceDtoList.add(serviceItemPriceDto);
        }
        return serviceItemPriceDtoList;
    }

    public static ServiceItemPrice transformToServiceItemPriceEntity(ServiceItemPriceDTO serviceItemPriceDto){
        ServiceItemPrice serviceItemPrice = new ServiceItemPrice();
        serviceItemPrice.setId(serviceItemPriceDto.getId());
        if (serviceItemPriceDto.getDiameter() != null) {
            serviceItemPrice.setDiameter(transformToDiameterEntity(serviceItemPriceDto.getDiameter()));
        }
        serviceItemPrice.setServiceItem(transformToServiceItemEntity(serviceItemPriceDto.getServiceItem()));
        serviceItemPrice.setType(transformToTypeEntity(serviceItemPriceDto.getType()));
        serviceItemPrice.setPrice(serviceItemPriceDto.getPrice());
        return serviceItemPrice;
    }

    public static TypeDTO transformToTypeDto(Type type){
        TypeDTO typeDto = new TypeDTO();
        typeDto.setId(type.getId());
        typeDto.setType(type.getType());
        return typeDto;
    }

    public static Type transformToTypeEntity(TypeDTO typeDto){
        Type type = new Type();
        type.setId(typeDto.getId());
        type.setType(typeDto.getType());
        return type;
    }

    public static UserDTO transformToUserDto(User user) {
        UserDTO userDto = new UserDTO();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static User transformToUserEntity(UserDTO userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());

        return user;
    }

}
