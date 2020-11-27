package com.cybertek.ecommerce.implementation;

import com.cybertek.ecommerce.dto.CategoryDTO;
import com.cybertek.ecommerce.entity.Category;
import com.cybertek.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryDao extends AbstractDao<CategoryDTO,Integer>  implements CategoryService{

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
     * Read all categories.
     * @return list of categories {@link List<Category>}
     */
    public List<CategoryDTO> readAll() {
        return categories;
    }

    /**
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

    /**
     * Create new category
     * @param categoryDTO CategoryDTO to be created.
     */
    public void create(CategoryDTO categoryDTO) {
        List<CategoryDTO> sortedCategories = categories.stream()
                .sorted(Comparator.comparing(CategoryDTO::getId))
                .collect(Collectors.toList());
        Optional<CategoryDTO> lastCategory = sortedCategories.stream().reduce((a, b) -> b);

        int lastId = lastCategory.isPresent() ? lastCategory.get().getId() : 0;

        categoryDTO.setId(lastId + 1);
        categories.add(categoryDTO);
    }

    /**
     * Update an existing CategoryDTO.
     * @param categoryDTO CategoryDTO to be updated
     * @param id categoryDTO id
     * @throws Exception exception if category does not exist.
     */
    public void update(CategoryDTO categoryDTO, Integer id) {
        CategoryDTO foundedCategoryDTO = readById(id);

        if (foundedCategoryDTO == null) {
            System.out.println(("This category does not exist!"));
        }

        foundedCategoryDTO.setName(categoryDTO.getName());
        foundedCategoryDTO.setName(categoryDTO.getDescription());
    }

    /**
     * Delete category
     * @param id specific id to be deleted.
     */
    public void delete(Integer id) {

        categories.removeIf(x -> x.getId().equals(id));
        // Write your code to delete the category in a list.
    }

}
