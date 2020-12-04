package com.cybertek.ecommerce.implementation;

import com.cybertek.ecommerce.dto.CategoryDTO;
import com.cybertek.ecommerce.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryDao {
    @Autowired
    private ProductDao productDao;
    /**
     * list of categories, simulate database.
     */
    public static List<CategoryDTO> categories = new ArrayList<>();

    /**
     * Populate categories with dummy data.
     */
    static {
        categories.add(new CategoryDTO(1, "General", "General category description"));
        categories.add(new CategoryDTO(2, "Electronics", "Electronics category desc"));
        categories.add(new CategoryDTO(3, "Beauty", "Beauty category description"));
        categories.add(CategoryDTO.builder()
                                .id(4)
                                .name("Clothes")
                                .build());
    }

    /**
     * Default Constructor.
     */
    public CategoryDao() {
    }

    /**
     * it works
     * Read all categories.
     * @return list of categories {@link List<CategoryDTO>}
     */
    public List<CategoryDTO> readAll() {
        return categories;
    }

    /**
     * it works
     * Read category by id.
     * @param id specific id to be readed.
     * @return specific Category {@link CategoryDTO}
     */
    public CategoryDTO readById(Integer id) {
       return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public CategoryDTO readByName(String name) {
        return categories.stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * it works                         control it
     * Create new category
     * @param categoryDTO CategoryDTO to be created.
     */
    public void create(CategoryDTO categoryDTO) throws Exception {

        CategoryDTO foundedCategoryDTO = readByName(categoryDTO.getName());

        if (foundedCategoryDTO != null)  {
            throw new Exception("This category already created ");
        }

        List<CategoryDTO> sortedCategories = categories.stream()
                .sorted(Comparator.comparing(CategoryDTO::getId))
                .collect(Collectors.toList());
        Optional<CategoryDTO> lastCategory = sortedCategories.stream().reduce((a, b) -> b);

        int lastId = lastCategory.isPresent() ? lastCategory.get().getId() : 0;

        categoryDTO.setId(lastId + 1);
        categories.add(categoryDTO);
    }

    /**
     *
     * add exception                do it
     * Update an existing CategoryDTO.
     * @param categoryDTO CategoryDTO to be updated
     * @param id categoryDTO id
     * @throws Exception exception if category does not exist.
     */
    public void update(CategoryDTO categoryDTO, Integer id) throws Exception {
        CategoryDTO foundedCategoryDTO = readById(id);

        if (foundedCategoryDTO == null)  {
            throw new Exception("This category does not  exist ");
        }


            foundedCategoryDTO.setName(categoryDTO.getName());
            foundedCategoryDTO.setDescription(categoryDTO.getDescription());

    }

    /**
     *
     * add control mechanism for category delete            do it
     * Delete category
     * @param id specific id to be deleted.
     */
    public void delete(Integer id) throws Exception {
        Optional<ProductDTO> productDTOs = productDao.readAll().stream()
                .filter(x -> x.getCategoryDTO().getId().equals(id))
                .reduce((a, b) -> b);

        int existOrNot = productDTOs.isPresent() ? productDTOs.get().getId() : 0;

        if(existOrNot>0) {

            throw new Exception("This category has products");

        }

        categories.removeIf(x -> x.getId().equals(id));






    }

}
