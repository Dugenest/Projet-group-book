package com.afci.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.afci.data.Book;
import com.afci.data.BookRepository;
import com.afci.exception.BookNotFoundException;
import com.afci.exception.DuplicateBookException;
import com.afci.exception.InvalidBookDataException;

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
	public Book findTitleBook(String title) {
	    Book book = (Book) repository.findNameBookContainingAllIgnoreCase(title);

	    if (book == null) {
	        throw new BookNotFoundException("Aucun livre trouvé avec le nom : " + title);
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
	        throw new InvalidBookDataException("Le titre du livre ne doit pas être vide !");
	    }
	    if (b.getAuthor() == null || b.getAuthor().trim().isEmpty()) {
	        throw new InvalidBookDataException("L'auteur du livre ne doit pas être vide !");
	    }
	    if (b.getCategory() == null || b.getCategory().trim().isEmpty()) {
	        throw new InvalidBookDataException("La catégorie du livre ne doit pas être vide !");
	    }
	    if (b.getIsbnBook() == null || b.getIsbnBook().trim().isEmpty()) {
	        throw new InvalidBookDataException("L'ISBN du livre ne doit pas être vide !");
	    }
	    if (b.getTitle().length() < 1 || b.getTitle().length() > 30) { 
	        throw new InvalidBookDataException("Le titre du livre doit comporter entre 1 et 30 caractères !");
	    }
	    if (b.getAuthor().length() < 2 || b.getAuthor().length() > 20) { 
	        throw new InvalidBookDataException("L'auteur du livre doit comporter entre 2 et 20 caractères !");
	    }
	    if (b.getCategory().length() < 3 || b.getCategory().length() > 20) { 
	        throw new InvalidBookDataException("La catégorie du livre doit comporter entre 3 et 20 caractères !");
	    }
	    if (b.getIsbnBook().length() < 10 || b.getIsbnBook().length() > 13) { 
	        throw new InvalidBookDataException("L'ISBN du livre doit comporter entre 10 et 13 caractères !");
	    }

	    // Vérifier si le livre existe déjà par nom
	    if (repository.findTitleBook(b.getTitle()) != null) {
	        throw new DuplicateBookException("Le livre avec le nom " + b.getTitle() + " existe déjà !");
	    }

	    return repository.save(b); // Retourner le livre ajouté
	}

	// Modifier un livre
	public Book updateBook(Long id, Book newBook) {
	    return repository.findById(id).map(existingBook -> {
	        existingBook.setTitle(newBook.getTitle());
	        existingBook.setAuthor(newBook.getAuthor());

	        return repository.save(existingBook);
	    }).orElseThrow(() -> new BookNotFoundException("Le livre avec l'id " + id + " n'existe pas !"));
	}

	// Supprimer un livre
	public void deleteBook(Long id) {
	    // On utilise orElseThrow pour une exception plus claire
	    repository.findById(id)
	              .orElseThrow(() -> new BookNotFoundException("Le livre avec l'id " + id + " n'existe pas !"));
	    repository.deleteById(id);
	}


}
