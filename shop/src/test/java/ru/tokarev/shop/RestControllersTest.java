package ru.tokarev.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tokarev.shop.repository.entity.Category;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllersTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void categoryControllerTest() {
        List<Category> categoryList = this.restTemplate.getForObject("/api/v1/category/all", List.class);
        Assert.assertFalse(categoryList.isEmpty());
    }
}
