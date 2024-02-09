package br.com.erudio.apiresterudio.mapper.custom;

import br.com.erudio.apiresterudio.data.vo.v1.BookVo;
import br.com.erudio.apiresterudio.data.vo.v1.PersonVo;
import br.com.erudio.apiresterudio.models.Book;
import br.com.erudio.apiresterudio.models.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MapperCustom {

    private static final ModelMapper mapper =  new ModelMapper();

    static {
        mapper.createTypeMap(Person.class, PersonVo.class).addMapping(Person::getId, PersonVo::setKey);
        mapper.createTypeMap(PersonVo.class, Person.class).addMapping(PersonVo::getKey, Person::setId);
        mapper.createTypeMap(Book.class, BookVo.class).addMapping(Book::getId, BookVo::setKey);
        mapper.createTypeMap(BookVo.class, Book.class).addMapping(BookVo::getKey, Book::setId);
    }
    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
    
}
