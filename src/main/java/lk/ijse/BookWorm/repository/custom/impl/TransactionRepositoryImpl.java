package lk.ijse.BookWorm.repository.custom.impl;

import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.repository.custom.TransactionRepository;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionRepositoryImpl implements TransactionRepository {

    private Session session;
    @Override
    public void save(Transaction entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
    }

    @Override
    public void delete(Transaction entity) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(Transaction entity) throws SQLException, ClassNotFoundException {

    }

    @Override
    public ArrayList<Transaction> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        return null;
    }

    @Override
    public void setSession(Session session) {

    }
}
