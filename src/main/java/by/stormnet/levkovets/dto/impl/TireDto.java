package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.Dto;

import java.util.Date;
import java.util.Objects;

public class TireDto implements Dto {
    private Integer id;
    private WidthDto width;
    private HeightDto height;
    private DiameterDto diameter;
    private Date date;

    public TireDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WidthDto getWidth() {
        return width;
    }

    public void setWidth(WidthDto width) {
        this.width = width;
    }

    public HeightDto getHeight() {
        return height;
    }

    public void setHeight(HeightDto height) {
        this.height = height;
    }

    public DiameterDto getDiameter() {
        return diameter;
    }

    public void setDiameter(DiameterDto diameter) {
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
        TireDto tireDto = (TireDto) o;
        return Objects.equals(id, tireDto.id) &&
                Objects.equals(width, tireDto.width) &&
                Objects.equals(height, tireDto.height) &&
                Objects.equals(diameter, tireDto.diameter) &&
                Objects.equals(date, tireDto.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width, height, diameter, date);
    }
}
