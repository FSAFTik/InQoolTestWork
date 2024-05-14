package com.example.TennisReservation.Configurators;

import com.example.TennisReservation.Dao.CourtDao;
import com.example.TennisReservation.Dao.CustomerDao;
import com.example.TennisReservation.Dao.ReservationDao;
import com.example.TennisReservation.Dao.SurfaceDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * This class is responsible for configuring the services for the application.
 * It is annotated with @Configuration to indicate that it's a source of bean definitions.
 * The @EnableTransactionManagement annotation is used to enable Spring's annotation-driven transaction management capability.
 */
@Configuration
@EnableTransactionManagement
public class ServiceConfig {

    /**
     * Configures the DataSource bean.
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        return dataSource;
    }

    /**
     * Configures the SessionFactory bean.
     *
     * @return LocalSessionFactoryBean
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.TennisReservation");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * Sets the Hibernate properties.
     *
     * @return Properties
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return properties;
    }

    /**
     * Configures the ReservationDao bean.
     *
     * @return ReservationDao
     */
    @Bean
    public ReservationDao reservationDao() {
        return new ReservationDao(sessionFactory().getObject());
    }

    /**
     * Configures the CourtDao bean.
     *
     * @return CourtDao
     */
    @Bean
    public CourtDao courtDao() {
        return new CourtDao(sessionFactory().getObject());
    }

    /**
     * Configures the CustomerDao bean.
     *
     * @return CustomerDao
     */
    @Bean
    public CustomerDao customerDao() {
        return new CustomerDao(sessionFactory().getObject());
    }

    /**
     * Configures the SurfaceDao bean.
     *
     * @return SurfaceDao
     */
    @Bean
    public SurfaceDao surfaceDao() {
        return new SurfaceDao(sessionFactory().getObject());
    }

    /**
     * Configures the PlatformTransactionManager bean.
     *
     * @param sessionFactory SessionFactory
     * @return PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}