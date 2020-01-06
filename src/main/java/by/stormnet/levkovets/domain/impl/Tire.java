package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Date;
import java.util.Objects;

public class Tire implements Entity {
    private Integer id;
    private Integer width;
    private Integer height;
    private Integer diameter;
    private Date date;

    public Tire() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
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
        Tire tire = (Tire) o;
        return Objects.equals(id, tire.id) &&
                Objects.equals(width, tire.width) &&
                Objects.equals(height, tire.height) &&
                Objects.equals(diameter, tire.diameter) &&
                Objects.equals(date, tire.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width, height, diameter, date);
    }
}
