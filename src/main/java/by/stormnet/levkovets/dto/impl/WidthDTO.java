package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.DTO;

import java.util.Objects;

public class WidthDTO implements DTO {

    private Integer id;
    private String width;

    public WidthDTO() {
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
        WidthDTO widthDto = (WidthDTO) o;
        return Objects.equals(id, widthDto.id) &&
                Objects.equals(width, widthDto.width);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width);
    }
}
