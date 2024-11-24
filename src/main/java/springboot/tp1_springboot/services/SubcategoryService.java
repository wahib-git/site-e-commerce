package springboot.tp1_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.tp1_springboot.model.Subcategory;
import springboot.tp1_springboot.repository.SubcategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    // Créer une sous-catégorie
    public Subcategory createSubcategory(Subcategory sub) {
        return subcategoryRepository.save(sub);
    }

    // Récupérer une sous-catégorie par ID
    public Optional<Subcategory> getSubcategoryById(Long id) {
        return subcategoryRepository.findById(id);
    }

    // Récupérer toutes les sous-catégories
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    // Mettre à jour une sous-catégorie
    public Subcategory updateSubcategory(Long id, Subcategory updatedSubcategory) {
        Optional<Subcategory> existingSubcategoryOptional = subcategoryRepository.findById(id);

        if (existingSubcategoryOptional.isPresent()) {
            Subcategory existingSubcategory = existingSubcategoryOptional.get();
            existingSubcategory.setTitle(updatedSubcategory.getTitle());
            existingSubcategory.setDescription(updatedSubcategory.getDescription());
            existingSubcategory.setCategory(updatedSubcategory.getCategory());
            existingSubcategory.setProducts(updatedSubcategory.getProducts());
            return subcategoryRepository.save(existingSubcategory);
        } else {
            throw new RuntimeException("Subcategory not found with id " + id);
        }
    }

    // Supprimer une sous-catégorie par ID
    public void deleteSubcategory(Long id) {
        subcategoryRepository.deleteById(id);
    }
}

