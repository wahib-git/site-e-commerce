package springboot.tp1_springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tp1_springboot.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}

