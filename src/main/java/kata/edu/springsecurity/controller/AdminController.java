package kata.edu.springsecurity.controller;

import kata.edu.springsecurity.entity.User;
import kata.edu.springsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String getAll(ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.getAll());
        return "user_list";
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

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.update(id, user.getName(), user.getLastName());
        return "redirect:/admin/users";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        userService.remove(id);
        return "redirect:/admin/users";
    }
}
