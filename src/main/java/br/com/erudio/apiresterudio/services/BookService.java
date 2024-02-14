package br.com.erudio.apiresterudio.services;


import br.com.erudio.apiresterudio.controllers.BookControler;
import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
import br.com.erudio.apiresterudio.exceptions.RequireObjectIsNullException;
import br.com.erudio.apiresterudio.mapper.custom.MapperCustom;
import br.com.erudio.apiresterudio.models.Book;
import br.com.erudio.apiresterudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookVo> findAll(){
        var listBook = repository.findAll();
        var listBookVo = MapperCustom.parseListObjects(listBook, BookVo.class);
        listBookVo.forEach(x->{x.add(linkTo(methodOn(BookControler.class).findAll()).withSelfRel());});
        listBookVo.get(1).setLaunchDate(listBook.get(1).getLaunchDate());
        return listBookVo;
    }

    public BookVo findById(Integer id){
        var entity = repository.findById(id).orElseThrow(RequireObjectIsNullException::new);
        var entityVo = MapperCustom.parseObject(entity, BookVo.class);
        entityVo.add(linkTo(methodOn(BookControler.class).findById(entityVo.getKey())).withSelfRel());
        entityVo.setLaunchDate(entity.getLaunchDate());
        return entityVo;
    }

    public BookVo update(BookVo newBookVo){
        var entityVo = findById(newBookVo.getKey());
        var BookCurrent = MapperCustom.parseObject(updateBook(entityVo, newBookVo), Book.class);
        BookCurrent = repository.save(BookCurrent);
        return MapperCustom.parseObject(BookCurrent, BookVo.class);
    }

    public void delete(Integer id){
        var entity = repository.findById(id).orElseThrow(RequireObjectIsNullException::new);
        repository.delete(entity);
    }


    private BookVo updateBook(BookVo entityVo,BookVo newBookVo){
        entityVo.setKey(newBookVo.getKey());
        entityVo.setAuthor(newBookVo.getAuthor());
        entityVo.setLaunchDate(newBookVo.getLaunchDate());
        entityVo.setPrice(newBookVo.getPrice());
        entityVo.setTitle(newBookVo.getTitle());

        return entityVo;
    }

}
