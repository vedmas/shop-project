package ru.tokarev.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.tokarev.shop.controller.repr.ProductRepr;
import ru.tokarev.shop.repository.CategoryRepository;
import ru.tokarev.shop.repository.entity.Products;
import ru.tokarev.shop.service.producer.ProducerService;
import ru.tokarev.shop.service.product.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProducerService producerService;

    @GetMapping("/products")
    public String productsPage(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", products);
        return "/admin/products";
    }

    @GetMapping("/product/create")
    public String createPage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", new ProductRepr());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("producers", producerService.findAll());
        return "admin/product_form";
    }

    @PostMapping("/product")
    public String saveProduct(Model model, ProductRepr productRepr, RedirectAttributes redirectAttributes) throws IOException {
        model.addAttribute("activePage", "Products");
        try {
            productService.saveProduct(productRepr);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if (productRepr.getId() == null) {
                return "redirect:/admin/product/create";
            }
            return "redirect:/admin/product/" + productRepr.getId() + "/edit";
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/product/{id}/edit")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", productService.get(id));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("producers", producerService.findAll());
        return "admin/product_form";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
       productService.deleteById(id);
       return "redirect:/admin/products";
    }
}