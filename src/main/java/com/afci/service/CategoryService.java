package com.afci.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.afci.data.Category;
import com.afci.data.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public CategoryRepository getCatRepository() {
		return categoryRepository;
	}

	// méthodes CRUD

	// Créer une nouvelle catégorie
    public ResponseEntity<Category> createCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(category.getIdCategory());

        if (existingCategory.isPresent()) {
            // Une catégorie avec le même ID existe déjà
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(savedCategory);
    }
    
    // Update une catégorie
    public ResponseEntity<Category> updateCategory(Category updatedCategory) {
        Long categoryId = updatedCategory.getIdCategory();
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);

        if (existingCategory.isPresent()) {
            // La catégorie existe, on peut procéder à la mise à jour
            Category updatedCategoryEntity = categoryRepository.save(updatedCategory);
            return ResponseEntity.ok(updatedCategoryEntity);
        } else {
            // La catégorie n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

	// Récupérer toutes les catégories
	public List<Category> getAllCategories() {
		return (List<Category>) categoryRepository.findAll();
	}

	// Récupérer une catégorie par son id
	public Optional<Category> getCategoryById(Long id) {
		/*
		 * Nous utilisons Optional pour gérer de manière sûre les cas où une catégorie
		 * n'est pas trouvée pour un identifiant donné. Au lieu de renvoyer null (ce qui
		 * peut provoquer des NullPointerException), nous renvoyons un Optional qui peut
		 * être traité de manière appropriée (présence ou absence de valeur).
		 */
		return categoryRepository.findById(id);
	}

	// delete -> on teste si la catégorie existe avant
	public ResponseEntity<Category> deleteCategory(Long id) {
		Optional<Category> existingCategory = categoryRepository.findById(id);

		if (existingCategory.isPresent()) {
			categoryRepository.deleteById(id);
			return ResponseEntity.ok(existingCategory.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
