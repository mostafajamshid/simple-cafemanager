/*
 * Copyright (c) 2021.  Mostafa Jamshid https://www.linkedin.com/in/mostafa-jamshid/
 */

package com.sfl.mapper;

import com.sfl.configuration.MapStructConfig;
import com.sfl.model.Order;
import com.sfl.rest.payload.response.OrderResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(config = MapStructConfig.class)
public interface OrderMapper {

  OrderResponse toOrderResponse(Order order);

  List<OrderResponse> toOrderResponses(List<Order> orders);
}
