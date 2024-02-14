package br.com.erudio.apiresterudio.unittests.mockito.services;

import br.com.erudio.apiresterudio.models.Book;
import br.com.erudio.apiresterudio.repositories.BookRepository;
import br.com.erudio.apiresterudio.services.BookService;
import br.com.erudio.apiresterudio.unittests.mapper.mocks.MockBook;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Book> listBook = input.MockBookEntityList();

        when(repository.findAll()).thenReturn(listBook);

        var result = repository.findAll();

        assertNotNull(result);


        assertEquals("Author test"+1, result.get(1).getAuthor());
        assertEquals("Title test"+1, result.get(1).getTitle());
        assertNotNull(result.get(1).getPrice());
        assertNotNull(result.get(1).getLaunchDate());

        assertEquals("Author test"+5, result.get(5).getAuthor());
        assertEquals("Author test"+5, result.get(5).getAuthor());
        assertNotNull(result.get(5).getPrice());
        assertNotNull(result.get(5).getLaunchDate());

    }

    @Test
    void findById() {
        Book entity = input.mockBookEntity(1);

        when(repository.findById(1)).thenReturn(Optional.of(entity));

        var result = repository.findById(1);

        assertNotNull(result);
        assertEquals("Author test"+1, result.get().getAuthor());
        assertEquals("Title test"+1, result.get().getTitle());
        assertNotNull(result.get().getPrice());
        assertNotNull(result.get().getLaunchDate());

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}