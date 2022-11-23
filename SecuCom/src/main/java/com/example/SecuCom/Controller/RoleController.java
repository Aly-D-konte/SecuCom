package com.example.SecuCom.Controller;


import com.example.SecuCom.Model.Role;
import com.example.SecuCom.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/add")
    public Role add (@RequestBody Role role){
        return  this.roleService.add(role);

    }


    @PutMapping("/modifier/{id}")
    public  Role modifier (@RequestBody  Role role, @PathVariable Long id ){
      return this.roleService.modifier(role, id);
    }

    @GetMapping("/liste")
    public List<Role> liste(){
        return  roleService.lister();
    }
}
