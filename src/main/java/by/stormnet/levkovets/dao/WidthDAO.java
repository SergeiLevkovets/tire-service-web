package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.Width;

import java.util.List;

public interface WidthDAO {
    void save(Width width);

    void update(Width width);

    void delete(Width width);

    Width loadById(Integer id);

    List<Width> loadAll();

    Width loadBySize(String size);
}
