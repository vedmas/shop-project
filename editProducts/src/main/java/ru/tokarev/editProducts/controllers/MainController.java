package ru.tokarev.editProducts.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.tokarev.editProducts.service.CategoryServiceRest;
import ru.tokarev.editProducts.service.repr.CategoryInfo;

@Controller
@Slf4j
public class MainController {

    private RestTemplate restTemplate;
    private CategoryServiceRest categoryServiceRest;


    @Autowired
    public MainController(RestTemplate restTemplate, CategoryServiceRest categoryServiceRest) {
        this.restTemplate = restTemplate;
        this.categoryServiceRest = categoryServiceRest;
    }

    @GetMapping("")
    public String startPage() {
        return "index";
    }

    ResponseEntity<CategoryInfo> result;
    @GetMapping("/categories")
    public String productAll(Model model) {
        model.addAttribute("categoryList", categoryServiceRest.categoryAll());
//        result = restTemplate.getForEntity("http://localhost:8080/api/v1/category/3", CategoryInfo.class);
//        log.info("HTTP Status: {}, Category name: {}", result.getStatusCode(), Objects.requireNonNull(result.getBody()).getNameCategory());
        return "categories";
    }

    @GetMapping("/category/{id}/edit")
    public String editCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("category", categoryServiceRest.getCategoryById(id));
        model.addAttribute("edit", true);
        return "category_form";
    }

    @GetMapping("/category/create")
    public String createCategory(Model model) {
        model.addAttribute("category", new CategoryInfo());
        model.addAttribute("create", true);
        return "category_form";
    }

    @GetMapping("/category/{id}/delete")
    public String deleteCategoryById(@PathVariable("id") Long id) {
        categoryServiceRest.deleteCategoryById(id);
        return "redirect:/categories";
    }

    @PostMapping("/category")
    public String actionOnCategory(CategoryInfo categoryInfo, RedirectAttributes redirectAttributes) {
        try{
            log.info("categoryInfo id: {}, name: {}", categoryInfo.getId(), categoryInfo.getNameCategory());
            categoryServiceRest.saveCategory(categoryInfo);
        }
        catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if(categoryInfo.getId() == null) {
                return "redirect:/category/create";
            }
            else return "redirect:/category/" + categoryInfo.getId() + "/edit";
        }
        return "redirect:/categories";
    }


}
