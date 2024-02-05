package br.com.erudio.apiresterudio.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id","firstName","lastName","address","gender"})
public class PersonVo extends RepresentationModel<PersonVo>  implements Serializable {

    @JsonProperty("id")
    private Long key;

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String address;
    private String gender;
    
}
