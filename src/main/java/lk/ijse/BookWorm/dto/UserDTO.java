package lk.ijse.BookWorm.dto;

import lk.ijse.BookWorm.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String password;
    private String mobile;
    private String email;
    private String address;

    public UserDTO (String userId, String userName, String password) {
        this.id=id;
        this.name=userName;
        this.password=password;
    }
//    private LocalDate dob;

    public User toEntity(){
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setPassword(this.password);
        user.setMobile(this.mobile);
        user.setEmail(this.email);
        user.setAddress(this.address);
//        user.setDob(this.dob);
        return user;
    }


}
