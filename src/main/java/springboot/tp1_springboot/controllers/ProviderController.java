package springboot.tp1_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.tp1_springboot.model.Provider;
import springboot.tp1_springboot.services.ProviderService;

import java.util.List;

@Controller
@RequestMapping("/providers") // Préfixe commun pour les endpoints
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // Affiche le formulaire pour ajouter un nouveau fournisseur
    @GetMapping("/addProvider")
    public String showAddProviderForm(Model model) {
        model.addAttribute("providerForm", new Provider());
        return "providers/new_provider";
    }

    // Sauvegarde un nouveau fournisseur
    @PostMapping("/saveProvider")
    public String saveProvider(@ModelAttribute("providerForm") Provider provider) {
        providerService.createProvider(provider);
        return "redirect:/providers/allProvider";
    }

    // Affiche tous les fournisseurs
    @GetMapping("/allProvider")
    public String showAllProviders(Model model) {
        List<Provider> providers = providerService.getAllProviders();
        model.addAttribute("providers", providers);
        return "providers/providers_list";
    }

    // Affiche les détails d'un fournisseur par son ID
    @GetMapping("Provider/{id}")
    public String showProviderDetails(@PathVariable("id") Long id, Model model) {
        Provider provider = providerService.getProviderById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found with id " + id));
        model.addAttribute("provider", provider);
        return "providers/provider_detail";
    }

    // Affiche le formulaire de modification pour un fournisseur
    @GetMapping("/edit/{id}")
    public String showEditProviderForm(@PathVariable("id") Long id, Model model) {
        Provider provider = providerService.getProviderById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found with id " + id));
        model.addAttribute("providerForm", provider);
        return "providers/edit_provider";
    }

    // Met à jour un fournisseur
    @PostMapping("/update/{id}")
    public String updateProvider(@PathVariable("id") Long id, @ModelAttribute("providerForm") Provider updatedProvider) {
        providerService.updateProvider(id, updatedProvider);
        return "redirect:/providers/allProvider";
    }

    // Supprime un fournisseur par son ID
    @GetMapping("/delete/{id}")
    public String deleteProvider(@PathVariable("id") Long id) {
        providerService.deleteProvider(id);
        return "redirect:/providers/allProvider";
    }
}