package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Tire;

import java.util.List;

public interface TireDao {
    Integer save(Tire tire);

    void update(Tire tire);

    void delete(Tire tire);

    Tire loadById(Integer id);

    List<Tire> loadAll();
}
