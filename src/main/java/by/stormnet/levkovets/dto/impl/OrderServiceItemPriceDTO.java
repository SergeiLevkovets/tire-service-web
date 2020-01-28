package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.DTO;

import java.util.Objects;

public class OrderServiceItemPriceDTO implements DTO {
    private Integer id;
    private OrderDTO order;
    private ServiceItemPriceDTO serviceItemPrice;
    private Integer count;
    private Double totalPrice;

    public OrderServiceItemPriceDTO() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public ServiceItemPriceDTO getServiceItemPrice() {
        return serviceItemPrice;
    }

    public void setServiceItemPrice(ServiceItemPriceDTO serviceItemPrice) {
        this.serviceItemPrice = serviceItemPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderServiceItemPriceDTO that = (OrderServiceItemPriceDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(serviceItemPrice, that.serviceItemPrice) &&
                Objects.equals(count, that.count) &&
                Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, serviceItemPrice, count, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderServiceItemPriceDTO{" +
                "id=" + id +
                ", order=" + order +
                ", serviceItemPrice=" + serviceItemPrice +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
