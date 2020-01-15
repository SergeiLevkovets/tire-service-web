package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.Dto;

import java.util.Objects;

public class WidthDto implements Dto {

    private Integer id;
    private String width;

    public WidthDto() {
    }

    public Integer getId() {
        return id;
    }

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
        WidthDto widthDto = (WidthDto) o;
        return Objects.equals(id, widthDto.id) &&
                Objects.equals(width, widthDto.width);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width);
    }
}
