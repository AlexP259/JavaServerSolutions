package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Category;

import java.util.List;

public interface ICategoryService {

    public Category saveCategory(Category category);

    public List<Category> getAllCategory();

    public Boolean existCategory(String name);

    public Boolean deleteCategory(int id);
}
