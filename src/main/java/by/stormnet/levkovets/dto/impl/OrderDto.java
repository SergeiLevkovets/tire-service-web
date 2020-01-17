package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.domain.impl.Tire;
import by.stormnet.levkovets.domain.impl.Type;
import by.stormnet.levkovets.domain.impl.User;
import by.stormnet.levkovets.dto.Dto;

import java.util.Date;
import java.util.Objects;

public class OrderDto implements Dto {
    private Integer id;
    private UserDto user;
    private TireDto tire;
    private TypeDto type;
    private Double totalPrice;
    private Date date;

    public OrderDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public TireDto getTire() {
        return tire;
    }

    public void setTire(TireDto tire) {
        this.tire = tire;
    }

    public TypeDto getType() {
        return type;
    }

    public void setType(TypeDto type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) &&
                Objects.equals(user, orderDto.user) &&
                Objects.equals(tire, orderDto.tire) &&
                Objects.equals(type, orderDto.type) &&
                Objects.equals(totalPrice, orderDto.totalPrice) &&
                Objects.equals(date, orderDto.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, tire, type, totalPrice, date);
    }
}
