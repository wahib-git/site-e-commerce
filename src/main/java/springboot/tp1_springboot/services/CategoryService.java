package springboot.tp1_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.tp1_springboot.model.Category;
import springboot.tp1_springboot.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Créer une catégorie
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Récupérer une catégorie par son ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Récupérer toutes les catégories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Mettre à jour une catégorie
    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existingCategoryOptional = categoryRepository.findById(id);

        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            existingCategory.setTitle(updatedCategory.getTitle());
            existingCategory.setDescription(updatedCategory.getDescription());
            // Ajouter d'autres champs si nécessaire
            return categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found with id " + id);
        }
    }

    // Supprimer une catégorie par son ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
