package by.stormnet.levkovets.dao;

import by.stormnet.levkovets.domain.impl.ServiceItem;
import by.stormnet.levkovets.domain.impl.ServiceItemPrice;
import by.stormnet.levkovets.domain.impl.Type;

import java.util.List;

public interface ServiceItemPriceDAO {
    void save(ServiceItemPrice serviceItemPrice);

    void update(ServiceItemPrice serviceItemPrice);

    void delete(ServiceItemPrice serviceItemPrice);

    ServiceItemPrice loadById(Integer id);

    List<ServiceItemPrice> loadAll();

    List<ServiceItemPrice> loadAllByItem(ServiceItem item);

    List<ServiceItemPrice> loadAllByType(Type type);
}
