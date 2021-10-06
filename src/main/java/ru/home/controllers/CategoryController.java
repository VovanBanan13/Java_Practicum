package ru.home.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.dto.CategoryDTO;
import ru.home.mappers.CategoryMapper;
import ru.home.models.Category;
import ru.home.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.findAllCategories();

        return new ResponseEntity<>(categoryMapper.toCategoryDTOS(categories), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Integer id) {
        Category category = categoryService.getById(id);

        return new ResponseEntity<>(categoryMapper.EntityToDto(category), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category) {
        categoryService.save(category);

        return new ResponseEntity<>(categoryMapper.EntityToDto(category), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        Category changedCategory = categoryService.update(id, category);

        return new ResponseEntity<>(categoryMapper.EntityToDto(changedCategory), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("id") Integer id) {
        Category category = categoryService.getById(id);

        categoryService.delete(id);

        return new ResponseEntity<>(categoryMapper.EntityToDto(category),HttpStatus.NO_CONTENT);
    }
}
