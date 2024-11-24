package springboot.tp1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tp1_springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

