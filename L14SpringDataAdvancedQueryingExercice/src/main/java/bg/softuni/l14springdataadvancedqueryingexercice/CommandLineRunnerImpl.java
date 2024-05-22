package bg.softuni.l14springdataadvancedqueryingexercice;


import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.AgeRestriction;
import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.Book;
import bg.softuni.l14springdataadvancedqueryingexercice.model.entity.EditionType;
import bg.softuni.l14springdataadvancedqueryingexercice.service.AuthorService;
import bg.softuni.l14springdataadvancedqueryingexercice.service.BookService;
import bg.softuni.l14springdataadvancedqueryingexercice.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //seedData();

//        printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//        printAllAuthorsAndNumberOfTheirBooks();
//        printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        //Ex1
//        printAllByAgeRestriction();
        //Ex2
//        printAllBooksWithTitlesLessThan5000Copies();
        //Ex3
//        printAllBooksWithPriceNotBetween();
        //Ex4
//        printTitlesOfBooksWhichNotReleasedIn(READER.readLine());
        //Ex5
        printBooksReleasedBefore(READER.readLine());





    }

    private void printBooksReleasedBefore(String s) {

        Integer[] date = Arrays.stream(s.split("-")).map(Integer::valueOf).toArray(Integer[]::new);
        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(date);
    }

    private void printTitlesOfBooksWhichNotReleasedIn(String year) {
        bookService.findAllByReleaseDateNotIn(Integer.parseInt(year))
                .stream()
                .limit(3)
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void printAllBooksWithPriceNotBetween() {
        BigDecimal a = new BigDecimal("5");
        BigDecimal b = new BigDecimal("40");

        bookService.findAllBooksWithPriceNotBetween(a, b)
                .stream()
                .map(book -> book.getTitle() + "- $" + book.getPrice())
                .forEach(System.out::println);
    }

    private void findAllBooksWithTitlesLessThan5000Copies() {
        EditionType editionType = EditionType.valueOf("GOLD");
        long copies  = 5000;
        bookService.findAllBooksWithTitlesLessThan5000Copies(editionType, copies)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void findByAgeRestriction() throws IOException {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(READER.readLine().toUpperCase());
        bookService.findByAgeRestriction(ageRestriction)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(Integer[] year) {
        bookService
                .findAllBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
