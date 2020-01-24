package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.ServiceItem;

import java.util.List;

public interface ServiceItemDAO {
    void save(ServiceItem serviceItem);

    void update(ServiceItem serviceItem);

    void deleteById(Integer id);

    ServiceItem loadById(Integer id);

    List<ServiceItem> loadAll();

    ServiceItem loadByArticle(String name);
}
