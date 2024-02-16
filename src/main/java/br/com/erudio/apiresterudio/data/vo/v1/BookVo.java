package br.com.erudio.apiresterudio.data.vo.v1;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "id", "author", "launch_date", "price", "title" })
public class BookVo extends RepresentationModel<BookVo> implements Serializable {

    @JsonProperty("id")
    private Integer Key;
    private String author;
    private Date launchDate;
    private Double price;
    private String title;

}
