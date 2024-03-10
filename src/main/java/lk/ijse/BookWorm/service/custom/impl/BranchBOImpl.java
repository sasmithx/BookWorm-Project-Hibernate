package lk.ijse.BookWorm.service.custom.impl;

import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.dto.BranchDTO;
import lk.ijse.BookWorm.repository.DAOFactory;
import lk.ijse.BookWorm.repository.custom.BranchDAO;
import lk.ijse.BookWorm.service.custom.BranchBO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class BranchBOImpl implements BranchBO {

    BranchDAO branchDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BranchDAO);

    @Override
    public boolean saveBranches(BranchDTO branchDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            branchDAO.setSession(session);
            branchDAO.save(branchDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatedBranches(BranchDTO branchDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            branchDAO.setSession(session);
            branchDAO.update(branchDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteBranches(BranchDTO branchDTO) {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            branchDAO.setSession(session);
            branchDAO.delete(branchDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<BranchDTO> getAllBranches() throws SQLException {

    }
}
