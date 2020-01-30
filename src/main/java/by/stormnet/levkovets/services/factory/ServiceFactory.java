package by.stormnet.levkovets.services.factory;

import by.stormnet.levkovets.services.*;
import by.stormnet.levkovets.services.OrderStatisticService;

public abstract class ServiceFactory {

    public abstract DiameterService getDiameterService();

    public abstract HeightService getHeightService();

    public abstract OrderService getOrderService();

    public abstract OrderStatisticService getOrderStatisticService();

    public abstract OrderServiceItemPriceService getOrderServiceItemPriceService();

    public abstract ServiceItemPriceService getServiceItemPriceService();

    public abstract ServiceItemService getServiceItemService();

    public abstract TireService getTireService();

    public abstract TypeService getTypeService();

    public abstract UserService getUserService();

    public abstract WidthService getWidthService();

    public static ServiceFactory getFactory() {
        return new ServiceFactoryImpl();

    }
}
