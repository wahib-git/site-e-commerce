package springboot.tp1_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.tp1_springboot.model.Product;
import springboot.tp1_springboot.model.Provider;
import springboot.tp1_springboot.services.ProductService;
import springboot.tp1_springboot.services.ProviderService;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProviderService providerService;

    // Affiche le formulaire pour ajouter un produit
    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    public String addProduct( Model model) {
        //Product product = new Product();
        model.addAttribute("ProductForm", new Product());
        List<Provider> providers = providerService.getAllProviders();
        model.addAttribute("listeProvider", providers);
        return "product/new_product";
    }

    // Sauvegarde un produit
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("ProductForm") Product product) {
        productService.createProduct(product);
        return "redirect:/all";
    }
    @GetMapping("edit/{id}")
    public String editProduct(@PathVariable("id")long id,Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product",product);
        return "product/update_product";
    }
    @PostMapping("update/{id}")
    public String updateProduct (@PathVariable("id")long id,Product product ){
        productService.updateProduct(product);
        return "redirect:/all";
    }

    // Affiche tous les produits
    @RequestMapping("/all")
    public String listProducts (Model model) {
        List<Product> listProducts = productService.getAllProducts();
        model.addAttribute("listProducts", listProducts);
        return "product/liste_Products";
    }
    // Supprime un produit
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/all";
    }
}
