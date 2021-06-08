package com.sfl.repository;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */

import com.sfl.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(int id);

}
