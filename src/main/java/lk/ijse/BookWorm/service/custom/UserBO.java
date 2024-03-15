package lk.ijse.BookWorm.service.custom;

import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    boolean saveUsers(UserDTO userDTO);
    boolean updateUsers(UserDTO userDTO);
    boolean deleteUsers(UserDTO userDTO);
    UserDTO searchUsers(String id);
    ArrayList<UserDTO> getAllUsers() throws SQLException;
    /*boolean verifyCredentials(String username,String password) throws SQLException, ClassNotFoundException;*/

}
