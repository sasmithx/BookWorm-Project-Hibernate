package lk.ijse.BookWorm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTM {
    private String id;
    private String name;
    private String password;
    private String mobile;
    private String email;
    private String address;
    /*private LocalDate dob;*/
}
