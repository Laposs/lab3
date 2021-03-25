package ru.pebgs.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;
import java.util.Set;

@org.springframework.context.annotation.Configuration
public class SpringConfig {
    @Bean
    public Properties properties() {
        var properties = new Properties();
        properties.setProperty(Environment.ENHANCER_ENABLE_ASSOCIATION_MANAGEMENT, "true");
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        properties.setProperty(Environment.USER, "sa");
        properties.setProperty(Environment.PASS, "password");
        properties.setProperty(Environment.DRIVER, "org.h2.Driver");
        properties.setProperty(Environment.URL, "jdbc:h2:mem:pebgs");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "create");

        return properties;
    }

    @Bean
    @Primary
    public SessionFactory sessionFactory() {
        var entityManagerFactoryBean = new Configuration();

        var configurationBuilder = new ConfigurationBuilder();
        configurationBuilder
                .setUrls(ClasspathHelper.forPackage("ru.pebgs.model"))
                .addScanners(new TypeAnnotationsScanner());
        var reflections = new Reflections(configurationBuilder);
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Entity.class);
        for (Class<?> aClass : typesAnnotatedWith) {
            entityManagerFactoryBean.addAnnotatedClass(aClass);
        }

        entityManagerFactoryBean.setProperties(properties());
        entityManagerFactoryBean.addPackage("ru.pebgs.model.*");

        return entityManagerFactoryBean.buildSessionFactory();
    }
}
