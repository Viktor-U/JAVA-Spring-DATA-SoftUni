package bg.softuni.l14springdataadvancedqueryingexercice.repository;


import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.AgeRestriction;
import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.Book;
import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, long copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal a, BigDecimal b);

    @Query( "FROM Book b " +
            "WHERE YEAR(b.releaseDate) != :year ")
    List<Book> findAllByReleaseDateNotIn(int year);

}
