package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorRepositoryTests {

    @Autowired
    AuthorRepository authorRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    PublisherRepository publisherRepo;

    @Before
    public void setUp() throws Exception {
        authorRepo.deleteAll();
        bookRepo.deleteAll();
        publisherRepo.deleteAll();
    }

    @Test
    public void shouldAddAndGetAuthor() {

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

        //Assert...
        Optional<Author> author1 = authorRepo.findById(author.getAuthorId());

        assertEquals(author1.get(), author);
    }

    @Test
    public void shouldGetAllAuthor() {
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

        author = authorRepo.save(author);

        Author author2 = new Author();
        author2.setFirstName("David");
        author2.setLastName("Smith");
        author2.setStreet("Raymond Ave");
        author2.setCity("San Jose");
        author2.setState("CA");
        author2.setPostalCode("95128");
        author2.setPhone("404-768-9756");
        author2.setEmail("dsmith@gmail.com");

        author = authorRepo.save(author2);

        // ACT
        List<Author> aList = authorRepo.findAll();

        // ASSERT
        assertEquals(aList.size(), 2);

    }

    @Test
    public void shouldUpdateAuthor() {

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

        //ACT
        author.setFirstName("UPDATED");

        author = authorRepo.save(author);

        //Assert...
        Optional<Author> author1 = authorRepo.findById(author.getAuthorId());

        assertEquals(author1.get(), author);

    }
    @Test
    public void shouldDeleteAuthor() {

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

        author = authorRepo.save(author);

        //ACT
        authorRepo.deleteById(author.getAuthorId());

        //Assert...
        Optional<Author> author1 = authorRepo.findById(author.getAuthorId());
        assertFalse(author1.isPresent());

    }

}