package springboot.tp1_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.tp1_springboot.model.Orders;
import springboot.tp1_springboot.repository.OrdersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    // Créer une commande
    public Orders createOrders(Orders ord) {
        return ordersRepository.save(ord);
    }

    // Récupérer une commande par son ID
    public Optional<Orders> getOrdersById(Long id) {
        return ordersRepository.findById(id);
    }

    // Récupérer toutes les commandes
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // Mettre à jour une commande
    public Orders updateOrders(Long id, Orders updatedOrder) {
        Optional<Orders> existingOrderOptional = ordersRepository.findById(id);

        if (existingOrderOptional.isPresent()) {
            Orders existingOrder = existingOrderOptional.get();
            existingOrder.setRef(updatedOrder.getRef());
            existingOrder.setCustomer(updatedOrder.getCustomer());
            existingOrder.setPrice(updatedOrder.getPrice());
            existingOrder.setDate(updatedOrder.getDate());
            // Ajouter d'autres champs si nécessaire
            return ordersRepository.save(existingOrder);
        } else {
            throw new RuntimeException("Order not found with id " + id);
        }
    }

    // Supprimer une commande par son ID
    public void deleteOrders(Long id) {
        ordersRepository.deleteById(id);
    }
}

