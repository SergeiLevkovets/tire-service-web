package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Objects;

public class OrderServiceItemPrice implements Entity {
    private Integer id;
    private Order order;
    private ServiceItemPrice serviceItemPrice;
    private Integer count;
    private Double totalPrice;


    public OrderServiceItemPrice() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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
        OrderServiceItemPrice that = (OrderServiceItemPrice) o;
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
        return "OrderServiceItemPrice{" +
                "id=" + id +
                ", order=" + order +
                ", serviceItemPrice=" + serviceItemPrice +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
