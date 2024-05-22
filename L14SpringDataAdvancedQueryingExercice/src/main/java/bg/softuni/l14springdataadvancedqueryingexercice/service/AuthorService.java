package bg.softuni.l14springdataadvancedqueryingexercice.service;



import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();
}
