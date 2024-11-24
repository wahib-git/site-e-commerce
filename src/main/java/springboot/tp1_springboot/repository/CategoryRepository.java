package springboot.tp1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tp1_springboot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

