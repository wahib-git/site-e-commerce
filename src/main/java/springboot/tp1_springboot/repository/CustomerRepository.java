package springboot.tp1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tp1_springboot.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
