package lk.ijse.BookWorm.service.custom;

import lk.ijse.BookWorm.dto.BranchDTO;
import lk.ijse.BookWorm.service.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BranchBO extends SuperBO {
    boolean saveBranches(BranchDTO branchDTO);
    boolean updatedBranches(BranchDTO branchDTO);
    boolean deleteBranches(BranchDTO branchDTO);
    ArrayList<BranchDTO> getAllBranches() throws SQLException;
}
