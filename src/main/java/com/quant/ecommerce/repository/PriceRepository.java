package com.quant.ecommerce.repository;

import com.quant.ecommerce.entity.Price;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface PriceRepository extends ReactiveCrudRepository<Price, Long> {

    @Query("select ")
    Flux<Price> findApplyedRate(@Param("dateTime") LocalDateTime dateTime);
}
