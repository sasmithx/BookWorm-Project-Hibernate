package lk.ijse.BookWorm.dto;

import lk.ijse.BookWorm.entity.Book;

public class BookDTO {
    private String id;
    private String bookName;
    private String authorName;
    private String genre;
    private int qty;


    public Book toEntity(){
        Book book = new Book();
        book.setId(this.id);
        book.setBookName(this.bookName);
        book.setAuthorName(this.authorName);
        book.setGenre(this.genre);
        book.setQty(this.qty);
        return book;
    }
}
