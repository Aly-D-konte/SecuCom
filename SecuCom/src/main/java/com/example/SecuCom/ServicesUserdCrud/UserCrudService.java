package com.example.SecuCom.ServicesUserdCrud;

import java.util.List;

import com.example.SecuCom.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserCrudService {


    String Supprimer(Long id);  // LA METHODE PERMETTANT DE SUPPRIMER UN COLLABORATEUR

    String Modifier(User users, Long id);   // LA METHODE PERMETTANT DE MODIFIER UN COLLABORATEUR

    List<User> Afficher();       // LA METHODE PERMETTANT D'AFFICHER UN COLLABORATEUR

    User Ajouter(User user); // LA METHODE PERMETTANT D'AJOUTER UN COLLABORATEUR
    
}
