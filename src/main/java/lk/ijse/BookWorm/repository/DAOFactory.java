package lk.ijse.BookWorm.repository;

import lk.ijse.BookWorm.repository.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        UserDAO,BranchDAO,BookDAO,TransactionDAO,TransactionDetailDAO
    }

    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case UserDAO:
                return (T) new UserDAOImpl();
            case BranchDAO:
                return (T) new BranchDAOImpl();
            case BookDAO:
                return (T) new BookDAOImpl();
            case TransactionDAO:
                return (T) new TransactionDAOImpl();
            case TransactionDetailDAO:
                return (T) new TransactionDetailDAOImpl();
        }
        return null;
    }
}
