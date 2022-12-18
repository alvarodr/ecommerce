package com.quant.ecommerce.service;

import com.quant.ecommerce.entity.PriceEntity;
import com.quant.ecommerce.repository.PriceRepository;
import com.quant.ecommerce.service.impl.PriceServiceInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class PriceEntityServiceShould {
    @Autowired
    PriceRepository priceRepository;
    PriceService priceService;

    @BeforeEach
    public void setUp() {
        priceService = new PriceServiceInMemory(priceRepository);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/master.input", delimiter = ';')
    public void day_and_hour_returned_price(Integer brandId, Integer productId, String dateRequest, Double priceExpected) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateRequest, dtf);

        Mono<PriceEntity> price = priceService.findAppliedPriceRate(brandId, productId, dateTime);

        StepVerifier.create(price).consumeNextWith(p -> Assertions.assertEquals(priceExpected, p.price())).verifyComplete();
    }

}