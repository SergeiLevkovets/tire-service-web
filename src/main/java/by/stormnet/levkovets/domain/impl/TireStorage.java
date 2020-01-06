package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Date;
import java.util.Objects;

public class TireStorage implements Entity {

    private Integer id;
    private String name;
    private Tire tire;
    private Date endDate;
    private User user;

    public TireStorage() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TireStorage that = (TireStorage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(tire, that.tire) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tire, endDate, user);
    }
}
