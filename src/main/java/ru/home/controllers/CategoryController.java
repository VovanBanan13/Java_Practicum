package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import ru.home.dto.CategoryDto;
import ru.home.mappers.CategoryMapper;
import ru.home.models.Category;
import ru.home.services.CategoryService;

@RestController
@RequestMapping("/category")
@Api(value="category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @ApiOperation(value = "View a list of categories")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryService.findAllCategories();

        return new ResponseEntity<>(categoryMapper.toCategoryDtos(categories), HttpStatus.OK);
    }

    @ApiOperation(value = "View information about the category")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Integer id) {
        Category category = categoryService.getById(id);

        return new ResponseEntity<>(categoryMapper.entityToDto(category), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new category")
    @PostMapping(produces = "application/json")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody Category category) {
        categoryService.save(category);

        return new ResponseEntity<>(categoryMapper.entityToDto(category), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update category information")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
        Category changedCategory = categoryService.update(id, category);

        return new ResponseEntity<>(categoryMapper.entityToDto(changedCategory), HttpStatus.OK);
    }

    @ApiOperation(value = "Remote category")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable("id") Integer id) {
        Category category = categoryService.getById(id);

        categoryService.delete(id);

        return new ResponseEntity<>(categoryMapper.entityToDto(category),HttpStatus.NO_CONTENT);
    }
}
