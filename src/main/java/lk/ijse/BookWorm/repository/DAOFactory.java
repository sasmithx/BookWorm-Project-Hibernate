package lk.ijse.BookWorm.repository;

import lk.ijse.BookWorm.repository.custom.impl.BookDAOImpl;
import lk.ijse.BookWorm.repository.custom.impl.BranchDAOImpl;
import lk.ijse.BookWorm.repository.custom.impl.TransactionRepositoryImpl;
import lk.ijse.BookWorm.repository.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        UserDAO,BranchDAO,BookDAO,TransactionRepository
    }

    public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case UserDAO:
                return (T) new UserDAOImpl();
            case BranchDAO:
                return (T) new BranchDAOImpl();
            case BookDAO:
                return (T) new BookDAOImpl();
            case TransactionRepository:
                return (T) new TransactionRepositoryImpl();
        }
        return null;
    }
}
