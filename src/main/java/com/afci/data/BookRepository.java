package com.afci.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository extends CrudRepository<Book, Long> {

	Book findNameBookContainingAllIgnoreCase(String title);

	Object findTitleBook(String title);

}
