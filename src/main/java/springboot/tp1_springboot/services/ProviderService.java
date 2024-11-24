package springboot.tp1_springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.tp1_springboot.model.Provider;
import springboot.tp1_springboot.repository.ProviderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    // Créer un fournisseur
    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    // Récupérer un fournisseur par son ID
    public Optional<Provider> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

    // Récupérer tous les fournisseurs
    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    // Mettre à jour un fournisseur
    public Provider updateProvider(Long id, Provider updatedProvider) {
        Optional<Provider> existingProviderOptional = providerRepository.findById(id);

        if (existingProviderOptional.isPresent()) {
            Provider existingProvider = existingProviderOptional.get();
            existingProvider.setName(updatedProvider.getName());
            existingProvider.setEmail(updatedProvider.getEmail());
            existingProvider.setPhone(updatedProvider.getPhone());
            // Ajouter d'autres champs si nécessaire
            return providerRepository.save(existingProvider);
        } else {
            throw new RuntimeException("Provider not found with id " + id);
        }
    }

    // Supprimer un fournisseur par son ID
    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }
}
