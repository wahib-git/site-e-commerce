package springboot.tp1_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.tp1_springboot.model.Orders;
import springboot.tp1_springboot.services.OrdersService;

import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    // Affiche le formulaire pour ajouter une nouvelle commande
    @RequestMapping("/addOrder")
    public String addOrder(Model model) {
        Orders order = new Orders();
        model.addAttribute("orderForm", order);
        return "order/new_order";
    }

    // Sauvegarde une nouvelle commande
    @RequestMapping(value = "/save_order", method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute("orderForm") Orders order) {
        ordersService.createOrders(order);
        return "redirect:/allOrders";
    }

    // Affiche toutes les commandes
    @RequestMapping("/allOrders")
    public String getAllOrders(Model model) {
        List<Orders> orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/orders_list";
    }

    // Affiche les détails d'une commande par son ID
    @RequestMapping("/order/{id}")
    public String getOrderById(@PathVariable("id") Long id, Model model) {
        Orders order = ordersService.getOrdersById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
        model.addAttribute("order", order);
        return "order/order_detail";
    }

    // Affiche le formulaire de modification pour une commande
    @RequestMapping("/editOrder/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        Orders order = ordersService.getOrdersById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
        model.addAttribute("orderForm", order);
        return "order/edit_order";
    }

    // Met à jour une commande
    @RequestMapping(value = "/updateOrder/{id}", method = RequestMethod.POST)
    public String updateOrder(@PathVariable("id") Long id, @ModelAttribute("orderForm") Orders updatedOrder) {
        ordersService.updateOrders(id, updatedOrder);
        return "redirect:/allOrders";
    }

    // Supprime une commande par son ID
    @RequestMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        ordersService.deleteOrders(id);
        return "redirect:/allOrders";
    }
}

