package by.stormnet.levkovets.services.converters;

import by.stormnet.levkovets.domain.impl.*;
import by.stormnet.levkovets.dto.impl.*;

public class EntityDtoConverter {

    public EntityDtoConverter() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static OrderServiceItemPriceDto transformToOrderServiceItemPriceDto(OrderServiceItemPrice orderServiceItemPrice){
        OrderServiceItemPriceDto orderServiceItemPriceDto = new OrderServiceItemPriceDto();
        orderServiceItemPriceDto.setId(orderServiceItemPrice.getId());
        orderServiceItemPriceDto.setOrder(transformToOrderDto(orderServiceItemPrice.getOrder()));
        orderServiceItemPriceDto.setServiceItemPrice(transformToServiceItemPriceDto(orderServiceItemPrice.getServiceItemPrice()));
        orderServiceItemPriceDto.setCount(orderServiceItemPrice.getCount());
        return orderServiceItemPriceDto;
    }

    public static OrderServiceItemPrice transformToOrderServiceItemPriceEntity(OrderServiceItemPriceDto orderServiceItemPriceDto){
        OrderServiceItemPrice orderServiceItemPrice = new OrderServiceItemPrice();
        orderServiceItemPrice.setId(orderServiceItemPrice.getId());
        orderServiceItemPrice.setOrder(transformToOrderEntity(orderServiceItemPriceDto.getOrder()));
        orderServiceItemPrice.setServiceItemPrice(transformToServiceItemPriceEntity(orderServiceItemPriceDto.getServiceItemPrice()));
        if (orderServiceItemPriceDto.getCount() == null){
            orderServiceItemPrice.setCount(1);
        }else {
            orderServiceItemPrice.setCount(orderServiceItemPriceDto.getCount());
        }
        return orderServiceItemPrice;
    }

    public static OrderDto transformToOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUser(transformToUserDto(order.getUser()));
        orderDto.setTire(transformToTireDto(order.getTire()));
        orderDto.setType(transformToTypeDto(order.getType()));
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setDate(order.getDate());
        return orderDto;
    }

    public static Order transformToOrderEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(order.getId());
        order.setUser(transformToUserEntity(orderDto.getUser()));
        order.setTire(transformToTireEntity(orderDto.getTire()));
        order.setType(transformToTypeEntity(orderDto.getType()));
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setDate(orderDto.getDate());
        return order;
    }

    public static TireDto transformToTireDto(Tire tire){
        TireDto tireDto = new TireDto();
        tireDto.setId(tire.getId());
        tireDto.setWidth(transformToWidthDto(tire.getWidth()));
        tireDto.setHeight(transformToHeightDto(tire.getHeight()));
        tireDto.setDiameter(transformToDiameterDto(tire.getDiameter()));
        tireDto.setDate(tire.getDate());
        return tireDto;
    }

    public static Tire transformToTireEntity(TireDto tireDto){
        Tire tire = new Tire();
        tire.setId(tire.getId());
        tire.setWidth(transformToWidthEntity(tireDto.getWidth()));
        tire.setHeight(transformToHeightEntity(tireDto.getHeight()));
        tire.setDiameter(transformToDiameterEntity(tireDto.getDiameter()));
        tire.setDate(tireDto.getDate());
        return tire;
    }

    public static DiameterDto transformToDiameterDto(Diameter diameter){
        DiameterDto diameterDto = new DiameterDto();
        diameterDto.setId(diameter.getId());
        diameterDto.setDiameter(diameter.getDiameter());
        return diameterDto;
    }

    public static Diameter transformToDiameterEntity(DiameterDto diameterDto){
        Diameter diameter = new Diameter();
        diameter.setId(diameterDto.getId());
        diameter.setDiameter(diameterDto.getDiameter());
        return diameter;
    }

    public static HeightDto transformToHeightDto(Height height){
        HeightDto heightDto = new HeightDto();
        heightDto.setId(height.getId());
        heightDto.setHeight(height.getHeight());
        return heightDto;
    }

    public static Height transformToHeightEntity(HeightDto heightDto){
        Height height = new Height();
        height.setId(heightDto.getId());
        height.setHeight(heightDto.getHeight());
        return height;
    }

    public static WidthDto transformToWidthDto(Width width){
        WidthDto widthDto = new WidthDto();
        widthDto.setId(width.getId());
        widthDto.setWidth(width.getWidth());
        return widthDto;
    }

    public static Width transformToWidthEntity(WidthDto widthDto){
        Width width = new Width();
        width.setId(widthDto.getId());
        width.setWidth(widthDto.getWidth());
        return width;
    }

    public static ServiceItemDto transformToServiceItemDto(ServiceItem serviceItem){
        ServiceItemDto serviceItemDto = new ServiceItemDto();
        serviceItemDto.setId(serviceItem.getId());
        serviceItemDto.setName(serviceItem.getName());
        return serviceItemDto;
    }

    public static ServiceItem transformToServiceItemEntity(ServiceItemDto serviceItemDto){
        ServiceItem ServiceItem = new ServiceItem();
        ServiceItem.setId(serviceItemDto.getId());
        ServiceItem.setName(serviceItemDto.getName());
        return ServiceItem;
    }

    public static ServiceItemPriceDto transformToServiceItemPriceDto(ServiceItemPrice serviceItem){
        ServiceItemPriceDto serviceItemPriceDto = new ServiceItemPriceDto();
        serviceItemPriceDto.setId(serviceItem.getId());
        if (serviceItem.getDiameter() != null) {
            serviceItemPriceDto.setDiameter(transformToDiameterDto(serviceItem.getDiameter()));
        }
        serviceItemPriceDto.setServiceItem(transformToServiceItemDto(serviceItem.getServiceItem()));
        serviceItemPriceDto.setType(transformToTypeDto(serviceItem.getType()));
        serviceItemPriceDto.setPrice(serviceItem.getPrice());
        return serviceItemPriceDto;
    }

    public static ServiceItemPrice transformToServiceItemPriceEntity(ServiceItemPriceDto serviceItemPriceDto){
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

    public static TypeDto transformToTypeDto(Type type){
        TypeDto typeDto = new TypeDto();
        typeDto.setId(type.getId());
        typeDto.setType(type.getType());
        return typeDto;
    }

    public static Type transformToTypeEntity(TypeDto typeDto){
        Type type = new Type();
        type.setId(typeDto.getId());
        type.setType(typeDto.getType());
        return type;
    }

    public static UserDto transformToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

    public static User transformToUserEntity(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());

        return user;
    }

}
