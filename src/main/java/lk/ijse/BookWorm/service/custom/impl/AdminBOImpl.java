package lk.ijse.BookWorm.service.custom.impl;

import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.dto.AdminDTO;
import lk.ijse.BookWorm.service.custom.AdminBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminBOImpl implements AdminBO {

    @Override
    public boolean saveAdmin() {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        try {

        }catch (Exception e){

        }
    }

    @Override
    public boolean updateAdmin(AdminDTO adminDTO) {
        return false;
    }

    @Override
    public AdminDTO searchAdmin(String id) {
        return null;
    }

    @Override
    public ArrayList<String> loadAdminIds() throws SQLException {
        return null;
    }

    @Override
    public String generateNextAdminId() {
        return null;
    }
}
