package com.resourcesManager.backend.resourcesManager;


import com.resourcesManager.backend.resourcesManager.services.AuthenticationService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;


@SpringBootApplication
public class ResourcesManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourcesManagerApplication.class, args);
    }
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource);
//        emf.setJpaVendorAdapter(jpaVendorAdapter);
//        emf.setPackagesToScan("com.resourcesManager.backend.resourcesManager.entities");
//        return emf;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/resourcesManager");
//        dataSource.setUsername("root");
//        dataSource.setPassword("");
//        return dataSource;
//    }
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setGenerateDdl(true);
//        adapter.setShowSql(true);
//        adapter.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");
//        return adapter;
//    }
    @Bean
    CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
        return args -> {
//            Stream.of("user", "responsable", "fournisseur", "technicien", "CHEF_DEP",
//                    "PROF").forEach(name -> {
//                Role
//                        role = new Role();
//                role.setNomRole(name);
//                authenticationService.saveRole(role.getNomRole());
//            });
//            Role responsableRole = authenticationService.getRole("responsable");
//            User responsable = new User();
//            responsable.setUsername("responsable");
//            responsable.setPassword("responsable");
//            responsable.setRoles(new ArrayList<>());
//            responsable.getRoles().add(responsableRole);
//            authenticationService.saveResponsable(responsable);
        };
    }


}
