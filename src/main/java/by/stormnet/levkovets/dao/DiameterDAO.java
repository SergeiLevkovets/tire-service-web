package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Diameter;

import java.util.List;

public interface DiameterDAO {
    void save(Diameter diameter);

    void update(Diameter diameter);

    void delete(Diameter diameter);

    Diameter loadById(Integer id);

    List<Diameter> loadAll();

    Diameter loadBySize(String size);
}
