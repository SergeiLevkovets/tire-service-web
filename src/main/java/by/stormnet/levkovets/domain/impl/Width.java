package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Objects;

public class Width implements Entity {
    private Integer id;
    private String width;

    public Width() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Width width1 = (Width) o;
        return Objects.equals(id, width1.id) &&
                Objects.equals(width, width1.width);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width);
    }

    @Override
    public String toString() {
        return "Width{" +
                "id=" + id +
                ", width='" + width + '\'' +
                '}';
    }
}
