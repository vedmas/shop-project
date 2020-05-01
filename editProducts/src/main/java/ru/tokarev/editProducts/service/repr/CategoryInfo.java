package ru.tokarev.editProducts.service.repr;

public class CategoryInfo {

    private Long id;
    private String nameCategory;

    public CategoryInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
