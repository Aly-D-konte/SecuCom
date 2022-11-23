package com.example.SecuCom.ServiceImpl;

import com.example.SecuCom.Model.Role;
import com.example.SecuCom.Repository.RoleRepository;
import com.example.SecuCom.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role modifier(Role role, Long id) {
        return roleRepository.findById(id).map(
                r->{
                    r.setNom(role.getNom());
                    return  roleRepository.save(r);
                }
        ).orElseThrow(()-> new RuntimeException("Role modifier avec succès"));
    }

    @Override
    public String supprimer(Long id) {
        this.roleRepository.deleteById(id);
        return "Role modifier avec succès";
    }

    @Override
    public List<Role> lister() {
        return this.roleRepository.findAll();
    }
}
