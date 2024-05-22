package bg.softuni.l14springdataadvancedqueryingexercice.repository;


import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a JOIN a.books b ORDER BY length(b.title) DESC")
    List<Author> findAllByBooksSizeDESC();
}
