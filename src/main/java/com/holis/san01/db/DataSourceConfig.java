package com.holis.san01.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.exemplo.repo")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.a")
    public DataSource dataSourceA() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.b")
    public DataSource dataSourceB() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> targets = new HashMap<>();
        targets.put("A", dataSourceA());
        targets.put("B", dataSourceB());

        DynamicRoutingDataSource routing = new DynamicRoutingDataSource();
        routing.setDefaultTargetDataSource(dataSourceA());
        routing.setTargetDataSources(targets);

        return routing;
    }
}