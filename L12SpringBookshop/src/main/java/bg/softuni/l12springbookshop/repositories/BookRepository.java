package bg.softuni.l12springbookshop.repositories;

import bg.softuni.l12springbookshop.domein.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
