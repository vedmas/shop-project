package ru.tokarev.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.tokarev.shop.controller.repr.CategoryRepr;
import ru.tokarev.shop.repository.entity.Category;
import ru.tokarev.shop.service.category.CategoryService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categoriesPage(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categories);
        return "/admin/categories";
    }

    @GetMapping("/category/{id}/edit")
    public String categoryEdit(@PathVariable Long id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryService.get(id));
        return "admin/category_form";
    }

    @GetMapping("/category/create")
    public  String categoryCreate(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new Category());
        return "admin/category_form";
    }

    @PostMapping("/category")
    public String actionOnTheCategory(@Valid CategoryRepr categoryRepr, RedirectAttributes redirectAttributes) {
        try {
            categoryService.saveCategory(categoryRepr);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if (categoryRepr.getId() == null) {
                return "redirect:/admin/category/create";
            }
            return "redirect:/admin/category/" + categoryRepr.getId() + "/edit";
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/category/{id}/delete")
    public String categoryDelete(@PathVariable Long id, Model model) {
        categoryService.deleteById(id);
        model.addAttribute("activePage", "Categories");
        return "redirect:/admin/categories";
    }
}
