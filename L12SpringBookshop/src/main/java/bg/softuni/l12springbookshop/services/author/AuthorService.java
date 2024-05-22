package bg.softuni.l12springbookshop.services.author;

import bg.softuni.l12springbookshop.domein.entities.Author;

import java.util.List;

public interface AuthorService {
    void seedAuthors(List<Author> authors);

    boolean isDataSeeded();
}
