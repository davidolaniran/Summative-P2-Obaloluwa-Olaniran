package com.company.bookstore.controller;

import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PublisherControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    private PublisherController publisherController;

    @Mock
    private PublisherRepository publisherRepository;

    private Publisher testPublisher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(publisherController).build();

        testPublisher = new Publisher();
        testPublisher.setPublisherId(1);
        testPublisher.setName("Test Publisher");
        testPublisher.setStreet("Test Address");
        testPublisher.setPhone("Test Phone");
        testPublisher.setEmail("Test Email");
    }

    @Test
    public void testGetAllPublishers() throws Exception {
        List<Publisher> publishers = new ArrayList<>();
        publishers.add(testPublisher);

        when(publisherRepository.findAll()).thenReturn(publishers);

        mockMvc.perform(MockMvcRequestBuilders.get("/publisher"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPublisherById() throws Exception {
        when(publisherRepository.findById(anyInt())).thenReturn(Optional.of(testPublisher));

        mockMvc.perform(MockMvcRequestBuilders.get("/publisher/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddPublisher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/publisher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Publisher\",\"address\":\"Test Address\",\"phone\":\"Test Phone\",\"email\":\"Test Email\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdatePublisher() throws Exception {
        when(publisherRepository.findById(anyInt())).thenReturn(Optional.of(testPublisher));

        mockMvc.perform(MockMvcRequestBuilders.put("/publisher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"publisherId\":1,\"name\":\"Test Publisher\",\"address\":\"Test Address\",\"phone\":\"Test Phone\",\"email\":\"Test Email\"}"))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testDeletePublisher() throws Exception {
        when(publisherRepository.findById(anyInt())).thenReturn(Optional.of(testPublisher));

        mockMvc.perform(MockMvcRequestBuilders.delete("/publisher/1"))
                .andExpect(status().isNoContent());
    }
}
