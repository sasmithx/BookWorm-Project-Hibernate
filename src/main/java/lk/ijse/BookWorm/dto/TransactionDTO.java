package lk.ijse.BookWorm.dto;

import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.entity.User;
import lk.ijse.BookWorm.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
/*@AllArgsConstructor*/
@NoArgsConstructor
public class TransactionDTO {
    private int id;
    private LocalDate orderDate;
    private String userName;
    private int qty;
    private String dueDate;
    private String transactionType;
    private User user;
    private List<CartTm> tmList = new ArrayList<>();

    public TransactionDTO(int transactionId, LocalDate orderDate, String userName, int qty, List<CartTm> tmList) {
        id=transactionId;
        this.orderDate=orderDate;
        this.userName=userName;
        this.qty=qty;
        this.tmList=tmList;
    }



    public Transaction toEntity(){
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setOrderDate(orderDate);
        transaction.setUserName(userName);
        transaction.setQty(qty);
        transaction.setDueDate(dueDate);
        transaction.setTransactionType(transactionType);
        transaction.setUser(user);
        return transaction;
    }
}
