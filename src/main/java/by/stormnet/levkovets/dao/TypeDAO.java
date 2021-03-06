package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Type;

import java.util.List;

public interface TypeDAO {
    void save(Type type);

    void update(Type type);

    void deleteById(Integer id);

    Type loadById(Integer id);

    List<Type> loadAll();

    Type loadByType(String type);
}
