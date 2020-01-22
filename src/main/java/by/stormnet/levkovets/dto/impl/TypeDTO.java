package by.stormnet.levkovets.dto.impl;


import by.stormnet.levkovets.dto.DTO;

import java.util.Objects;

public class TypeDTO implements DTO {

    private Integer id;
    private String type;

    public TypeDTO() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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
        TypeDTO type1 = (TypeDTO) o;
        return Objects.equals(id, type1.id) &&
                Objects.equals(type, type1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "TypeDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
