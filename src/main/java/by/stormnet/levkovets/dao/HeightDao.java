package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Height;

import java.util.List;

public interface HeightDao {
    void save(Height height);

    void update(Height height);

    void delete(Height height);

    Height loadById(Integer id);

    List<Height> loadAll();

    Height loadBySize(String size);
}
