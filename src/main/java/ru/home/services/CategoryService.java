package ru.home.services;

import java.util.List;
import ru.home.models.Category;

public interface CategoryService {
    List<Category> findAllCategories();
    Category getById(int id);
    void save(Category category);
    void delete(int id);
}
