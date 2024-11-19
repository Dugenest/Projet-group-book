package com.afci.projet_spring_book.data;

import java.io.Serializable;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

	// Attributs
    private Long idbook;
    private String title;
    private Author author; // Agrégation vers Author
    private Category category; // Agrégation vers Category
    private int dateBook;
    private String isbnBook;
	
    
    // Constructeur
    public Book() {
    }


    // Getters et Setters
	public Long getIdbook() {
		return idbook;
	}


	public void setIdbook(Long idbook) {
		this.idbook = idbook;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public int getDateBook() {
		return dateBook;
	}


	public void setDateBook(int dateBook) {
		this.dateBook = dateBook;
	}


	public String getIsbnBook() {
		return isbnBook;
	}


	public void setIsbnBook(String isbnBook) {
		this.isbnBook = isbnBook;
	}
    s

}
