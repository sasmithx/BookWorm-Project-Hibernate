package lk.ijse.BookWorm.service.custom;

import lk.ijse.BookWorm.service.SuperBO;

public interface AdminDashBO extends SuperBO {
    int bookCount();
    int userCount();
}
