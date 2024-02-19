package br.com.erudio.apiresterudio.unittests.mockito.services;

import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
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
    void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Book> listBook = input.MockBookEntityList();

        when(repository.findAll()).thenReturn(listBook);

        var result = service.findAll();

        assertNotNull(result);


        assertEquals("Author test"+1, result.get(1).getAuthor());
        assertEquals("Title test"+1, result.get(1).getTitle());
        assertEquals(25D, result.get(1).getPrice());
        assertNotNull(result.get(1).getLaunchDate());

        assertEquals("Author test"+5, result.get(5).getAuthor());
        assertEquals("Author test"+5, result.get(5).getAuthor());
        assertEquals(25D, result.get(5).getPrice());
        assertNotNull(result.get(5).getLaunchDate());

    }

    @Test
    void findById() {
        Book entity = input.mockBookEntity(1);

        when(repository.findById(1)).thenReturn(Optional.of(entity));

        var result = service.findById(1);

        assertNotNull(result);
        assertEquals("Author test1", result.getAuthor());
        assertEquals("Title test1", result.getTitle());
        assertEquals(25D, result.getPrice());
        assertNotNull(result.getLaunchDate());

    }

    @Test
    void update() {
//        var entity = input.mockBookEntity(1);
//        BookVo newBookVo = input.mockBookVO(1);
//
//        when(repository.findById(1)).thenReturn(Optional.of(entity));
//        when(repository.save(entity)).thenReturn(entity);
//
//        var result = service.update(newBookVo);
//
//        assertNotNull(result);
//        assertEquals("Author test1", result.getAuthor());
//        assertEquals("Title test1", result.getTitle());
//        assertEquals(25D, result.getPrice());
//        assertNotNull(result.getLaunchDate());
    }

    @Test
    void delete() {
        Integer id = 1;
        var book = input.mockBookEntity(id);

        when(repository.findById(id)).thenReturn(Optional.of(book));

        service.delete(id);
    }

    @Test
    void create() {

//        var book = input.mockBookEntity(1);
//        var bookVo = input.mockBookVO(1);
//
//
//        when(repository.save(book)).thenReturn(book);
//
//        var result = service.create(bookVo);
//
//        assertNotNull(result);
//        assertNotNull(result.getKey());
//        assertEquals("Author test1", result.getAuthor());
//        assertEquals("Title test1", result.getTitle());
//        assertEquals(25D, result.getPrice());
//        assertNotNull(result.getLaunchDate());
    }
}