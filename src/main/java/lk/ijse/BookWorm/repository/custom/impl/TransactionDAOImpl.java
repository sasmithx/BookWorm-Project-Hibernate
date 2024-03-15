package lk.ijse.BookWorm.repository.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.repository.custom.TransactionDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    private Session session;

    @Override
    public boolean save(Transaction entity) throws SQLException, ClassNotFoundException {
        int save = (int) session.save(entity);
        return save>0;
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
        this.session = session;
    }

//    @Override
//    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
////        String hql = "SELECT t.id FROM Transaction t ORDER BY t.id DESC";
//        Session session1 = SessionFactoryConfig.getSessionFactoryConfig().getSession();
//
//        String hql = "FROM Transaction";
//        Query<Transaction> query = session1.createQuery(hql);
////        query.setMaxResults(1);
//        List<Transaction> transactions = query.list();
//        List<String> list = new ArrayList<>();
//
//        for(Transaction transaction : transactions){
//           list.add(String.valueOf(transaction.getId()));
//        }
//        session1.close();
//
//        if(!transactions.isEmpty()){
//          String currentOrderId = list.get(list.size()-1);
//            System.out.println("current"+currentOrderId);
////            String currentOrderId = result.get(0);
////            return splitOrderId(currentOrderId);
//        }
////        return splitOrderId(null);
//    }

    private String splitOrderId(String currentOrderId) {    //O008
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "O00" + id;
        }
        return "O001";
    }

    @Override
    public ObservableList<String> loadOrderID() throws SQLException, ClassNotFoundException {
        ObservableList<String> orderIds = FXCollections.observableArrayList();

        try{
            String hql = "SELECT t.id FROM Transaction t";
            Query<String> query = session.createQuery(hql, String.class);
            List<String> result = query.list();

            orderIds.addAll(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return orderIds;
    }


}
