package com.quant.ecommerce.rest;

import com.quant.ecommerce.entity.Price;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
public class ProductRestControllerShould {

    @Autowired
    private ProductRestController productRestController;

    @ParameterizedTest
    @CsvFileSource(resources = "/master.input", delimiter = ';')
    public void request_datetime_brand_product_returned_price(Integer brandId, Integer productId, String dateTime,
                                                                Double priceExpected) {
        WebTestClient.bindToController(productRestController).build().get()
            .uri(uriBuilder -> uriBuilder.path("/product/{productId}")
                .queryParam("dateTimeApplied", dateTime)
                .build(productId))
            .accept(MediaType.APPLICATION_JSON)
            .header("brandId", String.valueOf(brandId))
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(Price.class)
            .value(price -> price.price(), Matchers.equalTo(priceExpected));

    }

}
