package com.company.bookstore.controller;

import com.company.bookstore.model.Author;
import com.company.bookstore.model.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {

    @Autowired
    PublisherRepository publisherRepo;

    @PostMapping("/publisher")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    @GetMapping("/publisher/{publisherId}")
    public Publisher getPublisherById(@PathVariable int publisherId) {

        Optional<Publisher> returnVal = publisherRepo.findById(publisherId);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/publisher")
    public List<Publisher> getAllPublishers() {
        return publisherRepo.findAll();
    }

    @PutMapping("/publisher")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher) {
        publisherRepo.save(publisher);
    }

    @DeleteMapping("/publisher/{publisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int publisherId) {
        publisherRepo.deleteById(publisherId);
    }
}

