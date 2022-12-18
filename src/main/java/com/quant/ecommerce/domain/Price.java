package com.quant.ecommerce.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Price(
    Integer brandId,
    Integer productId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer priceList,
    Double price,
    String currency) {
}
