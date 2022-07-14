package br.com.lookatthiscar.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(
    basePackages =
    ["br.com.lookatthiscar.repository"]
)
@EnableTransactionManagement
class DBConfig {

    @Autowired
    lateinit var datasourceProperties: DBProperties

    @Bean
    fun dataSource(): DataSource? {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(datasourceProperties.driverClassName)
        dataSource.url = datasourceProperties.url
        dataSource.password = datasourceProperties.password
        dataSource.username = datasourceProperties.username
        return dataSource
    }

    @Bean
    fun entityManagerFactory(
        @Autowired
        @Qualifier("hibernateProperties") properties: Properties
    ): LocalContainerEntityManagerFactoryBean? {
        val entityManager = LocalContainerEntityManagerFactoryBean()
        entityManager.dataSource = dataSource()!!
        entityManager.setPackagesToScan(*arrayOf("br.com.lookatthiscar.model.entity"))
        entityManager.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManager.setJpaProperties(properties);
        return entityManager
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory?): JpaTransactionManager? {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }

    @Bean("hibernateProperties")
    @Profile("test")
    fun properties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop")
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
        properties.setProperty("hibernate.show_sql", "true")
        return properties
    }

    @Bean("hibernateProperties")
    @Profile("!test")
    fun postgreSqlProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", "none")
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
        properties.setProperty("hibernate.show_sql", "true")
        return properties
    }

}