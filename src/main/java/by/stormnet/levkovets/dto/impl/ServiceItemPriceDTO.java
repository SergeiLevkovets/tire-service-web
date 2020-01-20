package by.stormnet.levkovets.dto.impl;


import by.stormnet.levkovets.dto.DTO;

import java.util.Objects;

public class ServiceItemPriceDTO implements DTO {

    private Integer id;
    private ServiceItemDTO serviceItem;
    private TypeDTO type;
    private DiameterDTO diameter;
    private Double price;

    public ServiceItemPriceDTO() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceItemDTO getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(ServiceItemDTO serviceItem) {
        this.serviceItem = serviceItem;
    }

    public TypeDTO getType() {
        return type;
    }

    public void setType(TypeDTO type) {
        this.type = type;
    }

    public DiameterDTO getDiameter() {
        return diameter;
    }

    public void setDiameter(DiameterDTO diameter) {
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
        ServiceItemPriceDTO that = (ServiceItemPriceDTO) o;
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
