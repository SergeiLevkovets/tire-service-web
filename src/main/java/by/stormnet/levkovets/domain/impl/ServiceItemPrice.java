package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Objects;

public class ServiceItemPrice implements Entity {

    private Integer id;
    private ServiceItem serviceItem;
    private Type type;
    private Diameter diameter;
    private Double price;

    public ServiceItemPrice() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceItem getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(ServiceItem serviceItem) {
        this.serviceItem = serviceItem;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Diameter getDiameter() {
        return diameter;
    }

    public void setDiameter(Diameter diameter) {
        this.diameter = diameter;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceItemPrice that = (ServiceItemPrice) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(serviceItem, that.serviceItem) &&
                Objects.equals(type, that.type) &&
                Objects.equals(diameter, that.diameter) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceItem, type, diameter, price);
    }
}
