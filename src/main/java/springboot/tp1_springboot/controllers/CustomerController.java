package springboot.tp1_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.tp1_springboot.model.Customer;
import springboot.tp1_springboot.services.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Affiche le formulaire pour ajouter un nouveau client
    @GetMapping("/customers/add")
    public String addCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customerForm", customer);
        return "customer/new_customer";
    }

    // Sauvegarde un nouveau client
    @PostMapping("/customers/save")
    public String saveCustomer(@ModelAttribute("customerForm") Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/customers/all";
    }

    // Affiche tous les clients
    @GetMapping("/customers/all")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer/customers_list";
    }

    // Affiche les détails d'un client par son ID
    @GetMapping("/customers/{id}")
    public String getCustomerById(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
        model.addAttribute("customer", customer);
        return "customer/customer_detail";
    }

    // Affiche le formulaire de modification pour un client
    @GetMapping("customers/edit/{id}")
    public String editCustomerForm(@PathVariable("id") Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
        model.addAttribute("customerForm", customer);
        return "customer/edit_customer";
    }

    // Met à jour un client
    @PostMapping("customers/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute("customerForm") Customer updatedCustomer) {
        customerService.updateCustomer(id, updatedCustomer);
        return "redirect:/customers/all";
    }

    // Supprime un client par son ID
    @GetMapping("customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers/all";
    }
}
