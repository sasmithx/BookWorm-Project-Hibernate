package lk.ijse.BookWorm.service;

import lk.ijse.BookWorm.service.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        UserBO,AdminBO,UserBookBO,BranchBO,AdminDashboardBO,UserDashboardBO
    }

    public <T extends SuperBO>T getBO(BOFactory.BOTypes boTypes){
        switch (boTypes){
            case UserBO:
                return (T) new UserBOImpl();
            case AdminBO:
                return (T) new AdminUserBOImpl();
            case UserBookBO:
                return (T) new UserBookBOImpl();
            case BranchBO:
                return (T) new BranchBOImpl();
            case AdminDashboardBO:
                return (T) new AdminDashboardBOImpl();
            case UserDashboardBO:
                return (T) new UserDashboardBOImpl();
        }
        return null;
    }
}
