package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorRepository authorRepo;

    private Author testAuthor;
    private List<Author> testAuthorList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        testAuthor = new Author();
        testAuthor.setAuthorId(1);
        testAuthor.setFirstName("TestFirstName");
        testAuthor.setLastName("TestLastName");
        testAuthor.setStreet("TestStreet");
        testAuthor.setCity("TestCity");
        testAuthor.setState("TestState");
        testAuthor.setPostalCode("TestPostalCode");
        testAuthor.setPhone("TestPhone");
        testAuthor.setEmail("TestEmail");

        testAuthorList = Arrays.asList(testAuthor);
    }

    @Test
    public void testAddAuthor() throws Exception {
        when(authorRepo.save(any(Author.class))).thenReturn(testAuthor);

        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"TestFirstName\",\"lastName\":\"TestLastName\",\"street\":\"TestStreet\",\"city\":\"TestCity\",\"state\":\"TestState\",\"postalCode\":\"TestPostalCode\",\"phone\":\"TestPhone\",\"email\":\"TestEmail\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("TestFirstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("TestLastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("TestStreet"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("TestCity"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("TestState"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postalCode").value("TestPostalCode"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("TestPhone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("TestEmail"));

        verify(authorRepo, times(1)).save(any(Author.class));
    }

    @Test
    public void testGetAuthorById() throws Exception {
        when(authorRepo.findById(anyInt())).thenReturn(Optional.of(testAuthor));

        mockMvc.perform(MockMvcRequestBuilders.get("/author/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.authorId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("TestFirstName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("TestLastName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("TestStreet"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("TestCity"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("TestState"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.postalCode").value("TestPostalCode"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("TestPhone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("TestEmail"));
    }
    @Test
    public void testUpdateAuthor() throws Exception {
        Author updatedAuthor = new Author();
        updatedAuthor.setAuthorId(1);
        updatedAuthor.setFirstName("UpdatedFirstName");
        updatedAuthor.setLastName("UpdatedLastName");
        updatedAuthor.setStreet("UpdatedStreet");
        updatedAuthor.setCity("UpdatedCity");
        updatedAuthor.setState("UpdatedState");
        updatedAuthor.setPostalCode("UpdatedPostalCode");
        updatedAuthor.setPhone("UpdatedPhone");
        updatedAuthor.setEmail("UpdatedEmail");

        when(authorRepo.save(any(Author.class))).thenReturn(updatedAuthor);

    }

    @Test
    public void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("TestFirstName");
        author.setLastName("TestLastName");
        author.setStreet("TestStreet");
        author.setCity("TestCity");
        authorRepo.save(author);

        authorRepo.deleteById(author.getAuthorId());

        Optional<Author> deletedAuthor = authorRepo.findById(author.getAuthorId());

        assertFalse(deletedAuthor.isPresent());
    }
}