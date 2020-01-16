package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.ServiceItem;

import java.util.List;

public interface ServiceItemDao {
    void save(ServiceItem serviceItem);

    void update(ServiceItem serviceItem);

    void delete(ServiceItem serviceItem);

    ServiceItem loadById(Integer id);

    List<ServiceItem> loadAll();

    ServiceItem loadByName(String name);
}
