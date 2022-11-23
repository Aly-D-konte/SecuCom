package com.example.SecuCom.Services;

import java.util.List;

import com.example.SecuCom.Model.User;

public interface UserService {

    User add (User user);
    User modifier(User user, Long id);
    List<User> liste();
    String supprimer(Long id);
    
}
