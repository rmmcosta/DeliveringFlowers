package com.rmmcosta.deliveringflowers.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Autowired
    Environment env;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "com.rmmcosta.datasource")
    public DataSource getDataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url(env.getProperty("com.rmmcosta.datasource.url"));
        return builder.build();
    }

    /*
    @Autowired
    Environment env;

    @Primary
    @Bean
    public DataSource customDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("com.rmmcosta.datasource.url"));
        dataSource.setUsername(env.getProperty("com.rmmcosta.datasource.username"));
        dataSource.setPassword(env.getProperty("com.rmmcosta.datasource.password"));

        return dataSource;
    }
    */
}
