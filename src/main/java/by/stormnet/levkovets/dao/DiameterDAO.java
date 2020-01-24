package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Diameter;

import java.util.List;

public interface DiameterDAO {
    void save(Diameter diameter);

    void update(Diameter diameter);

    void deleteById(Integer id);

    Diameter loadById(Integer id);

    List<Diameter> loadAll();

    Diameter loadBySize(String size);
}
