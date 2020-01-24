package by.stormnet.levkovets.dto.impl;


import by.stormnet.levkovets.dto.DTO;

import java.util.Objects;

public class ServiceItemDTO implements DTO {

    private Integer id;
    private String name;
    private String article;

    public ServiceItemDTO() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceItemDTO that = (ServiceItemDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(article, that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, article);
    }

    @Override
    public String toString() {
        return "ServiceItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}
