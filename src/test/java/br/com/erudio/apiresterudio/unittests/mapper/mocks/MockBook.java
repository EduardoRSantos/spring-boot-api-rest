package br.com.erudio.apiresterudio.unittests.mapper.mocks;

import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
import br.com.erudio.apiresterudio.data.vo.v1.PersonVo;
import br.com.erudio.apiresterudio.models.Book;
import br.com.erudio.apiresterudio.models.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockBookEntity() { return mockBookEntity(0); }

    public BookVo mockBookVO() { return mockBookVO(0); }


    public List<Book> MockBookEntityList(){
        List<Book> listBook = new ArrayList<>();
        for (int i=0;i<15;i++){
            listBook.add(mockBookEntity(i));
        }
        return listBook;
    }
    public List<BookVo> MockBookVoList(){
        List<BookVo> listBookVo = new ArrayList<>();
        for (int i=0;i<15;i++){
            listBookVo.add(mockBookVO(i));
        }
        return listBookVo;
    }

    public Book mockBookEntity(Integer number){
        Book book = new Book();
        book.setId(number);
        book.setAuthor("Author test" + number);
        book.setTitle("Title test" + number);
        book.setPrice(25D);
        book.setLaunchDate(new Date());

        return book;
    }
    public BookVo mockBookVO(Integer number) {
        BookVo bookVo = new BookVo();
        bookVo.setKey(number);
        bookVo.setAuthor("Author test" + number);
        bookVo.setTitle("Title test" + number);
        bookVo.setPrice(25D);
        bookVo.setLaunchDate(new Date());
        return bookVo;
    }


}
