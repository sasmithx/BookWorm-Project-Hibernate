package lk.ijse.BookWorm.repository.custom.impl;

import lk.ijse.BookWorm.entity.Book;
import lk.ijse.BookWorm.entity.TransactionDetail;
import lk.ijse.BookWorm.repository.custom.TransactionDetailDAO;
import lk.ijse.BookWorm.tm.CartTm;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class TransactionDetailDAOImpl implements TransactionDetailDAO {
    private Session session;
    @Override
    public boolean saveOrderDetail(String transactionId, List<CartTm> tmList) throws SQLException, ClassNotFoundException {
        for (CartTm cartTm : tmList) {
            if (!saveOrderDetails(transactionId, cartTm)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetails(String transactionId, CartTm cartTm) throws SQLException, ClassNotFoundException {
        Transaction transaction = session.beginTransaction();

        try {
            transaction = session.beginTransaction();


            TransactionDetail transactionDetail = new TransactionDetail();
            /*transactionDetail.setTransaction(transactionId);
            transactionDetail.(cartTm.getBookID());
            transactionDetail.(cartTm.getTitle());
            transactionDetail.(cartTm.getQty());
            transactionDetail.(cartTm.getAmount());*/

            transactionDetail.setBook(
                    new Book(cartTm.getBookID(),
                            cartTm.getTitle(),
                            cartTm.getQty()
                            )
                    );

            transactionDetail.setTransaction(
                     new lk.ijse.BookWorm.entity.Transaction(
                             cartTm.getBookID(),
                             cartTm.getTitle(),
                             cartTm.getQty()
                     )
            );

            session.save(transactionDetail);



            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }


    }
}
