package com.quant.ecommerce.config;

import com.quant.ecommerce.configuration.PersistenceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfiguration.class)
public class DataSourceConfig {

}
