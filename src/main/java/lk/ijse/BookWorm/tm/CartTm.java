package lk.ijse.BookWorm.tm;

import com.jfoenix.controls.JFXButton;
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

    public CartTm(String code, String description, int qty, JFXButton btnremove) {
        bookID=code;
        title=description;
        this.qty=qty;
        removebtn=btnremove;
    }
}
