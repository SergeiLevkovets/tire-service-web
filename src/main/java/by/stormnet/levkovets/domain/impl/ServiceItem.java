package by.stormnet.levkovets.domain.impl;

import by.stormnet.levkovets.domain.Entity;

import java.util.Objects;

public class ServiceItem implements Entity {

    private Integer id;
    private String name;
    private String article;

    public ServiceItem() {
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
        ServiceItem that = (ServiceItem) o;
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
        return "ServiceItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}
