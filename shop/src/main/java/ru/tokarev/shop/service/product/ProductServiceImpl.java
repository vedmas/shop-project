package ru.tokarev.shop.service.product;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.tokarev.shop.aspect.TrackTime;
import ru.tokarev.shop.controller.repr.ProductRepr;
import ru.tokarev.shop.repository.ProductRepository;
import ru.tokarev.shop.repository.entity.Picture;
import ru.tokarev.shop.repository.entity.PictureData;
import ru.tokarev.shop.repository.entity.Products;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service("productService")
@Slf4j
public class ProductServiceImpl implements ProductService, Serializable {

    private static final long serialVersionUID = -5039352816560595361L;

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @TrackTime
    @Override
    @Transactional
    public List<Products> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public ProductRepr get(Long id) {
        return new ProductRepr(productRepository.findById(id).get());
    }

    @TrackTime
    @Override
    @Transactional
    public void saveProduct(ProductRepr productRepr) throws IOException {
        Products product = (productRepr.getId() != null) ? productRepository.findById(productRepr.getId()).get()
                : new Products();
        product.setNameProduct(productRepr.getNameProduct());
        product.setCategory(productRepr.getCategory());
        product.setProducer(productRepr.getProducer());
        product.setPrice(productRepr.getPrice());
        product.setDiscount(productRepr.getDiscount());
        if (productRepr.getNewPictures() != null) {
            for (MultipartFile newPicture : productRepr.getNewPictures()) {
                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }
                product.getPictures().add(new Picture(newPicture.getOriginalFilename(),
                        newPicture.getContentType(), new PictureData(newPicture.getBytes())));
            }
        }
        productRepository.save(product);
        log.info("Product id {} name {} save or update", product.getId(), product.getNameProduct());
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void delete(Products product) {
        productRepository.delete(product);
    }

    @Override
    public Products findOneByNameProduct(String name) {
        return null;
    }

    @Override
    public Products findOneById(Long id) {
        return productRepository.findOneById(id);
    }
}
