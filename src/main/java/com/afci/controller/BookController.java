package com.afci.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afci.data.Book;
import com.afci.exception.BookNotFoundException;
import com.afci.service.BookService;


@RestController
@RequestMapping("/books")

public class BookController {
	
	private final BookService bs;

	// Instanciation du service
	public BookController(BookService bs) {
	    this.bs = bs;
	}

	// Liste de tous les livres
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
	    List<Book> books = bs.getAllBooks();
	    
	    if (books.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 si la liste est vide
	    }
	    
	    return new ResponseEntity<>(books, HttpStatus.OK); // 200 OK pour une liste réussie
	}

	// Ajouter un livre
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book b) {
	    Book createdBook = bs.addBook(b);
	    return new ResponseEntity<>(createdBook, HttpStatus.CREATED); // 201 pour la création
	}

	// Modifier un livre
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
	    try {
	        Book updatedBook = bs.updateBook(id, book);
	        return new ResponseEntity<>(updatedBook, HttpStatus.OK); // 200 OK pour la mise à jour
	    } catch (BookNotFoundException e) {
	        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404 si livre non trouvé
	    }
	}

	// Trouver un livre par ID
	@GetMapping("/{id}")
	public ResponseEntity<Book> findBook(@PathVariable Long id) {
	    try {
	        Book book = bs.findBookById(id);
	        return new ResponseEntity<>(book, HttpStatus.OK); // 200 OK si livre trouvé
	    } catch (BookNotFoundException e) {
	        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404 si livre non trouvé
	    }
	}

	// Trouver un livre par son nom
	@GetMapping("/search/{nameBook}")
	public ResponseEntity<Book> findBookByName(@PathVariable String nameBook) {
	    try {
	        Book book = bs.findBookByName(nameBook);
	        return new ResponseEntity<>(book, HttpStatus.OK); // 200 OK pour une recherche réussie
	    } catch (BookNotFoundException e) {
	        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404 si aucun livre trouvé
	    }
	}

	// Supprimer un livre
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
	    try {
	        bs.deleteBook(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 si suppression réussie
	    } catch (BookNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 si livre non trouvé
	    }
	}
	
}
