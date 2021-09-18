package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.CategoryDTO;
import ru.home.models.Category;

@Component
public class CategoryMapper {
    @Autowired
    ModelMapper modelMapper;

    public CategoryDTO EntityToDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public List<CategoryDTO> toCategoryDTOS(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }
}
