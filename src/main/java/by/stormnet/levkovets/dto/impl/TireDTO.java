package by.stormnet.levkovets.dto.impl;

import by.stormnet.levkovets.dto.DTO;

import java.util.Date;
import java.util.Objects;

public class TireDTO implements DTO {
    private Integer id;
    private WidthDTO width;
    private HeightDTO height;
    private DiameterDTO diameter;
    private Date date;

    public TireDTO() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public WidthDTO getWidth() {
        return width;
    }

    public void setWidth(WidthDTO width) {
        this.width = width;
    }

    public HeightDTO getHeight() {
        return height;
    }

    public void setHeight(HeightDTO height) {
        this.height = height;
    }

    public DiameterDTO getDiameter() {
        return diameter;
    }

    public void setDiameter(DiameterDTO diameter) {
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
        TireDTO tireDto = (TireDTO) o;
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

    @Override
    public String toString() {
        return "TireDTO{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", diameter=" + diameter +
                ", date=" + date +
                '}';
    }
}
