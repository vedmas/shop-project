package ru.tokarev.editProducts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tokarev.editProducts.service.repr.CategoryInfo;

import java.util.List;

@Service
public class CategoryServiceRest implements CategoryService{

    private final RestTemplate restTemplate;

    @Autowired
    public CategoryServiceRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CategoryInfo> categoryAll() {
        return restTemplate.exchange(
                "http://shop/api/v1/category/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryInfo>>() {
                }
        ).getBody();
    }

    @Override
    public CategoryInfo getCategoryById(Long id) {
         return restTemplate.exchange("http://shop/api/v1/category/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CategoryInfo>() {
        }).getBody();
    }

    @Override
    public void deleteCategoryById(Long id) {
        restTemplate.delete("http://shop/api/v1/category/" + id);
    }

    @Override
    public void saveCategory(CategoryInfo categoryInfo) {
        restTemplate.put("http://shop/api/v1/category/save", categoryInfo);
    }
}

