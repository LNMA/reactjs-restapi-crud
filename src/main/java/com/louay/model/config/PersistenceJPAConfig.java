package com.louay.model.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.louay.model.dao")
@EntityScan(basePackages = "com.louay.model.entity")
public class PersistenceJPAConfig {
    @Bean("pool")
    @Order(1)
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/reactjs-restapi-crud?useSSL=false");
        dataSource.setUsername("root");//TODO : database username
        dataSource.setPassword("1729384#General");// TODO : database password
        return dataSource;
    }

    @Bean
    @Order(2)
    public PlatformTransactionManager transactionManager(@Autowired @Qualifier("pool") DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());

        return transactionManager;
    }

    @Bean
    @Order(3)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        JpaVendorAdapter vendorAdapter = getHibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean entityManagerBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerBean.setDataSource(dataSource);
        entityManagerBean.setPackagesToScan("com.louay.model");
        entityManagerBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerBean.setJpaProperties(additionalProperties());

        return entityManagerBean;
    }

    @Bean
    public HibernateJpaVendorAdapter getHibernateJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }


    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

        //for batch
        properties.setProperty("hibernate.jdbc.batch_size", "2");
        properties.setProperty("hibernate.order_inserts", "true");
        properties.setProperty("hibernate.order_updates", "true");
        properties.setProperty("hibernate.batch_versioned_data", "true");

        return properties;
    }
}
