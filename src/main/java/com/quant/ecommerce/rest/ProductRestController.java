package com.quant.ecommerce.rest;

import com.quant.ecommerce.domain.Price;
import com.quant.ecommerce.handler.ApiError;
import com.quant.ecommerce.handler.EcommerceBusinessException;
import com.quant.ecommerce.service.PriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Api(value = "Product REST Controller Services", tags = "REST APIs related to product")
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
            @ApiResponse(code = 200, message = "Success", response = Price.class),
            @ApiResponse(code = 400, message = "Not found", response = ApiError.class),
            @ApiResponse(code = 401, message = "Not found", response = ApiError.class),
            @ApiResponse(code = 403, message = "Not found", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ApiError.class)
    })
    @Parameter(in = ParameterIn.HEADER, description = "Brand identified", name="brandId")
    @GetMapping(value = GET_PRICE_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Price> getPriceByBrandProductDateTimeApplied(@PathVariable("productId")
                                                             @ApiParam(example = "35455", value = "Product identifier")
                                                             @NonNull Integer productId,
                                                             @RequestHeader("brandId")
                                                             @ApiParam(example = "1", value = "Product identifier")
                                                             @NonNull Integer brandId,
                                                             @RequestParam("dateTimeApplied")
                                                             @ApiParam(example = "2020-06-14 10:10", value = "DateTime requested price")
                                                             @NonNull String dateTime) {
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
