package ru.tokarev.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.tokarev.shop.controller.repr.RoleRepr;
import ru.tokarev.shop.repository.entity.Roles;
import ru.tokarev.shop.service.role.RoleService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class RoleAdminController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public String rolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        List<Roles> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "admin/roles";
    }

    @GetMapping("/role/{id}/edit")
    public String producerEdit(@PathVariable Long id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", roleService.get(id));
        return "admin/role_form";
    }

    @GetMapping("/role/create")
    public String producerCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new Roles());
        return "admin/role_form";
    }

    @PostMapping("/role")
    public String actionOnTheProducer(RoleRepr roleRepr, RedirectAttributes redirectAttributes) {
        try {
            roleService.saveRoles(roleRepr);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if (roleRepr.getId() == null) {
                return "redirect:/admin/role/create";
            }
            return "redirect:/admin/role/" + roleRepr.getId() + "/edit";
        }
        return "redirect:/admin/roles";
    }

    @GetMapping("/role/{id}/delete")
    public String roleDelete(@PathVariable Long id, Model model) {
        roleService.deleteById(id);
        model.addAttribute("activePage", "Roles");
        return "redirect:/admin/roles";
    }
}
