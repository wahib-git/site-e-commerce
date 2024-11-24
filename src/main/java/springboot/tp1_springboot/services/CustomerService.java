package springboot.tp1_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.tp1_springboot.model.Customer;
import springboot.tp1_springboot.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Créer un client
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Récupérer un client par son ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Récupérer tous les clients
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Mettre à jour un client
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhone(updatedCustomer.getPhone());
            // Ajouter d'autres champs si nécessaire
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    // Supprimer un client par son ID
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
