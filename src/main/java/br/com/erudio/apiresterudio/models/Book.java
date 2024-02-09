package br.com.erudio.apiresterudio.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    @Column(name = "launch_date")
    private Date launchDate;
    private Double price;
    private String title;

}
