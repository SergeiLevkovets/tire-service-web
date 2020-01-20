package by.stormnet.levkovets.dao.factory;

import by.stormnet.levkovets.dao.*;
import by.stormnet.levkovets.dao.mysql.*;

public class MySqlDAOFactory extends DAOFactory {

    MySqlDAOFactory() {
    }

    @Override
    public DiameterDAO getDiameterDAO() {
        return new DiameterDAOImpl();
    }

    @Override
    public HeightDAO getHeightDAO() {
        return new HeightDAOImpl();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OrderDAOImpl();
    }

    @Override
    public OrderServiceItemPriceDAO getOrderServiceItemPriceDAO() {
        return new OrderServiceItemPriceDAOImpl();
    }

    @Override
    public ServiceItemDAO getServiceItemDAO() {
        return new ServiceItemDAOImpl();
    }

    @Override
    public ServiceItemPriceDAO getServiceItemPriceDAO() {
        return new ServiceItemPriceDAOImpl();
    }

    @Override
    public TireDAO getTireDAO() {
        return new TireDAOImpl();
    }

    @Override
    public TypeDAO getTypeDAO() {
        return new TypeDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public WidthDAO getWidthDAO() {
        return new WidthDAOImpl();
    }
}
