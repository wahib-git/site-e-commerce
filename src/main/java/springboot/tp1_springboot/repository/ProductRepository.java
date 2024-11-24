package springboot.tp1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tp1_springboot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

