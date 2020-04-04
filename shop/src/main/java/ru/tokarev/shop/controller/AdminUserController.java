package ru.tokarev.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tokarev.shop.repository.entity.Gender;
import ru.tokarev.shop.repository.entity.Roles;
import ru.tokarev.shop.repository.entity.Users;
import ru.tokarev.shop.service.gender.GenderService;
import ru.tokarev.shop.service.repr.SystemUser;
import ru.tokarev.shop.service.role.RoleService;
import ru.tokarev.shop.service.user.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    private UserService userService;

    private RoleService roleService;

    private GenderService genderService;

    @Autowired
    public AdminUserController(UserService userService, RoleService roleService, GenderService genderService) {
        this.userService = userService;
        this.roleService = roleService;
        this.genderService = genderService;
    }

    @GetMapping("")
    public String adminPage(Model model) {
        model.addAttribute("activePage", "None");
        return "admin/index";
    }

    @GetMapping("/users")
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Users");
        List<SystemUser> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/users";
    }

     @GetMapping("/user/{id}/edit")
     public String userEdit(Model model, @PathVariable("id") Long id) {
        SystemUser user = userService.findById(id);
        List<Roles> roles = roleService.findAll();
        List<Gender> genders = genderService.findAll();
        model.addAttribute("activePage", "Users");
        model.addAttribute("edit", true);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        model.addAttribute("genders", genders);
        return "admin/user_form";
    }

    @GetMapping("user/create")
    public String userCreate(Model model) {
        List<Roles> roles = roleService.findAll();
        List<Gender> genderList = genderService.findAll();
        model.addAttribute("activePage", "Users");
        model.addAttribute("create", true);
        model.addAttribute("roles", roles);
        model.addAttribute("user", new Users());
        model.addAttribute("genders", genderList);
        return "admin/user_form";
    }

    @PostMapping("user")
    public String actionOnTheUser(@Valid SystemUser user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");
        if (bindingResult.hasErrors()) {
            return "admin/user_form";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

     @GetMapping("user/{id}/delete")
     public String userDelete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
