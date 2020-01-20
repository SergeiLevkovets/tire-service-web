package by.stormnet.levkovets.services.factory;

import by.stormnet.levkovets.services.*;
import by.stormnet.levkovets.services.impl.*;

public class ServiceFactoryImpl extends ServiceFactory {
    @Override
    public DiameterService getDiameterService() {
        return new DiameterServiceImpl();
    }

    @Override
    public HeightService getHeightService() {
        return new HeightServiceImpl();
    }

    @Override
    public OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    @Override
    public OrderServiceItemPriceService getOrderServiceItemPriceService() {
        return new OrderServiceItemPriceServiceImpl();
    }

    @Override
    public ServiceItemPriceService getServiceItemPriceService() {
        return new ServiceItemPriceServiceImpl();
    }

    @Override
    public ServiceItemService getServiceItemService() {
        return new ServiceItemServiceImpl();
    }

    @Override
    public TireService getTireService() {
        return new TireServiceImpl();
    }

    @Override
    public TypeService getTypeService() {
        return new TypeServiceImpl();
    }

    @Override
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Override
    public WidthService getWidthService() {
        return new WidthServiceImpl();
    }
}
