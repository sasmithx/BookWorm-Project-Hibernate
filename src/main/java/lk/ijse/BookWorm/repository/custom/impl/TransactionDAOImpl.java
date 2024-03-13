package lk.ijse.BookWorm.repository.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.repository.custom.TransactionDAO;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDAOImpl implements TransactionDAO {
    private Session session;

    @Override
    public void save(Transaction entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
    }

    @Override
    public void delete(Transaction entity) throws SQLException, ClassNotFoundException {
        session.delete(entity);
    }

    @Override
    public void update(Transaction entity) throws SQLException, ClassNotFoundException {
        session.update(entity);
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

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<String> loadOrderID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
