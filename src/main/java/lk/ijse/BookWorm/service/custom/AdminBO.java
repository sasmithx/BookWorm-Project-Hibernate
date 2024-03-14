package lk.ijse.BookWorm.service.custom;

import lk.ijse.BookWorm.dto.AdminDTO;
import lk.ijse.BookWorm.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminBO extends SuperBO {
    boolean saveAdmin();
    boolean updateAdmin(AdminDTO adminDTO);
    AdminDTO searchAdmin(String id);
    ArrayList<String> loadAdminIds()  throws SQLException;
    String generateNextAdminId();
}
