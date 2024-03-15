package lk.ijse.BookWorm.repository.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.custom.UserDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Session session;

    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
        return false;
    }

    @Override
    public void delete(User entity) throws SQLException, ClassNotFoundException {
        session.delete(entity);
    }

    @Override
    public void update(User entity) throws SQLException, ClassNotFoundException {
        session.update(entity);
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        try {
            List<User> users = session.createNativeQuery("SELECT * FROM User", User.class).getResultList();
            return (ArrayList<User>) users;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException {
        String sql = "SELECT U.id FROM User AS U";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return (ArrayList<String>) list;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public ObservableList<String> loadUserId() throws SQLException, ClassNotFoundException {
        ObservableList<String> userData = FXCollections.observableArrayList();

        String hql = "SELECT u.id FROM User u";
        Query<String> query = session.createQuery(hql, String.class);

        List<String> result = query.list();

        userData.addAll(result);

        return userData;
    }

    @Override
    public User getUserById(String id) {
        String sql = "SELECT U FROM User AS U WHERE U.id = :user_id";
        Query namedquery = session.createQuery(sql);
        namedquery.setParameter("user_id",id);
        User user = (User)namedquery.getSingleResult();
        session.close();
        return user;
    }

    @Override
    public String generateNextId() {
        String sql = "SELECT U.id FROM User AS U ORDER BY  U.id desc ";
        Query namedquery = session.createQuery(sql);
        String userId = (String) namedquery.setMaxResults(1).uniqueResult();
        return userId;
    }

    @Override
    public int userCount() {
        String sql = "SELECT COUNT(U.id) FROM User AS U";
        Query query = session.createQuery(sql);
        Long count = (Long) query.getSingleResult();
        return Math.toIntExact(count);
    }

    @Override
    public User search(String newValue) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();

        Query<User> query = session.createQuery("FROM User WHERE id = :id", User.class);
        query.setParameter("id",newValue);
        List<User> results = query.list();

        if(!results.isEmpty()){
            return results.get(0);
        }
        return null;
    }
}
