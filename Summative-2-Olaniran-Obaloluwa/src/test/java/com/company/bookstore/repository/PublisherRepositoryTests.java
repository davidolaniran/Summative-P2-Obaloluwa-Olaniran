package com.company.bookstore.repository;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherRepositoryTests {

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
    public void shouldAddAndGetPublisher() {
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

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getPublisherId());

        assertEquals(publisher1.get(), publisher);
    }

    @Test
    public void shouldGetAllPublishers() {

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

        Publisher publisher2 = new Publisher();
        publisher2.setName("Pearson");
        publisher2.setStreet("Hudson");
        publisher2.setCity("New York City");
        publisher2.setState("NY");
        publisher2.setPostalCode("10013");
        publisher2.setPhone("345-768-9846");
        publisher2.setEmail("pHudson@gmail.com");
        publisher2.setBooks(new HashSet<Book>());

        publisher = publisherRepo.save(publisher2);

        List<Publisher> pList = publisherRepo.findAll();

        assertEquals(pList.size(), 2);

    }

    @Test
    public void shouldUpdatePublisher() {
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

        Publisher publisher2 = new Publisher();
        publisher2.setName("Pearson");
        publisher2.setStreet("Hudson");
        publisher2.setCity("New York City");
        publisher2.setState("NY");
        publisher2.setPostalCode("10013");
        publisher2.setPhone("345-768-9846");
        publisher2.setEmail("pHudson@gmail.com");
        publisher2.setBooks(new HashSet<Book>());

        publisher = publisherRepo.save(publisher2);

        // ACT
        Optional<Publisher> customer1 = publisherRepo.findById(publisher.getPublisherId());

        //Assert...
        assertEquals(customer1.get(), publisher);
    }

    @Test
    public void shouldDeletePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");

        publisher = publisherRepo.save(publisher);

        //ACT
        publisherRepo.deleteById(publisher.getPublisherId());

        //Assert...
        Optional<Publisher> publisher1 = publisherRepo.findById(publisher.getPublisherId());
        assertFalse(publisher1.isPresent());

    }

}