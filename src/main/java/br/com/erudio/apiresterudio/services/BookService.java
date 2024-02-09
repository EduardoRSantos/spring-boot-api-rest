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

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookVo> findAll(){
        List<Book> listBook = repository.findAll();
        List<BookVo> listBookVo = MapperCustom.parseListObjects(listBook, BookVo.class);
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

    public void update(){
    }

    public void delete(){

    }


}
