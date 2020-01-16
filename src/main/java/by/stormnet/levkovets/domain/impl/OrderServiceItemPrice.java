package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Objects;

public class OrderServiceItemPrice implements Entity {
    private Integer id;
    private Order order;
    private ServiceItemPrice serviceItemPrice;

    public OrderServiceItemPrice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ServiceItemPrice getServiceItemPrice() {
        return serviceItemPrice;
    }

    public void setServiceItemPrice(ServiceItemPrice serviceItemPrice) {
        this.serviceItemPrice = serviceItemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderServiceItemPrice that = (OrderServiceItemPrice) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(serviceItemPrice, that.serviceItemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, serviceItemPrice);
    }
}
