package br.com.erudio.apiresterudio.services;

import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
import br.com.erudio.apiresterudio.exceptions.RequireObjectIsNullException;
import br.com.erudio.apiresterudio.exceptions.ResourceNotFoundException;
import br.com.erudio.apiresterudio.mapper.custom.MapperBookCustom;
import br.com.erudio.apiresterudio.models.Book;
import br.com.erudio.apiresterudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;


    public List<BookVo> findAll(){return MapperBookCustom.parseListObjects(repository.findAll(), BookVo.class);}

    public BookVo findById(Integer id){
        var entity = repository.findById(id).orElseThrow(RequireObjectIsNullException::new);
        return MapperBookCustom.parseObject(entity, BookVo.class);
    }

}
