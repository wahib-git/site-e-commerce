package springboot.tp1_springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;

    // Relation Many-to-Many avec Orders
    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;

    // Relation Many-to-One avec Provider
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    // Relation Many-to-One avec Subcategory
    @OneToMany(mappedBy = "products",cascade = CascadeType.ALL)
    private List<Subcategory> Subcategory;


    }



