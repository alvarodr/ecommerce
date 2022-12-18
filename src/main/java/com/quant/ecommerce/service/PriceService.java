package com.quant.ecommerce.service;

import com.quant.ecommerce.domain.Price;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface PriceService {

    /**
     * Find priority price by date time
     * @param dateTime
     * @return
     */
    Mono<Price> findAppliedPriceRate(Integer brandId, Integer productId, LocalDateTime dateTime);
}
