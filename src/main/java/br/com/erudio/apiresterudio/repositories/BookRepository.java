package br.com.erudio.apiresterudio.repositories;

import br.com.erudio.apiresterudio.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
