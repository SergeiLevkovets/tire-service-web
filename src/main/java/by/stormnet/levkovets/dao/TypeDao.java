package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Type;

import java.util.List;

public interface TypeDao {
    void save(Type type);

    void update(Type type);

    void delete(Type type);

    Type loadById(Integer id);

    List<Type> loadAll();

    Type loadByType(String type);
}
