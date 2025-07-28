package com.pet.management.config;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionManagement;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.flywaydb.core.Flyway;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static jakarta.ejb.TransactionManagementType.BEAN;

@Singleton
@Startup
@TransactionManagement(value= BEAN)
public class FlywayMigration {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostConstruct
    public void migrate() {

        DataSource ds = lookupDataSource();

        Flyway flyway = Flyway.configure()
                .dataSource(ds)
                .baselineOnMigrate(true)
                .baselineVersion("1")
                .baselineDescription("Initial baseline")
                .load();

        flyway.migrate();
    }

    private DataSource lookupDataSource() {
        try {
            return (DataSource) new InitialContext().lookup("java:jboss/datasources/H2DS");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}