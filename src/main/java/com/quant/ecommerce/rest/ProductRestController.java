package com.quant.ecommerce.rest;

import com.quant.ecommerce.entity.PriceEntity;
import com.quant.ecommerce.handler.EcommerceBusinessException;
import com.quant.ecommerce.service.PriceService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ProductRestController {

    public static final String GET_PRICE_ENDPOINT = "/product/{productId}";
    public static final String PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    private final PriceService priceService;

    public ProductRestController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "Get price for brandId, productId and dateTime")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @Parameter(in = ParameterIn.HEADER, description = "Brand identified", name="brandId")
    @GetMapping(GET_PRICE_ENDPOINT)
    public Mono<PriceEntity> getPriceByBrandProductDateTimeApplied(@PathVariable("productId") @NonNull Integer productId,
                                                                   @RequestHeader("brandId") @NonNull Integer brandId,
                                                                   @RequestParam("dateTimeApplied") @NonNull String dateTime) {
        LocalDateTime localDateTime = null;
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(PATTERN_YYYY_MM_DD_HH_MM));
        } catch (RuntimeException ex) {
            throw new EcommerceBusinessException("DateTime format is not valid (" + PATTERN_YYYY_MM_DD_HH_MM + ")");
        }

        return priceService.findAppliedPriceRate(brandId, productId, localDateTime)
            .onErrorResume(ex -> Mono.error(new EcommerceBusinessException(ex.getMessage())));
    }
}
