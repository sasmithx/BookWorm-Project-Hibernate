package lk.ijse.BookWorm.repository.custom.impl;

import lk.ijse.BookWorm.entity.Branch;
import lk.ijse.BookWorm.repository.custom.BranchDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {

    private Session session;

    @Override
    public void save(Branch entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
    }

    @Override
    public void delete(Branch entity) throws SQLException, ClassNotFoundException {
        session.delete(entity);
    }

    @Override
    public void update(Branch entity) throws SQLException, ClassNotFoundException {
        session.update(entity);
    }

    @Override
    public ArrayList<Branch> getAll() throws SQLException, ClassNotFoundException {
        try{
            List<Branch> branches = session.createNativeQuery("SELECT * FROM Branch", Branch.class).getResultList();
            return (ArrayList<Branch>) branches;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        String sql = "SELECT B.id FROM Branch AS B";
        Query query = session.createQuery(sql);
        List list = query.list();
        return (ArrayList<String>) list;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public Branch getBranchById(String id) {
        String sql = " SELECT B FROM Branch AS B WHERE B.id = :branch_id";
        Query namedquery = session.createQuery(sql);
        namedquery.setParameter("branch_id",id);
        Branch branch = (Branch) namedquery.getSingleResult();
        session.close();
        return branch;
    }

    @Override
    public String generateNextId() {
        String sql = "SELECT B.id FROM Branch AS B ORDER BY B.id desc";
        Query namedquery = session.createQuery(sql);
        String branchId = (String) namedquery.setMaxResults(1).uniqueResult();
        return branchId;
    }
}
