/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.mapper;

import com.sfl.configuration.MapStructConfig;
import com.sfl.model.Product;
import com.sfl.model.ProductInOrder;
import org.mapstruct.Mapper;


@Mapper(config = MapStructConfig.class)
public interface ProductMapper {

  ProductInOrder toProductInOrder(Product product);
}
