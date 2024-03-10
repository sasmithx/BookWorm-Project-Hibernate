package lk.ijse.BookWorm.repository.custom;

import lk.ijse.BookWorm.entity.Branch;
import lk.ijse.BookWorm.repository.CrudDAO;

public interface BranchDAO extends CrudDAO<Branch,String> {
    Branch getBranchById(String id);
    String generateNextId();
}
