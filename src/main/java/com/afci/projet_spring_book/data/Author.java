package com.afci.projet_spring_book.data;

public class Author {
	 //--------------------------------------------- //
	//je definis les attributs de ma classe auteur  //
	//---------------------------------------------//
	
	private int Id_Auteur ;
	private String Nom ;
	private String Prenom;
	private String DateNaiss;
	private String Nationlite;
	private String Biographie;
	private String Deces;
	
	 	 //-------------------------------------------------------------- //
		//   je cree le constructeur vide le seul dont spring as besoin  //
	   //---------------------------------------------------------------//
	public Author() {
	
	}


	 //------------------------------------------------//
	// je met la tout ce qui est getters adn setters  //
	//-----------------------------------------------//
	public int getId_Auteur() {
		return Id_Auteur;
	}



	public void setId_Auteur(int id_Auteur) {
		Id_Auteur = id_Auteur;
	}



	public String getNom() {
		return Nom;
	}



	public void setNom(String nom) {
		Nom = nom;
	}



	public String getPrenom() {
		return Prenom;
	}



	public void setPrenom(String prenom) {
		Prenom = prenom;
	}



	public String getDateNaiss() {
		return DateNaiss;
	}



	public void setDateNaiss(String dateNaiss) {
		DateNaiss = dateNaiss;
	}



	public String getNationlite() {
		return Nationlite;
	}



	public void setNationlite(String nationlite) {
		Nationlite = nationlite;
	}



	public String getBiographie() {
		return Biographie;
	}



	public void setBiographie(String biographie) {
		Biographie = biographie;
	}



	public String getDeces() {
		return Deces;
	}



	public void setDeces(String deces) {
		Deces = deces;
	}
	
	
	
	
	
	
	
	
	
}
