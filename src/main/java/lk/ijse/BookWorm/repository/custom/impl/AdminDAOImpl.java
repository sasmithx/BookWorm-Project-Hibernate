package lk.ijse.BookWorm.repository.custom.impl;

import lk.ijse.BookWorm.entity.Admin;
import lk.ijse.BookWorm.repository.custom.AdminDAO;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    private Session session;
    @Override
    public boolean save(Admin entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
        return false;
    }

    @Override
    public void delete(Admin entity) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(Admin entity) throws SQLException, ClassNotFoundException {
        session.update(entity);
    }

    @Override
    public ArrayList<Admin> getAll() throws SQLException, ClassNotFoundException {
        try{
            List<Admin> admins = session.createNativeQuery("SELECT * FROM Admin", Admin.class).getResultList();
            return (ArrayList<Admin>) admins;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        return null;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public Admin getAdminById(int id) {
        return null;
    }

    @Override
    public String generateNextId() {
        return null;
    }
}
