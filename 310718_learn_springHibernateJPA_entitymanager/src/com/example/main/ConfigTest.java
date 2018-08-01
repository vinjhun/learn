package com.example.main;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 * what @EnableTransactionManagement did behind :
 *  ->inject (AutoProxyRegistrar) && (ProxyTransactionManagementConfiguration)
 *  
 *  -> AutoProxyRegistrar
 *      -Register InfrastructureAdvisorAutoProxyCreator Component for container
 *      -Return an enhancer, using interceptor to intercept
 *      
 *  -> ProxyTransactionManagementConfiguration
 *      -Provide Transaction Advisor    
 * 
 * **/

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.example.dao", "com.example.entity", "com.example.service" })  //using scanning here to perform testing
public class ConfigTest
{
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(this.dataSource());
        emf.setPackagesToScan(new String[] { "com.example.entity" });
        emf.setJpaVendorAdapter(this.hibernateJpaVendorAdapter());
        
        return emf;
    }

    // DriverManagerDataSource -- simple connection without pool factory
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        ds.setUrl("jdbc:db2://172.16.29.8:50000/FICO");
        ds.setUsername("db2inst1");
        ds.setPassword("db2inst1");

        return ds;
    }

    //transaction manager, how they pass this?..
    //transaction manager required for transactional management...
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
    {
        JpaTransactionManager jpaTManager = new JpaTransactionManager();
        jpaTManager.setEntityManagerFactory(this.entityManagerFactory().getObject());   //what is the meaning of this...

        return jpaTManager;
    }
    
    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.DB2);
        adapter.setDatabasePlatform("org.hibernate.dialect.DB2Dialect");
        adapter.setShowSql(true);
        
        return adapter;
    }
}
