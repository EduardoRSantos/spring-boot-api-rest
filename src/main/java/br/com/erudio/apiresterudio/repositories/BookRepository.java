package br.com.erudio.apiresterudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erudio.apiresterudio.models.Book;


public interface BookRepository extends JpaRepository<Book, Integer> {
}
