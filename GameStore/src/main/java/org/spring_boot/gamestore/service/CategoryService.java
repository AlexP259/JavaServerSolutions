package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Category;
import org.spring_boot.gamestore.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Boolean existCategory(String name) {
        return categoryRepo.existsByName(name);
    }

    @Override
    public Boolean deleteCategory(int id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if(category != null){
            categoryRepo.delete(category);
            return true;
        }
        return false;
    }
}
