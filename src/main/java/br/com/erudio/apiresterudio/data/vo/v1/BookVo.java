package br.com.erudio.apiresterudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonPropertyOrder({"id", "author", "lauchDate", "price", "title"})
public class BookVo implements Serializable {

    @JsonProperty("id")
    private Integer Key;
    private String author;
    private Date lauchDate;
    private Double price;
    private String title;

}
