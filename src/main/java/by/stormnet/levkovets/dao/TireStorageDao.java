package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.TireStorage;

import java.util.List;

public interface TireStorageDao {
    void save(TireStorage tireStorage);

    void update(TireStorage tireStorage);

    void delete(TireStorage tireStorage);

    TireStorage loadById(Integer id);

    List<TireStorage> loadAll();
}
