package lk.ijse.BookWorm.repository.custom;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.CrudDAO;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User,String> {
    ObservableList<String> loadUserId() throws SQLException, ClassNotFoundException;
    User getUserById(String id);
    String generateNextId();
    int userCount();
}
