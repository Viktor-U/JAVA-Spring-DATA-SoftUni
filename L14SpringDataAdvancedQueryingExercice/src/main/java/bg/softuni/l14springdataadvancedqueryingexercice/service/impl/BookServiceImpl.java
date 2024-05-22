package bg.softuni.l14springdataadvancedqueryingexercice.service.impl;


import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.*;
import bg.softuni.l14springdataadvancedqueryingexercice.repository.BookRepository;
import bg.softuni.l14springdataadvancedqueryingexercice.service.AuthorService;
import bg.softuni.l14springdataadvancedqueryingexercice.service.BookService;
import bg.softuni.l14springdataadvancedqueryingexercice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Book> findByAgeRestriction(AgeRestriction ageRestriction){
        return bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllBooksWithReleaseDateBeforeYear(Integer[] date) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(date[2], date[1], date[0]))
                .stream()
                .map(book -> String.format("%s %s $%.2f", book.getTitle(),
                        book.getEditionType(), book.getPrice()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public  List<Book> findAllBooksWithTitlesLessThan5000Copies(EditionType editionType, long copies) {

        return bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies);
    }

    @Override
    public List<Book> findAllBooksWithPriceNotBetween(BigDecimal a, BigDecimal b){
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(a, b);
    }
    @Override
    public List<Book> findAllByReleaseDateNotIn(int year) {
        return bookRepository.findAllByReleaseDateNotIn(year);
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
