package ru.tokarev.shop.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tokarev.shop.controller.repr.ProductRepr;
import ru.tokarev.shop.repository.entity.Products;
import ru.tokarev.shop.service.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/all")
    public List<Products> productAll() {
        return productService.findAll();
    }

    @GetMapping("{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

    @GetMapping("{id}/view")
    public ProductRepr viewProduct(@PathVariable("id") Long id) {
        return productService.get(id);
    }
}
