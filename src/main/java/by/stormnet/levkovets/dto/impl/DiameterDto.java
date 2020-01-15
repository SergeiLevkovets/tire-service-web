package by.stormnet.levkovets.dto.impl;


import by.stormnet.levkovets.dto.Dto;

import java.util.Objects;

public class DiameterDto implements Dto {
    private Integer id;
    private String diameter;

    public DiameterDto() {
    }

    public Integer getId() {
        return id;
    }

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
        DiameterDto width1 = (DiameterDto) o;
        return Objects.equals(id, width1.id) &&
                Objects.equals(diameter, width1.diameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, diameter);
    }
}
