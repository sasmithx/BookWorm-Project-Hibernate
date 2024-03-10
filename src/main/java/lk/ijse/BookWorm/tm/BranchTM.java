package lk.ijse.BookWorm.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchTM {
    private String id;
    private String name;
    private String location;
    private String mobile;
    private String email;
}
