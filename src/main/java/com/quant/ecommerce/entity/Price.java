package com.quant.ecommerce.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Mapper entity from PRICES table
 *
 * @param brandId brand identified, example (1 = ZARA)
 * @param startDate start date to apply price
 * @param endDate end date to apply price
 * @param priceList identified rate price apply
 * @param productId identified price
 * @param priority higher to lower priority number
 * @param price to apply
 * @param currency of price
 */
@Table(name = "PRICES")
@Builder
public record Price(
    @Id @Column("ID") Long id,
    @Column("BRAND_ID") Integer brandId,
    @Column("START_DATE") LocalDateTime startDate,
    @Column("END_DATE") LocalDateTime endDate,
    @Column("PRICE_LIST") Integer priceList,
    @Column("PRODUCT_ID") Integer productId,
    @Column("PRIORITY") Integer priority,
    @Column("PRICE") Double price,
    @Column("CURR") String currency
) {}
