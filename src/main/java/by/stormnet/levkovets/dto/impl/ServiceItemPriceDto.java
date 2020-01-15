package by.stormnet.levkovets.dto.impl;


import by.stormnet.levkovets.dto.Dto;

import java.util.Objects;

public class ServiceItemPriceDto implements Dto {

    private Integer id;
    private ServiceItemDto serviceItem;
    private TypeDto type;
    private DiameterDto diameter;
    private Double price;

    public ServiceItemPriceDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceItemDto getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(ServiceItemDto serviceItem) {
        this.serviceItem = serviceItem;
    }

    public TypeDto getType() {
        return type;
    }

    public void setType(TypeDto type) {
        this.type = type;
    }

    public DiameterDto getDiameter() {
        return diameter;
    }

    public void setDiameter(DiameterDto diameter) {
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
        ServiceItemPriceDto that = (ServiceItemPriceDto) o;
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
