package springboot.tp1_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.tp1_springboot.model.Category;
import springboot.tp1_springboot.services.CategoryService;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Affiche le formulaire pour ajouter une catégorie
    @RequestMapping("/addCategory")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("CategoryForm", category);
        return "category/new_category";
    }

    // Sauvegarde une catégorie
    @RequestMapping(value = "/save_category", method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("CategoryForm") Category category) {
        categoryService.createCategory(category);
        return "redirect:/allCategories";
    }

    // Affiche toutes les catégories
    @RequestMapping("/allCategories")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/category_list";
    }

    // Affiche les détails d'une catégorie par son ID
    @RequestMapping("/category/{id}")
    public String getCategoryById(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        model.addAttribute("category", category);
        return "category/category_detail";
    }

    // Affiche le formulaire de modification pour une catégorie
    @RequestMapping("/editCategory/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
        model.addAttribute("CategoryForm", category);
        return "category/edit_category";
    }

    // Met à jour une catégorie
    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.POST)
    public String updateCategory(@PathVariable("id") Long id, @ModelAttribute("CategoryForm") Category updatedCategory) {
        categoryService.updateCategory(id, updatedCategory);
        return "redirect:/allCategories";
    }

    // Supprime une catégorie
    @RequestMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/allCategories";
    }
}
