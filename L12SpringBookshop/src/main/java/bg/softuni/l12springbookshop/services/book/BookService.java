package bg.softuni.l12springbookshop.services.book;

import bg.softuni.l12springbookshop.domein.entities.Book;

import java.util.List;

public interface BookService {

    void seedBooks(List<Book> books);
}
