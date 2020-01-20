package by.stormnet.levkovets.dao.factory;

import by.stormnet.levkovets.dao.*;

public abstract class DAOFactory {
    public abstract DiameterDAO getDiameterDAO();
    public abstract HeightDAO getHeightDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract OrderServiceItemPriceDAO getOrderServiceItemPriceDAO();
    public abstract ServiceItemDAO getServiceItemDAO();
    public abstract ServiceItemPriceDAO getServiceItemPriceDAO();
    public abstract TireDAO getTireDAO();
    public abstract TypeDAO getTypeDAO();
    public abstract UserDAO getUserDAO();
    public abstract WidthDAO getWidthDAO();

    public static DAOFactory getFactory() {
        return getFactory(StorageTypes.MySql);
    }

    public static DAOFactory getFactory(StorageTypes storageTypes){
        switch (storageTypes) {
            case MySql:{
                return new MySqlDAOFactory();
            }
        }
        throw new RuntimeException("Unsupported storage type exception");
    }
}
