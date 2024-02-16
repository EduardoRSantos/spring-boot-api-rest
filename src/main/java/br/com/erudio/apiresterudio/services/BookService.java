package br.com.erudio.apiresterudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.apiresterudio.controllers.BookControler;
import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
import br.com.erudio.apiresterudio.exceptions.RequireObjectIsNullException;
import br.com.erudio.apiresterudio.mapper.custom.MapperCustom;
import br.com.erudio.apiresterudio.models.Book;
import br.com.erudio.apiresterudio.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookVo> findAll() {
        var listBook = repository.findAll();
        var listBookVo = MapperCustom.parseListObjects(listBook, BookVo.class);
        listBookVo.forEach(x -> {
            x.add(linkTo(methodOn(BookControler.class).findAll()).withSelfRel());
        });
        listBookVo.get(1).setLaunchDate(listBook.get(1).getLaunchDate());
        return listBookVo;
    }

    public BookVo findById(Integer id) {
        var entity = repository.findById(id).orElseThrow(RequireObjectIsNullException::new);
        var entityVo = MapperCustom.parseObject(entity, BookVo.class);
        entityVo.add(linkTo(methodOn(BookControler.class).findById(entityVo.getKey())).withSelfRel());
        entityVo.setLaunchDate(entity.getLaunchDate());
        return entityVo;
    }

    public BookVo create(BookVo BookVo) {
        if (BookVo == null)
            throw new RequireObjectIsNullException();
        var entity = MapperCustom.parseObject(BookVo, Book.class);
        var book = repository.save(entity);
        var vo = MapperCustom.parseObject(book, BookVo.class);
        vo.add(linkTo(methodOn(BookControler.class).findById(book.getId())).withSelfRel());
        return vo;
    }

    public BookVo update(BookVo newBookVo) {
        var entityVo = findById(newBookVo.getKey());
        var bookCurrent = MapperCustom.parseObject(updateBook(entityVo, newBookVo), Book.class);
        bookCurrent = repository.save(bookCurrent);
        var vo = MapperCustom.parseObject(bookCurrent, BookVo.class);
        vo.add(linkTo(methodOn(BookControler.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Integer id) {
        var entity = repository.findById(id).orElseThrow(RequireObjectIsNullException::new);
        repository.delete(entity);
    }

    private BookVo updateBook(BookVo entityVo, BookVo newBookVo) {
        entityVo.setKey(newBookVo.getKey());
        entityVo.setAuthor(newBookVo.getAuthor());
        entityVo.setLaunchDate(newBookVo.getLaunchDate());
        entityVo.setPrice(newBookVo.getPrice());
        entityVo.setTitle(newBookVo.getTitle());

        return entityVo;
    }

}
