package springboot.tp1_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.tp1_springboot.model.Subcategory;
import springboot.tp1_springboot.services.SubcategoryService;

import java.util.List;

@Controller
@RequestMapping("/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    // Affiche le formulaire pour ajouter une nouvelle sous-catégorie
    @GetMapping("/add")
    public String addSubcategoryForm(Model model) {
        Subcategory subcategory = new Subcategory();
        model.addAttribute("subcategoryForm", subcategory);
        return "new_subcategory";
    }

    // Sauvegarde une nouvelle sous-catégorie
    @PostMapping("/save")
    public String saveSubcategory(@ModelAttribute("subcategoryForm") Subcategory subcategory) {
        subcategoryService.createSubcategory(subcategory);
        return "redirect:/subcategories/all";
    }

    // Affiche toutes les sous-catégories
    @GetMapping("/all")
    public String getAllSubcategories(Model model) {
        List<Subcategory> subcategories = subcategoryService.getAllSubcategories();
        model.addAttribute("subcategories", subcategories);
        return "subcategories_list";
    }

    // Affiche les détails d'une sous-catégorie par son ID
    @GetMapping("/{id}")
    public String getSubcategoryById(@PathVariable("id") Long id, Model model) {
        Subcategory subcategory = subcategoryService.getSubcategoryById(id)
                .orElseThrow(() -> new RuntimeException("Subcategory not found with id " + id));
        model.addAttribute("subcategory", subcategory);
        return "subcategory_detail";
    }

    // Affiche le formulaire de modification pour une sous-catégorie
    @GetMapping("/edit/{id}")
    public String editSubcategoryForm(@PathVariable("id") Long id, Model model) {
        Subcategory subcategory = subcategoryService.getSubcategoryById(id)
                .orElseThrow(() -> new RuntimeException("Subcategory not found with id " + id));
        model.addAttribute("subcategoryForm", subcategory);
        return "edit_subcategory";
    }

    // Met à jour une sous-catégorie
    @PostMapping("/update/{id}")
    public String updateSubcategory(@PathVariable("id") Long id, @ModelAttribute("subcategoryForm") Subcategory updatedSubcategory) {
        subcategoryService.updateSubcategory(id, updatedSubcategory);
        return "redirect:/subcategories/all";
    }

    // Supprime une sous-catégorie par son ID
    @GetMapping("/delete/{id}")
    public String deleteSubcategory(@PathVariable("id") Long id) {
        subcategoryService.deleteSubcategory(id);
        return "redirect:/subcategories/all";
    }
}
