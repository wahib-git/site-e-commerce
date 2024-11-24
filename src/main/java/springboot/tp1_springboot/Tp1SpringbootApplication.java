package springboot.tp1_springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springboot.tp1_springboot.model.User;
import springboot.tp1_springboot.repository.UserRepository;

@SpringBootApplication
public class Tp1SpringbootApplication {

    // Define a logger
    private static final Logger log = LoggerFactory.getLogger(Tp1SpringbootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Tp1SpringbootApplication.class, args);
    }

    // CommandLineRunner to execute some code on application startup
    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // Save a new user
            repository.save(new User("saif"));

            // Log all users in the repository
            for (User user : repository.findAll()) {
                log.info("The user is: " + user.toString());
            }
        };
    }
}
