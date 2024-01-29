package br.com.erudio.apiresterudio.mapper.custom;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.apiresterudio.data.vo.v1.PersonVo;
import br.com.erudio.apiresterudio.models.Person;

@Service
public class PersonMapper {
    

    @Autowired
    private ModelMapper mapper;
    private List<PersonVo> listVo = new ArrayList<PersonVo>();


    public List<PersonVo> findAllVo(List<Person> listPersons){
        for (Person person : listPersons) {
            listVo.add(mapper.map(person, PersonVo.class));
        }
        return listVo;
    }
}
