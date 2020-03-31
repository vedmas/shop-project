package ru.tokarev.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tokarev.shop.service.cart.CartService;
import ru.tokarev.shop.service.product.ProductService;
import ru.tokarev.shop.service.repr.ProductInfo;

@Controller
@RequestMapping("/product")
public class MainProductController {

    private ProductService productService;
    private CartService cartService;

    @Autowired
    public MainProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("{id}")
    public String productDetail(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("productsRelated", productService.findAll());
        model.addAttribute("productView", productService.get(id));
        return "product_view";
    }

    @GetMapping("")
    public String productDet(Model model) {
        model.addAttribute("productsRelated", productService.findAll());
        model.addAttribute("productView", productService.get(30L));
        return "product_view";
    }

        @PostMapping("cart/update")
        public String updateCart (Long productId, String urlPath){
            cartService.addProductInfo(new ProductInfo(productService.get(productId), 1L));
            return "redirect:" + urlPath;
        }
    }

