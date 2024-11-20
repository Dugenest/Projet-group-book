package com.afci.projet_spring_book.service;

<<<<<<< HEAD
public class BookService {

=======
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.afci.projet_spring_book.data.Book;
import com.afci.projet_spring_book.data.BookRepository;
import com.afci.projet_spring_book.exception.BookNotFoundException;
import com.afci.projet_spring_book.exception.DuplicateBookException;
import com.afci.projet_spring_book.exception.InvalidBookDataException;

@Service

public class BookService {

	private final BookRepository repository;

	// Constructeur
	public BookService(BookRepository repository) {
	    this.repository = repository;
	}

	// Lister les livres
	public List<Book> getAllBooks() {
	    List<Book> books = new ArrayList<>();
	    repository.findAll().forEach(books::add);
	    return books;
	}

	// Trouver un livre par son id
	public Book findBookById(Long id) {
	    return repository.findById(id)
	            .orElseThrow(() -> new BookNotFoundException("Le livre désigné par l'id: " + id + " n'existe pas !"));
	}

	// Trouver un livre avec son nom
	public Book findBookByName(String nameBook) {
	    Book book = (Book) repository.findNameBookContainingAllIgnoreCase(nameBook);

	    if (book == null) {
	        throw new BookNotFoundException("Aucun livre trouvé avec le nom : " + nameBook);
	    }

	    return book;
	}

	// Ajouter un livre
	public Book addBook(Book b) {
	    if (b == null) {
	        throw new IllegalArgumentException("Le livre fourni est nul !");
	    }

	    // Vérification des contraintes
	    if (b.getTitle() == null || b.getTitle().trim().isEmpty()) {
	        throw new InvalidBookDataException("Le nom du livre ne doit pas être vide !");
	    }
	    if (b.getAuthor() == null || b.getAuthor().trim().isEmpty()) {
	        throw new InvalidBookDataException("Le nom du livre ne doit pas être vide !");
	    }
	    if (b.getNameBook().length() < 3 || b.getNameBook().length() > 20) { 
	        throw new InvalidBookDataException("Le nom du livre doit comporter entre 3 et 20 caractères !");
	    }

	    // Vérifier si le livre existe déjà par nom
	    if (repository.findNameBook(b.getNameBook()) != null) {
	        throw new DuplicateBookException("Le livre avec le nom " + b.getNameBook() + " existe déjà !");
	    }

	    return repository.save(b); // Retourner le livre ajouté
	}

	// Modifier un livre
	public Book updateBook(Long id, Book newBook) {
	    return repository.findById(id).map(existingBook -> {
	        existingBook.setNameBook(newBook.getNameBook());
	        existingBook.setPriceBook(newBook.getPriceBook());

	        return repository.save(existingBook);
	    }).orElseThrow(() -> new BookNotFoundException("Livre avec l'id " + id + " n'existe pas !"));
	}

	// Supprimer un livre
	public void deleteBook(Long id) {
	    // On utilise orElseThrow pour une exception plus claire
	    repository.findById(id)
	              .orElseThrow(() -> new BookNotFoundException("Le livre avec l'id " + id + " n'existe pas !"));
	    repository.deleteById(id);
	}


>>>>>>> book
}
