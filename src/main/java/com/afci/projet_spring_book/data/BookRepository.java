package com.afci.projet_spring_book.data;

<<<<<<< HEAD
public interface BookRepository {
=======
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository extends CrudRepository<Book, Long> {

	Book findNameBookContainingAllIgnoreCase(String nameBook);
>>>>>>> book

}
