package by.stormnet.levkovets.dto.impl;


import by.stormnet.levkovets.dto.DTO;

import java.util.Objects;

public class DiameterDTO implements DTO {
    private Integer id;
    private String diameter;

    public DiameterDTO() {
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
        DiameterDTO width1 = (DiameterDTO) o;
        return Objects.equals(id, width1.id) &&
                Objects.equals(diameter, width1.diameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diameter);
    }

    @Override
    public String toString() {
        return "DiameterDTO{" +
                "id=" + id +
                ", diameter='" + diameter + '\'' +
                '}';
    }
}
