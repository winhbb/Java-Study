package com.gaodun.storm.study.common;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

public abstract class DatabaseConfig {
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, JpaProperties jpaProperties,  DataSource dataSource,String entityPackage,String persistenceUnitName) {
        return builder.dataSource(dataSource).packages(entityPackage).properties(jpaProperties.getProperties()).persistenceUnit(persistenceUnitName).build();
    }

    public EntityManager entityManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return entityManagerFactory.getObject().createEntityManager();
    }

    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
