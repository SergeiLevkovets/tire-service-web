package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.DTO;

import java.util.Date;
import java.util.Objects;

public class OrderDTO implements DTO {
    private Integer id;
    private UserDTO user;
    private TireDTO tire;
    private TypeDTO type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) &&
                Objects.equals(user, orderDTO.user) &&
                Objects.equals(tire, orderDTO.tire) &&
                Objects.equals(type, orderDTO.type) &&
                Objects.equals(date, orderDTO.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, tire, type, date);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", user=" + user +
                ", tire=" + tire +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}
