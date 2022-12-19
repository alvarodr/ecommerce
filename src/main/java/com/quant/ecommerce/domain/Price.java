package com.quant.ecommerce.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@ApiModel(description = "Data price applied in a datetime")
public record Price(
    @ApiModelProperty(
        value = "Brand identifier",
        name = "brandId",
        dataType = "Integer",
        example = "1"
    )
    Integer brandId,
    @ApiModelProperty(
        value = "Product identifier",
        name = "productId",
        dataType = "Integer",
        example = "35455"
    )
    Integer productId,
    @ApiModelProperty(
        value = "Date start applied price",
        name = "startDate",
        dataType = "LocalDateTime",
        example = "2020-06-14T10:00:00"
    )
    LocalDateTime startDate,
    @ApiModelProperty(
        value = "Date end applied price",
        name = "endDate",
        dataType = "LocalDateTime",
        example = "2020-06-14T10:00:00"
    )
    LocalDateTime endDate,
    @ApiModelProperty(
        value = "Rate price list applied",
        name = "priceList",
        dataType = "Integer",
        example = "1"
    )
    Integer priceList,
    @ApiModelProperty(
        value = "Price product applied",
        name = "price",
        dataType = "Double",
        example = "35.50"
    )
    Double price,
    @ApiModelProperty(
        value = "Currency of price",
        name = "currency",
        dataType = "String",
        example = "EUR"
    )
    String currency) {
}
