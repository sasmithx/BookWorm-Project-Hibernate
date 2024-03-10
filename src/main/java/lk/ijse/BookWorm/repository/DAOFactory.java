package lk.ijse.BookWorm.repository;

import lk.ijse.BookWorm.repository.custom.impl.AdminDAOImpl;
import lk.ijse.BookWorm.repository.custom.impl.UserDAOImpl;
import lk.ijse.BookWorm.service.custom.impl.AdminDashboardBOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        UserDAO,AdminDAO
    }

    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case UserDAO:
                return (T) new UserDAOImpl();
            case AdminDAO:
                return (T) new AdminDAOImpl();
        }
        return null;
    }
}
