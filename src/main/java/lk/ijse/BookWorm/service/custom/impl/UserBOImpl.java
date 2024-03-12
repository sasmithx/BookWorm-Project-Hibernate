package lk.ijse.BookWorm.service.custom.impl;

import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.DAOFactory;
import lk.ijse.BookWorm.repository.custom.UserDAO;
import lk.ijse.BookWorm.service.custom.UserBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UserDAO);

    @Override
    public boolean saveUsers(UserDTO userDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

            try{
                userDAO.setSession(session);
                userDAO.save(userDTO.toEntity());
                transaction.commit();
                session.close();
                return true;
            } catch (Exception e){
                transaction.rollback();
                session.close();
                e.printStackTrace();
                return false;
            }
    }

    @Override
    public boolean updateUsers(UserDTO userDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            userDAO.setSession(session);
            userDAO.update(userDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUsers(UserDTO userDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            userDAO.setSession(session);
            userDAO.delete(userDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        userDAO.setSession(session);
        ArrayList<User> users = null;
        try {
            users = userDAO.getAll();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users){
            userDTOS.add(new UserDTO(
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    user.getMobile(),
                    user.getEmail(),
                    user.getAddress()
                    /*user.getDob()*/
            ));
        }
        session.close();
        return userDTOS;
    }
}
