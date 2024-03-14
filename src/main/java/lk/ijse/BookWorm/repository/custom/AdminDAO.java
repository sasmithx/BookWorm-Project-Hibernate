package lk.ijse.BookWorm.repository.custom;

import lk.ijse.BookWorm.entity.Admin;
import lk.ijse.BookWorm.repository.CrudDAO;

public interface AdminDAO extends CrudDAO<Admin,String> {
    public Admin getAdminById(int id);
    String generateNextId();
}
