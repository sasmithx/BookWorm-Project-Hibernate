package lk.ijse.BookWorm.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartTm {
    private String bookID;
    private String title;
    private int qty;
    private int amount;
    private Button removebtn;
}
