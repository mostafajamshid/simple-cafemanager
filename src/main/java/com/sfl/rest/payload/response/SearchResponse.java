package com.sfl.rest.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private int orderCount;
    private List<OrderResponse> orders;
}
