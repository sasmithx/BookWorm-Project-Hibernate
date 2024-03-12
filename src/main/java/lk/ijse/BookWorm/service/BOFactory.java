package lk.ijse.BookWorm.service;

import lk.ijse.BookWorm.service.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        UserBO,BranchBO,BookBO,AdminDashBO
    }

    public <T extends SuperBO>T getBO(BOFactory.BOTypes boTypes){
        switch (boTypes){
            case UserBO:
                return (T) new UserBOImpl();
            case BranchBO:
                return (T) new BranchBOImpl();
            case BookBO:
                return (T) new BookBOImpl();
            case AdminDashBO:
                return (T) new AdminDashBOImpl();
        }
        return null;
    }
}
