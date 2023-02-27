package com.example.springsecurity.controller;

import com.example.springsecurity.dto.UserCreateDto;
import com.example.springsecurity.dto.UserUpdateDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String user(ModelMap modelMap) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.addAttribute("user", userService.findByUsername(user.getUsername()));
        return "user";
    }

    @GetMapping("/users")
    public String getAll(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "user_list";
    }

    @GetMapping("/user/{id}")
    public ModelAndView getOne(@PathVariable("id") Long id) {
        return new ModelAndView("user", "user", userService.getOne(id));
    }

    @GetMapping("/user/{id}/HttpServletRequest")
    public String getOne(@PathVariable("id") Long id, HttpServletRequest request) {
        request.setAttribute("user", userService.getOne(id));
        return "user";
    }

    @GetMapping("/update/{id}")
    public String pageUpdate(ModelMap modelMap, @PathVariable("id") Long id) {
        modelMap.addAttribute("user", userService.getOne(id));
        return "user_manage";
    }

    @GetMapping("/create")
    public String pageCreate(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "user_manage";
    }

    @GetMapping("/setRole/{id}")
    public String pageSetRole(ModelMap modelMap, @PathVariable("id") Long id) {
        modelMap.addAttribute("user", userService.getOne(id));
        return "role";
    }

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute("user") UserCreateDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("user") UserUpdateDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        userService.update(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        userService.removeFromUsersRoles(id);
        userService.remove(id);
        return "redirect:/admin/users";
    }
}
