package bg.softuni.l14springdataadvancedqueryingexercice.service;



import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.AgeRestriction;
import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.Book;
import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllBooksWithReleaseDateBeforeYear(Integer[] date);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllBooksWithTitlesLessThan5000Copies(EditionType editionType, long copies);

    List<Book> findAllBooksWithPriceNotBetween(BigDecimal a, BigDecimal b);

    List<Book> findAllByReleaseDateNotIn(int year);
}
