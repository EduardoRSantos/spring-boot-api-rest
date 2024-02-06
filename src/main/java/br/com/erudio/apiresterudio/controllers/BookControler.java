package br.com.erudio.apiresterudio.controllers;

import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
import br.com.erudio.apiresterudio.models.Book;
import br.com.erudio.apiresterudio.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/book/v1")
public class BookControler {

    @Autowired
    private BookService service;


    @GetMapping
    public ResponseEntity<List<BookVo>> findAll(){return ResponseEntity.ok().body(service.findAll());}


    @GetMapping("/{id}")
    public ResponseEntity<BookVo> findById(@PathVariable(name = "id") Integer id ){ return ResponseEntity.ok().body(service.findById(id)); }
}
