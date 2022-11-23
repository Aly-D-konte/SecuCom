package com.example.SecuCom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.SecuCom.Model.User;
import com.example.SecuCom.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/add")
    public User add (@RequestBody User user){
        return userService.add(user);
    }

    @PutMapping("/modifier/{id}")
    public  String modifier(@RequestBody User user, @PathVariable Long id ){
        this.userService.modifier(user, id);
        return  "Utilisateur modifier avec succès";
    }

    @GetMapping("/liste")
    public List<User> liste(){
        return  userService.liste();
    }

    @DeleteMapping("/delete/{id}")
    public  String supprimer(@PathVariable Long id){
        this.userService.supprimer(id);
        return  "Utilisateur supprimer avec succès";
    }
}
