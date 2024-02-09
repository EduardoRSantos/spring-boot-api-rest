package br.com.erudio.apiresterudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonPropertyOrder({"id", "author", "launch_date", "price", "title"})
public class BookVo extends RepresentationModel<BookVo> implements Serializable {

    @JsonProperty("id")
    private Integer Key;
    private String author;
    private Date launchDate;
    private Double price;
    private String title;

}
