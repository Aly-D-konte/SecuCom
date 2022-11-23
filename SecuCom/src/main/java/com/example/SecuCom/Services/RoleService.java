package com.example.SecuCom.Services;

import com.example.SecuCom.Model.Role;

import java.util.List;

public interface RoleService {
    Role add(Role role);
    Role modifier(Role role, Long id);
    String supprimer(Long id);
    List<Role> lister();
}
