package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.Dto;

import java.util.Objects;

public class OrderServiceItemPriceDto implements Dto {
    private Integer id;
    private OrderDto order;
    private ServiceItemPriceDto serviceItemPrice;
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderServiceItemPriceDto that = (OrderServiceItemPriceDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(serviceItemPrice, that.serviceItemPrice) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, serviceItemPrice, count);
    }
}
