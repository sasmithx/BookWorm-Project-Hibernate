package lk.ijse.BookWorm.repository.custom;

import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.CrudDAO;

public interface UserDAO extends CrudDAO<User,String> {
    User getUserById(String id);
    String generateNextId();
}
