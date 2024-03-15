package lk.ijse.BookWorm.service.custom;

import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    boolean saveUsers(UserDTO userDTO);
    boolean updateUsers(UserDTO userDTO);
    boolean deleteUsers(UserDTO userDTO);
    UserDTO searchUsers(String id);
    ArrayList<UserDTO> getAllUsers() throws SQLException;
    String verifyCredentials(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> loadUserIds()  throws SQLException;

}
