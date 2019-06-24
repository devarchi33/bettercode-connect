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
    entityManagerFactoryRef = "connectEntityManager",
    transactionManagerRef = "connectTransactionManager"
)
public class ConnectJpaConfig {
    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Bean("connectDataSource")
    @ConfigurationProperties(prefix = "connect.datasource")
    public DataSource connectDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean("connectEntityManager")
    @ConfigurationProperties(prefix = "connect.datasource")
    public LocalContainerEntityManagerFactoryBean connectEntityManager(
        EntityManagerFactoryBuilder builder, @Qualifier("connectDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("connect.datasource.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("connect.datasource.hibernate.hbm2ddl.auto"));

        return builder
            .dataSource(dataSource)
            .packages("com.bettercode.connect.entity")
            .persistenceUnit("connect")
            .properties(properties)
            .build();
    }

    @Bean("connectTransactionManager")
    public PlatformTransactionManager connectTransactionManager(
        @Qualifier("connectEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
