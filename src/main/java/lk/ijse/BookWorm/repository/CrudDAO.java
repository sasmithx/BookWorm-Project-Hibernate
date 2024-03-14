package lk.ijse.BookWorm.repository;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO {
    boolean save(T entity) throws SQLException, ClassNotFoundException;
    void delete(T entity) throws SQLException, ClassNotFoundException;
    void update(T entity) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    ArrayList<ID> loadIds() throws SQLException ;

    void setSession(Session session);

}
