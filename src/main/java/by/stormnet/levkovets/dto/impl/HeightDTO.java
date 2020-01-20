package by.stormnet.levkovets.dto.impl;


import by.stormnet.levkovets.dto.DTO;

import java.util.Objects;

public class HeightDTO implements DTO {
    private Integer id;
    private String height;

    public HeightDTO() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeightDTO width1 = (HeightDTO) o;
        return Objects.equals(id, width1.id) &&
                Objects.equals(height, width1.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, height);
    }
}
