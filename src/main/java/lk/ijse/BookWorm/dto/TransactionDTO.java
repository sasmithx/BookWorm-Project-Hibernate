package lk.ijse.BookWorm.dto;

import lk.ijse.BookWorm.entity.Transaction;
import lk.ijse.BookWorm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private int id;
    private LocalDate orderDate;
    private String userName;
    private int qty;
    private int total;
    private String dueDate;
    private String transactionType;
    private User user;

    public Transaction toEntity(){
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setOrderDate(orderDate);
        transaction.setUserName(userName);
        transaction.setQty(qty);
        transaction.setTotal(total);
        transaction.setDueDate(dueDate);
        transaction.setTransactionType(transactionType);
        transaction.setUser(user);
        return transaction;
    }
}
