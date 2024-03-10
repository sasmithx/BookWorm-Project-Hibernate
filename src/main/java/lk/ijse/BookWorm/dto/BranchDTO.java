package lk.ijse.BookWorm.dto;

import lk.ijse.BookWorm.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
    private String id;
    private String name;
    private String location;
    private String mobile;
    private String email;


    public Branch toEntity(){
        Branch branch = new Branch();
        branch.setId(this.id);
        branch.setName(this.name);
        branch.setLocation(this.location);
        branch.setMobile(this.mobile);
        branch.setEmail(this.email);
        return branch;
    }

}
