package com.cybertek.ecommerce.implementation;

import com.cybertek.ecommerce.dto.CategoryDTO;
import com.cybertek.ecommerce.dto.ProductDTO;
import com.cybertek.ecommerce.service.CategoryService;
import com.cybertek.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductDao extends AbstractDao<ProductDTO,Integer>  implements ProductService {


    /**
     * List of ProductDTO for simulating DB
     */
    public static List<ProductDTO> products = new ArrayList<>();
    static {
        products.add(new ProductDTO(1, "Phones", BigDecimal.valueOf(1000l),5,"Apple",new CategoryDao().readById(2)));
        products.add(new ProductDTO(2, "Tshirt", BigDecimal.valueOf(10l),4,"Zara",new CategoryDao().readById(4)));
        products.add(new ProductDTO(3, "Mouse", BigDecimal.valueOf(1l),2,"Logitech",new CategoryDao().readById(2)));
        products.add(new ProductDTO(1, "Parfume", BigDecimal.valueOf(100l),2,"DKNY",new CategoryDao().readById(3)));

    }

    /**
     * Default Constructor.
     */
    public ProductDao() {
    }

    /**
     *
     * @return list of ProductDTO {@link List<ProductDTO>}
     */
    @Override
    public List<ProductDTO> readAll() {
        return products;
    }



    /**
     * Read ProductDTO by id.
     * @param id specific id to be readed.
     * @return specific ProductDTO {@link ProductDTO}
     */
    @Override
    public void update(ProductDTO object, Integer id) {

    }
    /**
     * Delete ProductDTO
     * @param id specific id to be deleted.
     */
    @Override
    public void delete(Integer id) {

    }
    /**
     * Create new ProductDTO
     * @param productDTO ProductDTO to be created.
     */
    @Override
    public void create(ProductDTO productDTO) {
        /*List<ProductDTO> sortedCategories = products.stream()
                .sorted(Comparator.comparing(ProductDTO::getId))
                .collect(Collectors.toList());

        Optional<ProductDTO> lastCategory = sortedCategories.stream().reduce((a, b) -> b);

        int lastId = lastCategory.isPresent() ? lastCategory.get().getId() : 0;

        productDTO.setId(lastId + 1);*/
        products.add(productDTO);
    }


    /**
     * Read ProductDTO by id.
     * @param id specific id to be readed.
     * @return specific ProductDTO {@link ProductDTO}
     */
    @Override
    public ProductDTO readById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    }

