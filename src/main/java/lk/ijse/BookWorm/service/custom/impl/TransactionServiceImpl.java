package lk.ijse.BookWorm.service.custom.impl;

import lk.ijse.BookWorm.config.SessionFactoryConfig;
import lk.ijse.BookWorm.dto.TransactionDTO;
import lk.ijse.BookWorm.repository.DAOFactory;
import lk.ijse.BookWorm.repository.custom.BookDAO;
import lk.ijse.BookWorm.repository.custom.TransactionRepository;
import lk.ijse.BookWorm.repository.custom.impl.TransactionRepositoryImpl;
import lk.ijse.BookWorm.service.custom.TransactionService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TransactionRepository);
    BookDAO bookDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BookDAO);
    @Override
    public boolean saveTransaction(TransactionDTO transactionDTO) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getSessionFactoryConfig().getSession();
        Transaction transaction = session.beginTransaction();

        transactionRepository.setSession(session);
        transactionRepository.save(transactionDTO.toEntity());

        bookDAO.setSession(session);
        bookDAO.update();


        try{
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e){
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

}
