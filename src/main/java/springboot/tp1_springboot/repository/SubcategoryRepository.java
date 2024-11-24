package springboot.tp1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tp1_springboot.model.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
