package lk.ijse.BookWorm.repository;

import lk.ijse.BookWorm.repository.custom.impl.AdminDAOImpl;
import lk.ijse.BookWorm.repository.custom.impl.BranchDAOImpl;
import lk.ijse.BookWorm.repository.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        UserDAO,BranchDAO
    }

    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case UserDAO:
                return (T) new UserDAOImpl();
            case BranchDAO:
                return (T) new BranchDAOImpl();
        }
        return null;
    }
}
