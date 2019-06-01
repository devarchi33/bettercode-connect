package com.bettercode.connect.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.bettercode.connect.repository",
    entityManagerFactoryRef = "excelEntityManager",
    transactionManagerRef = "excelTransactionManager"
)
public class ExcelJpaConfig {
    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean("excelDataSource")
    @ConfigurationProperties(prefix = "excel.datasource")
    public DataSource excelDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean("excelEntityManager")
    @ConfigurationProperties(prefix = "excel.datasource")
    public LocalContainerEntityManagerFactoryBean excelEntityManager(
        EntityManagerFactoryBuilder builder, @Qualifier("excelDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("excel.datasource.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("excel.datasource.hibernate.hbm2ddl.auto"));

        return builder
            .dataSource(dataSource)
            .packages("com.bettercode.connect.entity")
            .persistenceUnit("excel")
            .properties(properties)
            .build();
    }

    @Bean("excelTransactionManager")
    public PlatformTransactionManager excelTransactionManager(
        @Qualifier("excelEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
