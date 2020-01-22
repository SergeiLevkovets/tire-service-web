package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Objects;

public class Diameter implements Entity {
    private Integer id;
    private String diameter;

    public Diameter() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diameter width1 = (Diameter) o;
        return Objects.equals(id, width1.id) &&
                Objects.equals(diameter, width1.diameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diameter);
    }

    @Override
    public String toString() {
        return "Diameter{" +
                "id=" + id +
                ", diameter='" + diameter + '\'' +
                '}';
    }
}
