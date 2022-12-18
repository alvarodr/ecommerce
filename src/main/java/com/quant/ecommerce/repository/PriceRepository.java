package com.quant.ecommerce.repository;

import com.quant.ecommerce.entity.PriceEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface PriceRepository extends ReactiveCrudRepository<PriceEntity, Long> {

    @Query("SELECT PRICES.* FROM PRICES WHERE :dateTime BETWEEN START_DATE AND END_DATE AND BRAND_ID = :brandId AND " +
            "PRODUCT_ID = :productId")
    Flux<PriceEntity> findApplyedRate(@Param("brandId") Integer brandId, @Param("productId") Integer productId,
                                      @Param("dateTime") LocalDateTime dateTime);
}
