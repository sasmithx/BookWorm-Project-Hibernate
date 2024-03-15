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
    public boolean saveOrderDetail(lk.ijse.BookWorm.entity.Transaction transactionId, List<TransactionDetail> tmList) throws SQLException, ClassNotFoundException {
        for (TransactionDetail cartTm : tmList) {
            if (!saveOrderDetails(transactionId, cartTm)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetails(lk.ijse.BookWorm.entity.Transaction transactionId, TransactionDetail cartTm) throws SQLException, ClassNotFoundException {
//        Transaction transaction = session.beginTransaction();

        try {
            TransactionDetail transactionDetail = new TransactionDetail();
            /*transactionDetail.setTransaction(transactionId);
            transactionDetail.(cartTm.getBookID());
            transactionDetail.(cartTm.getTitle());
            transactionDetail.(cartTm.getQty());
            transactionDetail.(cartTm.getAmount());*/

            /*transactionDetail.setBook(
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
                     )*/
//            );
            cartTm.setTransaction(transactionId);

            session.save(cartTm);




            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }


    }
    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
