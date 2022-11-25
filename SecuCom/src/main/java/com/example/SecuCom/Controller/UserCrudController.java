package com.example.SecuCom.Controller;

import com.example.SecuCom.Model.User;
import com.example.SecuCom.ServicesUserdCrud.UserCrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")

public class UserCrudController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserCrudService usersCrud;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public User ajoute(@RequestBody User user ){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Welcome home Page " + localDateTime);
        return usersCrud.Ajouter(user);
    }

    // Listé les utilisateurs

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<User> AfficherUsers(){

        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Welcome home Page " + localDateTime);
        return usersCrud.Afficher();
    }

    //    MODIFIER les utilisateurs
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier/{id}"})
    public String ModifierUser(@RequestBody User users, @PathVariable  Long id){
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Welcome home Page " + localDateTime);
        usersCrud.Modifier(users, id);
        return "Modification reçu avec succès";
    }


    //    Supprimer les utilisateurs


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/Supprimer/{id}")
    public String Supprimer(@PathVariable("id") Long id){
        usersCrud.Supprimer(id);
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("Welcome home Page " + localDateTime);
        return "Suppression reçu";
    }




}
