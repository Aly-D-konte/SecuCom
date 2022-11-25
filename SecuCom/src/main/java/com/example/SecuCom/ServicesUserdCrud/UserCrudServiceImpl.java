package com.example.SecuCom.ServicesUserdCrud;

import com.example.SecuCom.Model.Role;
import com.example.SecuCom.Model.User;
import com.example.SecuCom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCrudServiceImpl implements UserCrudService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repositoryUsers;
    @Override
    public String Supprimer(Long id) {
        repositoryUsers.deleteById(id);
        return "Supprimer avec succes";
    }

    @Override
    public String Modifier(User users, Long id) {
        return repositoryUsers.findById(id).map(
                use ->{
                    use.setEmail(users.getEmail());
                    //use.setName(users.getName());
                    use.setUsername(users.getUsername());
                    use.setPassword(passwordEncoder.encode(users.getPassword()));

                    repositoryUsers.save(use);
                    return "Modification reussie avec succès";
                }
        ).orElseThrow(() -> new RuntimeException("Cet utilisateur n'existe pas"));
    }

    @Override
    public List<User> Afficher() {
        return repositoryUsers.findAll();
    }

    @Override
    public User Ajouter(User user) {
        return repositoryUsers.save(user);
    }
}
