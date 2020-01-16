package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.Dto;

import java.util.Objects;

public class OrderServiceItemPriceDto implements Dto {
    private Integer id;
    private OrderDto order;
    private ServiceItemPriceDto serviceItemPrice;

    public OrderServiceItemPriceDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public ServiceItemPriceDto getServiceItemPrice() {
        return serviceItemPrice;
    }

    public void setServiceItemPrice(ServiceItemPriceDto serviceItemPrice) {
        this.serviceItemPrice = serviceItemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderServiceItemPriceDto that = (OrderServiceItemPriceDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(serviceItemPrice, that.serviceItemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, serviceItemPrice);
    }
}
