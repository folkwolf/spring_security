package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "admin/listUser";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "admin/userInfo";
    }

    @GetMapping("/add")
    public String addUser(@ModelAttribute("newUser") User newUser, Model model) {
        model.addAttribute("userRoles", roleService.getAllRoles());
        return "admin/newUser";
    }

    @PostMapping("/add")
    public String addNewUser(@ModelAttribute("newUser") User newUser) {
        if(Objects.nonNull(newUser) && newUser.getAge() != 0 && newUser.getName() != null && newUser.getSurname() != null) {
            userService.saveUser(newUser);
        }
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("userRoles", roleService.getAllRoles());
        return "admin/editUser";
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/"+id;
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
