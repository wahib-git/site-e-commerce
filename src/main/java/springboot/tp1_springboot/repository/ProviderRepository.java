package springboot.tp1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tp1_springboot.model.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}

