package br.com.erudio.apiresterudio.mapper.custom;

import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
import br.com.erudio.apiresterudio.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperBookCustom {


    private static final ModelMapper mapper = new ModelMapper();


    static {
        mapper.createTypeMap(Book.class, BookVo.class).addMapping(Book::getId, BookVo::setKey);
        mapper.createTypeMap(BookVo.class, Book.class).addMapping(BookVo::getKey, Book::setId);
    }


    public static <O, D> D parseObject(O origin, Class<D> destination ){
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){
        List<D> listDestination = new ArrayList<>();
        origin.forEach(x -> { listDestination.add(mapper.map(x, destination));});
        return listDestination;
    }
}
