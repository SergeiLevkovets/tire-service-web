package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Objects;

public class Patch implements Entity {
    private Integer id;
    private String name;
    private Integer count;
    private Double price;

    public Patch() {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        Patch patch = (Patch) o;
        return Objects.equals(id, patch.id) &&
                Objects.equals(name, patch.name) &&
                Objects.equals(count, patch.count) &&
                Objects.equals(price, patch.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, count, price);
    }
}
