package com.gaodun.storm.course;

import com.gaodun.storm.study.common.DatabaseConfig;
import com.gaodun.storm.study.common.YamlPropertyLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration(value = Config.DB_NAME + "Config")
@PropertySource(value = "classpath:" + Config.DB_NAME + "-datasource.yml", factory = YamlPropertyLoaderFactory.class)
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = Config.DB_NAME + "EntityManagerFactory",
        transactionManagerRef = Config.DB_NAME + "TransactionManager",
        basePackages = {Config.REPOSITORY_PACKAGE})
public class Config extends DatabaseConfig {
    public static final String DB_NAME = "course";
    public static final String REPOSITORY_PACKAGE = "com.gaodun.storm.course.domain.model";
    public static final String ENTITY_PACKAGE = "com.gaodun.storm.course.domain.model";
    public static final String PERSISTENCE_UNIT_NAME = DB_NAME + "PersistenceUnit";

    @Autowired
    Environment env;
    @Bean(name = DB_NAME + "DataSourceProperties")
    @ConfigurationProperties("spring.datasource."+DB_NAME)
    public DataSourceProperties dataSourceProperties() {
        return super.dataSourceProperties();
    }

    @Bean(name = DB_NAME + "DataSource")
    public DataSource dataSource(@Qualifier(DB_NAME + "DataSourceProperties") DataSourceProperties dataSourceProperties) {
        return super.dataSource(dataSourceProperties);
    }

    @Bean(name = DB_NAME + "EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(ApplicationContext context,EntityManagerFactoryBuilder builder, JpaProperties jpaProperties, @Qualifier(DB_NAME + "DataSource") DataSource dataSource) {
        return super.entityManagerFactory(builder, jpaProperties, dataSource, ENTITY_PACKAGE, PERSISTENCE_UNIT_NAME);
    }

    @Bean(name = DB_NAME + "EntityManager")
    public EntityManager entityManager(@Qualifier(DB_NAME + "EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return super.entityManager(entityManagerFactory);
    }

    @Bean(name = DB_NAME + "TransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier(DB_NAME + "EntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return super.transactionManager(entityManagerFactory);
    }
}
