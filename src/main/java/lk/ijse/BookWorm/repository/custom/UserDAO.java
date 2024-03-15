package lk.ijse.BookWorm.repository.custom;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.CrudDAO;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {
    List<String> loadUserId() throws SQLException, ClassNotFoundException;
    User getUserById(String id);
    String generateNextId();
    int userCount();

    User search(String newValue) throws SQLException, ClassNotFoundException;

    User getUserByUserName (String userName);

//    boolean verifyCredentials(String username,String password) throws SQLException, ClassNotFoundException;
}
