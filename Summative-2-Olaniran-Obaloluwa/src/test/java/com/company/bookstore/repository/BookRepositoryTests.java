package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepositoryTests {

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    PublisherRepository publisherRepo;

    @Before
    public void setUp() {
        publisherRepo.deleteAll();
        bookRepo.deleteAll();
        authorRepo.deleteAll();
    }

    @Test
    public void shouldAddAndGetBook() {

        //ARRANGE
        Author author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setCity("Los Angeles");
        author.setState("CA");
        author.setPostalCode("11100");
        author.setPhone("111-222-3333");
        author.setEmail("sking@gmail.com");
        author.setBooks(new HashSet<Book>());

        author = authorRepo.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");
        publisher.setBooks(new HashSet<Book>());

        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setPublishDate(LocalDate.of(2018, 8, 01));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("29.99"));

        book = bookRepo.save(book);

        //ACT
        Optional<Book> book1 = bookRepo.findById(book.getBookId());

        //Assert...
        assertEquals(book1.get(), book);


    }
    @Test
    public void shouldGetBookByAuthorId() {

        //ARRANGE
        Author author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setCity("Los Angeles");
        author.setState("CA");
        author.setPostalCode("11100");
        author.setPhone("111-222-3333");
        author.setEmail("sking@gmail.com");
        author.setBooks(new HashSet<Book>());

        author = authorRepo.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");

        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setPublishDate(LocalDate.of(2018, 8, 01));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("29.99"));

        book = bookRepo.save(book);

        //ACT
        Optional<Book> book1 = bookRepo.findById(book.getBookId());

        assertEquals(book1.get(), book);

    }
    @Test
    public void shouldGetAllBooks() {

        Publisher publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");

        publisher = publisherRepo.save(publisher);

        //ARRANGE
        Author author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setCity("Los Angeles");
        author.setState("CA");
        author.setPostalCode("11100");
        author.setPhone("111-222-3333");
        author.setEmail("sking@gmail.com");
        author.setBooks(new HashSet<Book>());

        author = authorRepo.save(author);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setPublishDate(LocalDate.of(2018, 8, 01));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("29.99"));

        book = bookRepo.save(book);

        Book book2 = new Book();
        book2.setIsbn("9783161484569");
        book2.setPublishDate(LocalDate.of(2007, 7, 02));
        book2.setAuthorId(author.getAuthorId());
        book2.setTitle("Atomic Habits");
        book2.setPublisherId(publisher.getPublisherId());
        book2.setPrice(new BigDecimal("9.99"));

        book2 = bookRepo.save(book2);

        List<Book> bList = bookRepo.findAll();

        //Assert...
        assertEquals(2, bList.size());

    }

    @Test
    public void shouldUpdateBook() {
        //ARRANGE
        Author author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setCity("Los Angeles");
        author.setState("CA");
        author.setPostalCode("11100");
        author.setPhone("111-222-3333");
        author.setEmail("sking@gmail.com");
        author.setBooks(new HashSet<Book>());

        author = authorRepo.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");

        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setPublishDate(LocalDate.of(2018, 8, 01));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("29.99"));

        book = bookRepo.save(book);

        book.setTitle("This is a test book");
        book = bookRepo.save(book);

        //Assert.
        Optional<Book> book1 = bookRepo.findById(book.getBookId());

        assertEquals(book1.get(), book);
    }

    @Test
    public void shouldDeleteBooks() {

        //ARRANGE
        Author author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setCity("Los Angeles");
        author.setState("CA");
        author.setPostalCode("11100");
        author.setPhone("111-222-3333");
        author.setEmail("sking@gmail.com");
        author.setBooks(new HashSet<Book>());

        author = authorRepo.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");
        publisher.setBooks(new HashSet<Book>());

        publisher = publisherRepo.save(publisher);

        Book book = new Book();
        book.setIsbn("9783161484100");
        book.setPublishDate(LocalDate.of(2018, 8, 01));
        book.setAuthorId(author.getAuthorId());
        book.setTitle("An interesting book");
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("29.99"));

        book = bookRepo.save(book);

        //ACT
        bookRepo.deleteById(book.getBookId());

        //Assert...
        Optional<Book> book1 = bookRepo.findById(book.getBookId());
        assertFalse(book1.isPresent());

    }

}