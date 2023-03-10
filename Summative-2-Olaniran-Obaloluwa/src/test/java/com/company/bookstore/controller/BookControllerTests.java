package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Book;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookRepository bookRepository;

    private Book testBook;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        Author author = new Author();
        author.setAuthorId(1);
        author.setFirstName("Test");
        author.setLastName("Author");

        Publisher publisher = new Publisher();
        publisher.setPublisherId(1);
        publisher.setName("Test Publisher");

        testBook = new Book();
        testBook.setBookId(1);
        testBook.setIsbn("9783161484100");
        testBook.setPublishDate(LocalDate.parse("2021-01-01"));
        testBook.setAuthorId(1);
        testBook.setTitle("Test Title");
        testBook.setPublisherId(1);
        testBook.setPrice(BigDecimal.valueOf(20));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(testBook);

        when(bookRepository.findAll()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBookById() throws Exception {
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(testBook));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/1"))
                .andExpect(status().isOk());
    }
    @Test
    public void testSearchBookByAuthorId() throws Exception {
    when(bookRepository.findByAuthorId(anyInt())).thenReturn(Optional.of(testBook));

        mockMvc.perform(MockMvcRequestBuilders.get("/book/author/1"))
                .andExpect(status().isOk());


    }

    @Test
    public void testAddBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Title\",\"author\":\"Test Author\",\"price\":19.99}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateBook() throws Exception {
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(testBook));

        mockMvc.perform(MockMvcRequestBuilders.put("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testDeleteBook() throws Exception {
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(testBook));

        mockMvc.perform(MockMvcRequestBuilders.delete("/book/1"))
                .andExpect(status().isNoContent());
    }
}