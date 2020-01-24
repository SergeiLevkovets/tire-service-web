package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Height;

import java.util.List;

public interface HeightDAO {
    void save(Height height);

    void update(Height height);

    void deleteById(Integer id);

    Height loadById(Integer id);

    List<Height> loadAll();

    Height loadBySize(String size);
}
