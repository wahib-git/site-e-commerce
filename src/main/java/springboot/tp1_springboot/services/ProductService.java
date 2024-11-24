package springboot.tp1_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.tp1_springboot.model.Product;
import springboot.tp1_springboot.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Créer un produit
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }



    // Récupérer tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Récupérer un produit par son ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    // Mettre à jour un produit
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setSubcategory(updatedProduct.getSubcategory());
            // Ajouter d'autres champs si nécessaire
            return productRepository.saveAndFlush(updatedProduct);
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    // Supprimer un produit par son ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }
}
