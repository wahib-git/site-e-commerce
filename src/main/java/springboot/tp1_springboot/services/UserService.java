package springboot.tp1_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.tp1_springboot.model.User;
import springboot.tp1_springboot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Récupérer un utilisateur par son id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Mettre à jour un utilisateur
    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(updatedUser.getName());
            // Mettre à jour d'autres champs si nécessaire
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    // Supprimer un utilisateur par son id
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

