package com.example.SecuCom.ServiceImpl;

import java.util.List;

import com.example.SecuCom.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SecuCom.Repository.UserRepository;
import com.example.SecuCom.Services.UserService;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    UserRepository userrepository;


    @Override
    public User add(User user) {
        return this.userrepository.save(user);
    }

    @Override
    public User modifier(User user, Long id) {
        return this.userrepository.findById(id).map(
                u->{
                    u.setNom(user.getNom());
                    u.setPrenom(user.getPrenom());
                    u.setEmail(user.getEmail());
                    u.setPassword(user.getPassword());
                    return  userrepository.save(u);
                }
        ).orElseThrow(() -> new RuntimeException( "Utilisateur non trouvé"));
    }

    @Override
    public List<User> liste() {
        return this.userrepository.findAll();
    }

    @Override
    public String supprimer(Long id) {
        this.userrepository.deleteById(id);
        return "Utilisateur supprimer avec succés";
    }
}
