package com.quant.ecommerce.service;

import com.quant.ecommerce.entity.Price;
import com.quant.ecommerce.repository.PriceRepository;
import com.quant.ecommerce.service.impl.PriceServiceInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.*;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PriceServiceShould {

    @Mock
    PriceRepository priceRepository;
    PriceService priceService;

    @BeforeEach
    public void setUp() {
        priceService = new PriceServiceInMemory(priceRepository);
    }

    @Test
    public void day_14_hour_10_00_returned_35_50_EUR() {
        Clock clock = Clock.fixed(Instant.parse("2020-06-14T10:00:00.00Z"), ZoneId.systemDefault());
        LocalDateTime dateTime = LocalDateTime.now(clock);
        Mockito.when(priceRepository.findApplyedRate(ArgumentMatchers.eq(dateTime))).thenReturn(Flux.fromStream(initMockPrice()));
        Mono<Price> price = priceService.findAppliedPriceRate(dateTime);

        StepVerifier.create(price).consumeNextWith(p -> {
            Assertions.assertEquals(35.50d, p.price(), "Price must be 35.50 EUR");
        }).verifyComplete();
    }

    private Stream<Price> initMockPrice() {
        return Stream.of(
            Price.builder().price(30.50d).priority(0).build(),
            Price.builder().price(25.45d).priority(0).build(),
            Price.builder().price(35.50d).priority(1).build()
        );
    }

}