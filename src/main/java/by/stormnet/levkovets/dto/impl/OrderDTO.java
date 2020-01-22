package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.DTO;

import java.util.Date;
import java.util.Objects;

public class OrderDTO implements DTO {
    private Integer id;
    private UserDTO user;
    private TireDTO tire;
    private TypeDTO type;
    private Double totalPrice;
    private Date date;

    public OrderDTO() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TireDTO getTire() {
        return tire;
    }

    public void setTire(TireDTO tire) {
        this.tire = tire;
    }

    public TypeDTO getType() {
        return type;
    }

    public void setType(TypeDTO type) {
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
        OrderDTO orderDto = (OrderDTO) o;
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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", user=" + user +
                ", tire=" + tire +
                ", type=" + type +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}
