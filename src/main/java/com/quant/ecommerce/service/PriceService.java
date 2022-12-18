package com.quant.ecommerce.service;

import com.quant.ecommerce.entity.PriceEntity;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface PriceService {

    /**
     * Find priority price by date time
     * @param dateTime
     * @return
     */
    Mono<PriceEntity> findAppliedPriceRate(Integer brandId, Integer productId, LocalDateTime dateTime);
}
