package com.example.springsecurity.controller;

import com.example.springsecurity.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final RoleService roleService;


    @GetMapping("/allRole")
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("roles", roleService.getAll());
        return "role_list";
    }
    @PostMapping("/setRole/{id}")
    public String setRole(@PathVariable("id") Long id, @Valid @RequestParam(value = "role", required = false) String role) {
        roleService.setRoleToUser(id, role);
        return "redirect:/admin/users";
    }
}
