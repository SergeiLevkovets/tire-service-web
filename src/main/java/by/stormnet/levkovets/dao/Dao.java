package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.Entity;

import java.util.List;

public interface Dao<T extends Entity> {

    T loadById(Integer id);
    void save(T obj);
    void update(T obj);
    void delete(T obj);
    List<T> loadAll();
}
