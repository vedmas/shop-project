package ru.tokarev.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.tokarev.shop.controller.repr.SignupForm;
import ru.tokarev.shop.service.cart.CartService;
import ru.tokarev.shop.service.product.ProductService;
import ru.tokarev.shop.service.repr.ProductInfo;
import ru.tokarev.shop.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Slf4j
public class BasicController {

    private ProductService productService;

    private CartService cartService;

    private UserService userService;



    @Autowired
    public BasicController(ProductService productService, CartService cartService, UserService userService) {
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexPage(Model model, HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("products", productService.findAll());
        model.addAttribute("productsTop", productService.findAll());
        log.info("User registered {}", SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("User role {}", userService.isAdminRole());
        return "index";
    }
    @GetMapping("/login")
    String loginPage(SignupForm signupForm, Model model) {
        return "login";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignupForm signupForm, BindingResult bindingResult, RedirectAttributes attributes, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        if(signupForm.getNumberPhone().length() < 6) {
            model.addAttribute("error_numPhone", true);
            return "login";
        }
        attributes.addAttribute("numberPhone", signupForm.getNumberPhone());
        return "redirect:/register";
    }

    @GetMapping("/order/create")
    public String createOrder() {
        return "redirect:/checkout";
    }

    @GetMapping("/blank")
    public String blankPage() {
        return "blank";
    }


    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/checkoutAll")
    public String checkoutPage() {
        return "checkoutAll";
    }

    @GetMapping("/store")
    public String storePage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "store";
    }

    @PostMapping("/cart/update")
    public String updateCart(Long productId, String urlPath) {
        cartService.addProductInfo(new ProductInfo(productService.get(productId), 1L));
        return "redirect:" + urlPath;
    }

    @PostMapping("/cart/delete")
    public String deleteCart(Long productId, String urlPath) {
        cartService.delete(productId);
        log.info("Product id {} removed.", productId);
        return "redirect:" + urlPath;
    }
}
