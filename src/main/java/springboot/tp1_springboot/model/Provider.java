package springboot.tp1_springboot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Provider extends User {
    private String matricule;
    private String service;
    private String company;

    // Relation One-to-Many avec Product
    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    private List<Product> products;

    // Getters et Setters

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

