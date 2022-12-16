package com.quant.ecommerce.service.impl;

import com.quant.ecommerce.entity.Price;
import com.quant.ecommerce.repository.PriceRepository;
import com.quant.ecommerce.service.PriceService;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;

public class PriceServiceInMemory implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceInMemory(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Mono<Price> findAppliedPriceRate(LocalDateTime dateTime) {
        return priceRepository.findApplyedRate(dateTime)
            .switchIfEmpty(Mono.error(new RuntimeException("Price not found")))
            .sort(Comparator.comparing(Price::priority).reversed()).next();
    }

}
