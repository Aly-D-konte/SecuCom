package com.example.SecuCom.Controller;

import com.example.SecuCom.Model.User;
import com.example.SecuCom.ServicesUserdCrud.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserCrudController {

    @Autowired
    private UserCrudService usersCrud;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public User ajoute(@RequestBody User user ){

        return usersCrud.Ajouter(user);
    }

    // Listé les utilisateurs

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<User> AfficherUsers(){
        return usersCrud.Afficher();
    }

    //    MODIFIER les utilisateurs
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier/{id}"})
    public String ModifierUser(@RequestBody User users, @PathVariable  Long id){

        usersCrud.Modifier(users, id);
        return "Modification reçu avec succès";
    }


    //    Supprimer les utilisateurs


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/Supprimer/{id}")
    public String Supprimer(@PathVariable("id") Long id){
        usersCrud.Supprimer(id);
        return "Suppression reçu";
    }




}
