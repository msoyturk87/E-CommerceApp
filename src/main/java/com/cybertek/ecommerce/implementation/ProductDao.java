package com.cybertek.ecommerce.implementation;

import com.cybertek.ecommerce.dto.CategoryDTO;
import com.cybertek.ecommerce.dto.ProductDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductDao {
    @Autowired
    private CategoryDao categoryDao;
    /**
     * List of ProductDTO for simulating DB
     */
    public static List<ProductDTO> products = new ArrayList<>();


    @PostConstruct
    public void addData(){

        products.add(new ProductDTO(1, "Phones", BigDecimal.valueOf(1000l),5,"Apple",categoryDao.readById(2)));
        products.add(new ProductDTO(2, "Tshirt", BigDecimal.valueOf(10l),4,"Zara",categoryDao.readById(4)));
        products.add(new ProductDTO(3, "Mouse", BigDecimal.valueOf(1l),2,"Logitech",categoryDao.readById(2)));
        products.add(new ProductDTO(4, "Parfume", BigDecimal.valueOf(100l),2,"DKNY",categoryDao.readById(3)));

    }

    /**
     * Default Constructor.
     */
    public ProductDao() {
    }

    /**
     * it works
     * @return list of ProductDTO {@link List<ProductDTO>}
     */
    public List<ProductDTO> readAll() {
        return products;
    }



    /**
     * add exception message                  do it 
     * Read ProductDTO by id.
     * @param id specific id to be readed.
     * @return specific ProductDTO {@link ProductDTO}
     */
    public void update(ProductDTO productDTO, Integer id) throws Exception {
        ProductDTO foundedProductDTO = readById(id);

        if (foundedProductDTO == null) {
            throw new Exception("This product does not  exist");
                    }


            foundedProductDTO.setName(productDTO.getName());
            foundedProductDTO.setDescription(productDTO.getDescription());

    }

    /**
     * it works
     * Delete ProductDTO
     * @param id specific id to be deleted.
     */

    public void delete(Integer id) {
        products.removeIf(x -> x.getId().equals(id));
    }
    /**
     * it works
     * Create new ProductDTO
     * @param productDTO ProductDTO to be created.
     */
    public void create(ProductDTO productDTO) {
        List<ProductDTO> sortedProducts = products.stream()
                .sorted(Comparator.comparing(ProductDTO::getId))
                .collect(Collectors.toList());

        Optional<ProductDTO> lastCategory = sortedProducts.stream().reduce((a, b) -> b);

        int lastId = lastCategory.isPresent() ? lastCategory.get().getId() : 0;

        productDTO.setId(lastId + 1);
        products.add(productDTO);
    }


    /**
     * it works
     * Read ProductDTO by id.
     * @param id specific id to be readed.
     * @return specific ProductDTO {@link ProductDTO}
     */
    public ProductDTO readById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    }

