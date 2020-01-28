package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Tire;

import java.util.List;

public interface TireDAO {
    Integer save(Tire tire);

    void update(Tire tire);

    void deleteById(Integer id);

    Tire loadById(Integer id);

    List<Tire> loadAll();

    List<Tire> loadCountTOP(Integer count);

}
