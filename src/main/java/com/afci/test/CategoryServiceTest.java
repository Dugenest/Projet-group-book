package com.afci.test;

import com.afci.data.Category;
import com.afci.data.CategoryRepository;
import com.afci.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryServiceTest {
    public static void main(String[] args) {
        // Créer une instance de CategoryService avec un repository fictif
        CategoryService categoryService = new CategoryService(new FakeCategoryRepository());

        // Créer une nouvelle catégorie
        Category newCategory = new Category(1L, "Nouvelle Catégorie");
        categoryService.createCategory(newCategory);

        // Récupérer toutes les catégories
        System.out.println("Toutes les catégories :");
        categoryService.getAllCategories().forEach(System.out::println);
        
        Category newCategory2 = new Category(1L, "Changement de valeurs");
        categoryService.updateCategory(newCategory2);
        
        // Mises à jour
        System.out.println("Maj : ");
        categoryService.getAllCategories().forEach(System.out::println);

        // Récupérer une catégorie par son ID
        Category categoryById = categoryService.getCategoryById(1L).orElse(null);
        System.out.println("\nCatégorie avec l'ID 1 : " + categoryById);

        // Supprimer une catégorie
        categoryService.deleteCategory(1L);
        System.out.println("\nAprès suppression de la catégorie 1 :");
        categoryService.getAllCategories().forEach(System.out::println);
    }

    // Implémentation simplifiée de CategoryRepository
    private static class FakeCategoryRepository implements CategoryRepository {
        private static List<Category> categories = new ArrayList<>();

        @Override
        public Optional<Category> findById(Long id) {
            return categories.stream()
                    .filter(c -> c.getIdCategory().equals(id))
                    .findFirst();
        }

        @Override
        public List<Category> findAll() {
            return categories;
        }

        @Override
        public <S extends Category> S save(S entity) {
            categories.add(entity);
            return entity;
        }

        @Override
        public void deleteById(Long id) {
            categories.removeIf(c -> c.getIdCategory().equals(id));
        }

		@Override
		public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean existsById(Long id) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterable<Category> findAllById(Iterable<Long> ids) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long count() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void delete(Category entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAllById(Iterable<? extends Long> ids) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll(Iterable<? extends Category> entities) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteAll() {
			// TODO Auto-generated method stub
			
		}
    }
}