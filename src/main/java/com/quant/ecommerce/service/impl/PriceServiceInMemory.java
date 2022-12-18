package com.quant.ecommerce.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quant.ecommerce.domain.Price;
import com.quant.ecommerce.entity.PriceEntity;
import com.quant.ecommerce.handler.EcommerceBusinessException;
import com.quant.ecommerce.repository.PriceRepository;
import com.quant.ecommerce.service.PriceService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class PriceServiceInMemory implements PriceService {

    private final PriceRepository priceRepository;

    private final ObjectMapper mapper;

    public PriceServiceInMemory(PriceRepository priceRepository, ObjectMapper mapper) {
        this.priceRepository = priceRepository;
        this.mapper = mapper;
    }

    @Override
    public Mono<Price> findAppliedPriceRate(Integer brandId, Integer productId, LocalDateTime dateTime) {
        return priceRepository.findApplyedRate(brandId, productId, dateTime)
            .switchIfEmpty(Mono.error(new EcommerceBusinessException("Price not found")))
            .sort(Comparator.comparing(PriceEntity::priority).reversed()).next()
            .map(priceEntity -> mapper.convertValue(priceEntity, Price.class));
    }

}
