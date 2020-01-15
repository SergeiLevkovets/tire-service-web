package by.stormnet.levkovets.dto.impl;


import by.stormnet.levkovets.dto.Dto;

import java.util.Objects;

public class TypeDto implements Dto {

    private Integer id;
    private String type;

    public TypeDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeDto type1 = (TypeDto) o;
        return Objects.equals(id, type1.id) &&
                Objects.equals(type, type1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
