package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.DTO;

import java.util.List;
import java.util.Objects;

public class OrderStatisticDTO implements DTO {

    private Integer id;
    private OrderDTO order;
    private Double totalPrice;
private List<OrderServiceItemPriceDTO> orderServiceItemPriceDTOList;

    public OrderStatisticDTO() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public List<OrderServiceItemPriceDTO> getOrderServiceItemPriceDTOList() {
        return orderServiceItemPriceDTOList;
    }

    public void setOrderServiceItemPriceDTOList(List<OrderServiceItemPriceDTO> orderServiceItemPriceDTOList) {
        this.orderServiceItemPriceDTOList = orderServiceItemPriceDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatisticDTO that = (OrderStatisticDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(order, that.order) &&
                Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderStatisticDTO{" +
                "id=" + id +
                ", order=" + order +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
