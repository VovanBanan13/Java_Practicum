package ru.home.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.Category;
import ru.home.repositories.CategoryRepository;
import ru.home.services.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            throw new ObjectNotFoundAdvice();
        } else
            return categories;
    }

    @Override
    public Category getById(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
    }

    @Override
    public void save(Category category) {
        if(category.getName()==null)
            throw new ObjectNotFoundAdvice();
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(int id, Category category) {
        Category changedCategory = categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
        changedCategory.setName(category.getName());
        this.categoryRepository.save(changedCategory);
        return changedCategory;
    }
}