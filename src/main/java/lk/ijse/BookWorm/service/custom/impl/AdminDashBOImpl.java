package lk.ijse.BookWorm.service.custom.impl;

import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.repository.DAOFactory;
import lk.ijse.BookWorm.repository.custom.BookDAO;
import lk.ijse.BookWorm.repository.custom.UserDAO;
import lk.ijse.BookWorm.service.custom.AdminDashBO;
import org.hibernate.Session;

public class AdminDashBOImpl implements AdminDashBO {

    BookDAO bookDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BookDAO);
    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UserDAO);

    @Override
    public int bookCount() {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();

        try{
            bookDAO.setSession(session);
            int count = bookDAO.bookCount();

            session.close();
            return count;
        }catch (Exception e){
            session.close();
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int userCount() {
        return 0;
    }
}
